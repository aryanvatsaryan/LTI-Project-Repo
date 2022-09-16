/**
 * 
 */
package com.lti.exception;

/**
 * @author Group 02
 * Exception to check Registration available for courses
 *
 */
public class NoOpenCoursesForRegistration extends Exception {

	/***
	 * Method to display Exception 
	 * return error message
	 */
	public void geterror() {
		System.out.println("No Courses for Registration");
	}
}
