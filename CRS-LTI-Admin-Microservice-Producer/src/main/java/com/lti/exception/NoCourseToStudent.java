/**
 * 
 */
package com.lti.exception;

/**
 * @author Group 02
 * Exception to check if Course Exist for Student
 *
 */
public class NoCourseToStudent extends Exception {
	
	private int studentID;
	
	/**
	 * Constructor
	 * @param sid: studentID
	 */
	public NoCourseToStudent(int sid)
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
