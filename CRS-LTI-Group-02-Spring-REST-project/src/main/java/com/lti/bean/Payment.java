/**
 * 
 */
package com.lti.bean;

/**
 * @author 10710167
 *
 */
public class Payment {
	
	private int studentID;
	private int paymentDone;
	private float toatalFees;
	private String transactionID;
	private String modeOfPayment;
	
	public int getStudentID() {
		return studentID;
	}
	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}
	public int getPaymentDone() {
		return paymentDone;
	}
	public void setPaymentDone(int paymentDone) {
		this.paymentDone = paymentDone;
	}
	public float getToatalFees() {
		return toatalFees;
	}
	public void setToatalFees(float toatalFees) {
		this.toatalFees = toatalFees;
	}
	public String getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}
	public String getModeOfPayment() {
		return modeOfPayment;
	}
	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}
	
	
		
	
}	
