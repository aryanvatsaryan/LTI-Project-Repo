/**
 * 
 */
package com.lti.exception;

/**
 * Exception to check if no student is registered for the course
 * @author Group-02
 *
 */
public class NoStudentToCourse extends Exception {
	
	private int courseID;
	
	/**
	 * Constructor
	 * @param cid: CourseID
	 */
	public NoStudentToCourse(int cid)
	{
		this.courseID = cid;
	} 
	
	/**
	 * Getter method
	 * @return courseID
	 */
	public int getCid()
	{
		return courseID;
	}

}
