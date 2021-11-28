package com.it15306.dto.dashboard;

public class TongHopDonHang {
	private Integer choXacNhan;
	private Integer daXacNhan;
	private Integer dangVanChuyen;
	private Integer hoanThanh;
	private Integer khachHuy;
	private Integer cuaHangHuy;
	/**
	 * @return the choXacNhan
	 */
	public Integer getChoXacNhan() {
		return choXacNhan;
	}
	/**
	 * @param choXacNhan the choXacNhan to set
	 */
	public void setChoXacNhan(Integer choXacNhan) {
		this.choXacNhan = choXacNhan;
	}
	/**
	 * @return the daXacNhan
	 */
	public Integer getDaXacNhan() {
		return daXacNhan;
	}
	/**
	 * @param daXacNhan the daXacNhan to set
	 */
	public void setDaXacNhan(Integer daXacNhan) {
		this.daXacNhan = daXacNhan;
	}
	/**
	 * @return the dangVanChuyen
	 */
	public Integer getDangVanChuyen() {
		return dangVanChuyen;
	}
	/**
	 * @param dangVanChuyen the dangVanChuyen to set
	 */
	public void setDangVanChuyen(Integer dangVanChuyen) {
		this.dangVanChuyen = dangVanChuyen;
	}
	/**
	 * @return the hoanThanh
	 */
	public Integer getHoanThanh() {
		return hoanThanh;
	}
	/**
	 * @param hoanThanh the hoanThanh to set
	 */
	public void setHoanThanh(Integer hoanThanh) {
		this.hoanThanh = hoanThanh;
	}
	/**
	 * @return the khachHuy
	 */
	public Integer getKhachHuy() {
		return khachHuy;
	}
	/**
	 * @param khachHuy the khachHuy to set
	 */
	public void setKhachHuy(Integer khachHuy) {
		this.khachHuy = khachHuy;
	}
	/**
	 * @return the cuaHangHuy
	 */
	public Integer getCuaHangHuy() {
		return cuaHangHuy;
	}
	/**
	 * @param cuaHangHuy the cuaHangHuy to set
	 */
	public void setCuaHangHuy(Integer cuaHangHuy) {
		this.cuaHangHuy = cuaHangHuy;
	}
	
	
}
//-- + status: 1 // Chưa xác nhận
//-- + status: 2 // Đã xác nhận
//-- + status: 3 // Đang vận chuyển
//-- + status: 4 // Hoàn thành
//-- + Status: 5 // Huỷ (Từ khách)
//-- + Status: 6 // Từ chối (từ cửa hàng)