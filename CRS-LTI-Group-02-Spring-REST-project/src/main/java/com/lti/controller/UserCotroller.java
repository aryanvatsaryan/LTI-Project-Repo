/**
 * 
 */
package com.lti.controller;

import java.util.List;
import java.util.Scanner;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.bean.Registration;
import com.lti.bean.User;
import com.lti.dao.StudentDaoImplementation;
import com.lti.dao.UserDaoImplementation;
import com.lti.service.UserService;

/**
 * 
 * @author Group-02
 * User Controller Class
 * 
 */
@RestController
public class UserCotroller {
	
	@Autowired
	private UserService userService;
	@Autowired
	private StudentDaoImplementation studentDao;
	
	/**
     * Method to handle API request for login
     * @param currentUser
     * @returns boolean if login is successful
     */
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.POST, value = "/login")
	public ResponseEntity<Object> login(@RequestBody User currentUser) {
		
		return new ResponseEntity<>(userService.login(currentUser), HttpStatus.OK);
		
	}
	
	/**
     * Method to handle API request for Student Registration
     * @param newRegistration
     * @returns boolean if student registration is successful
     */
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.POST , value = "/studentRegistration")
	public ResponseEntity<Object> studentRegistration(@RequestBody Registration newRegistration) {
		
		return new ResponseEntity<>(studentDao.newRegistration(newRegistration), HttpStatus.OK);
	}
	
	/**
     * Method to handle API request for Updating Password
     * @param currentUser
     * @returns boolean if Updating Password is successful
     */
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.PUT, value = "/forgetPassword")
	public ResponseEntity<Object> forgetPassword(@RequestBody User currentUser) {
		
		return new ResponseEntity<>(userService.userUpdatePassword(currentUser), HttpStatus.OK);
	}
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/logout")
	@ResponseBody
	public ResponseEntity<Object> logout() {
		return new ResponseEntity<>("Logging Out to CRS Application ", HttpStatus.OK);
	}
	
	
}
