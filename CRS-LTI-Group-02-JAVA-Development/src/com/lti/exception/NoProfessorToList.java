/**
 * 
 */
package com.lti.exception;

/**
 * @author Group 02
 * Exception to check professor list
 *
 */
public class NoProfessorToList extends Exception {

	/***
	 * Method to display Exception 
	 * return
	 */
	public void geterror() {
		System.out.println("No Professor in Professor List");
	}
}
