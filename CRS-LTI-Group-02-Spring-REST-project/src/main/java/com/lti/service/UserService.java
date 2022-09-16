package com.lti.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.bean.Admin;
import com.lti.bean.Course;
import com.lti.bean.Professor;
import com.lti.bean.Student;
import com.lti.bean.User;
import com.lti.dao.UserDaoImplementation;
import com.lti.exception.UserNotFoundException;

/**
 * @author Group 02
 * Implementations of User Services
 */
@Service
public class UserService implements UserInterface {
	@Autowired
	private UserDaoImplementation userDao;

	Scanner input = new Scanner(System.in);

	/**
	 * Method for User to login into the system.
	 * @param user
	 * @param password: password of user
	 * @param username: username of user
	 * @param role: role of user
	 * @param check: boolean value 
	 * @throws UserNotFoundException
	 * @return check for successful login
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String login(User user) {

		String check="";

		String password = user.getPassword();
		String username = user.getUsername();
		String role = user.getRole();


		List userdetails = new ArrayList(4); 

		String userName = "";
		String userPassword = "";

		try {	
			userdetails = userDao.logincheck(username, role);

			userName = (String) userdetails.get(1);
			userPassword = (String) userdetails.get(2);

			if(password.equals(userPassword)) {
				System.out.println("\nLogin Sucessful !!");
				check="Login Sucessfull!!";
			} else {
				check="Incorrect Password!!";
			}
		}
		catch(UserNotFoundException e) {
			System.out.println(e.getMessage()); 
			System.out.println("Kindly register before login.");
			check="Kindly register before login.";
		}
		
		return check;
	}


	/**
	 * Method for User to logout the system.
	 * @return 
	 */
	public void logout() {
		System.out.println("\nLogout successful !!");
	}



	/**
	 * Method for User to update the password.
	 * @param user
	 * @param userID: user id of user
	 * @param username: username of user
	 * @param newPassword: password of user
	 * @param check: boolean value 
	 * @throws UserNotFoundException
	 * @return  check if successful method execution
	 */
	public String userUpdatePassword(User user) {

		String check="";
		int userID = user.getUserid();
		String username = user.getUsername();
		String newPassword = user.getPassword();

		try {
			userDao.resetPassword(userID, username,  newPassword);
			System.out.println("\nPassword updated sucessfully !!");
			check = "Password updated sucessfully !!";
		}
		catch(UserNotFoundException e) {
			System.out.println(e.getMessage());
			System.out.println("Kindly register before trying to update password.");
			check = "Kindly register before trying to update password.";
		}

		return check;

	}

}