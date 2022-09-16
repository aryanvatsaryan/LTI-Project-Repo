/**
 * 
 */
package com.lti.exception;

/**
 * @author Group 02
 * Exception to check if courses are available in course catalogue
 *
 */
public class NoCourseInCatalogue extends Exception {

	/***
	 * Method to display Exception 
	 * return
	 */
	public void geterror() {
		System.out.println("No courses in the Catalogue");
	}
}
