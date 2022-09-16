/**
 * 
 */
package com.lti.exception;

/**
 * @author Group 02
 * Exception to check if Grade Exist for Student
 *
 */
public class NoGradeToStudent extends Exception {
	
	private int studentID;
	
	/**
	 * Constructor
	 * @param sid: studentID
	 */
	public NoGradeToStudent(int sid)
	{
		this.studentID = sid;
	} 
	
	/**
	 * Getter method
	 * @return studentID
	 */
	public int getSid()
	{
		return studentID;
	}

}
