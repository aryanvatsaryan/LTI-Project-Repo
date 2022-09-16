/**
 * 
 */
package com.lti.exception;

/**
 * @author Group-02
 * Exception to check if no student is registered for the course
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
