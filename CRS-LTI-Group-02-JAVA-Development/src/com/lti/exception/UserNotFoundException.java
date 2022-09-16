/**
 * 
 */
package com.lti.exception;

/**
 * @author Group 02
 * Exception to check if user exists 
 * @author 10710167
 *
 */
public class UserNotFoundException extends Exception {

//	private Integer userID;

	/***
	 * Getter function for UserId
	 * @param userID
	 */
	public UserNotFoundException() {
//		this.userID = userID;
	}

	/**
	 * Message thrown by exception
	 * @return 
	 */
	@Override
	public String getMessage() {
		return "\nUser not found !!";
	}

}