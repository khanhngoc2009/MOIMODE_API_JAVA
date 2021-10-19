package com.it15306.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class OptionProductKey  implements Serializable{
	
	@Column(name = "option_id")
    Integer option_id;

    @Column(name = "product_id")
    Integer product_id;
}
