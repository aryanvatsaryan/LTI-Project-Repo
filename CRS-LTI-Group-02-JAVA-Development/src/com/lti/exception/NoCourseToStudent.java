/**
 * 
 */
package com.lti.exception;

/**
 * @author 10710166
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
