/**
 * 
 */
package com.lti.exception;

/**
 * @author 10710166
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
