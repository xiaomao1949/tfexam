package com.tf.exam.model;

public class SecurityPosition {
	private String securityCode;
	private int originalPosition;
	private int currentPosition;
	
	public String getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}
	public int getOriginalPosition() {
		return originalPosition;
	}
	public void setOriginalPosition(int originalPosition) {
		this.originalPosition = originalPosition;
	}
	public int getCurrentPosition() {
		return currentPosition;
	}
	public void setCurrentPosition(int currentPosition) {
		this.currentPosition = currentPosition;
	}
	 
	public SecurityPosition(String securityCode, int originalPosition, int currentPosition) {
		super();
		this.securityCode = securityCode;
		this.originalPosition = originalPosition;
		this.currentPosition = currentPosition;
		 
	}
}
