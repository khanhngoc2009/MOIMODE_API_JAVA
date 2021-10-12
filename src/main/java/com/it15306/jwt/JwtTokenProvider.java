package com.it15306.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.it15306.dto.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtTokenProvider {
 
    static final long EXPIRATIONTIME = 864000000;
    static final String SECRET = "JWT_SECURITY_SECRET_KEY";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";


    
    
    // Tạo ra jwt từ thông tin user
    public String generateToken(CustomUserDetails userDetails) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATIONTIME);
        // Tạo chuỗi json web token từ id của user.
        return Jwts.builder()
                   .setSubject(Integer.toString(userDetails.getUser().getId()))
                   .setIssuedAt(now)
                   .setExpiration(expiryDate)
                   .signWith(SignatureAlgorithm.HS512, SECRET)
                   .compact();
    }

    // Lấy thông tin user từ jwt
    
    public Integer getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                            .setSigningKey(SECRET)
                            .parseClaimsJws(token)
                            .getBody();

        return Integer.parseInt(claims.getSubject());
    }
    
    public String getUserNameFromJWT(String token) {
        Claims claims = Jwts.parser()
                            .setSigningKey(SECRET)
                            .parseClaimsJws(token)
                            .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
//            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
//            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
//            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
//            log.error("JWT claims string is empty.");
        }
        return false;
    }
    
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }
    
    public String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ EXPIRATIONTIME)).signWith(SignatureAlgorithm.HS256, SECRET).compact();
    }
    
    public Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    }
    
    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }
    
    public Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }
    
    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }
}
