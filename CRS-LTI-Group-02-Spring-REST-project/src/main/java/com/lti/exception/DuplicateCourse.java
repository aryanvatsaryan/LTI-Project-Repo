/**
 * 
 */
package com.lti.exception;

/**
 * @author Group 02
 * Exception to check if duplicate course is present
 *
 */
public class DuplicateCourse extends Exception {

	private int courseid ;

	/**
	 * Constructor
	 * @param cid: courseid
	 */
	public DuplicateCourse(int cid)
	{
		this.courseid = cid;
	} 

	/**
	 * Getter method
	 * @return courseid
	 */
	public int getCid()
	{
		return courseid;
	}
}
