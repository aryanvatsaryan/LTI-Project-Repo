/**
 * 
 */
package com.lti.exception;

/**
 * @author Group 02
 * Exception to check student list
 *
 */
public class NoStudentToList extends Exception {

	/***
	 * Method to display Exception 
	 * return
	 */
	public void geterror() {
		System.out.println("No Student in Student List");
	}
}
