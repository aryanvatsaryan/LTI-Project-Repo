/**
 * 
 */
package com.lti.exception;

/**
 * @author Group 02
 * Exception to check if courses are available in course list
 *
 */
public class NoCourseInCourses extends Exception {

	private int courseid ;

	/**
	 * Constructor
	 * @param cid: courseid
	 */
	public NoCourseInCourses(int cid)
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
