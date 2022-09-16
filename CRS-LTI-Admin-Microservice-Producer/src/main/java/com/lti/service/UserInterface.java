/**
 * 
 */
package com.lti.service;

import java.util.List;

import com.lti.bean.User;

/**
 * 
 * @author Group-02
 * Implementations of User Service
 *
 */
public interface UserInterface {
	
	@SuppressWarnings("rawtypes")
	public String login(User user);
	
	
	public void logout();
	
	public String userUpdatePassword(User user);
	//public void userRegistration();
	
	//public void userUpdatePassword();
	
	//public void requestCourseCatalogue();
	
	
}	
