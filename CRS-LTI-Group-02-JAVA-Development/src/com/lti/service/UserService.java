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
public class UserService implements UserInterface {
	
	UserDaoImplementation userDao = new UserDaoImplementation();
	Scanner input = new Scanner(System.in);

	/**
	 * Method for User to login into the system.
	 * @return List containing userID, username, password and userType
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List login() {

		// to return username and userType
		List userdetails = new ArrayList(4); 

		String username = "";
		Integer userType;
		String userTypeName = "";

		while(true) {
			System.out.println("User Type: ");
			System.out.println("  1. Admin");
			System.out.println("  2. Professor");
			System.out.println("  3. Student");

			System.out.print("\nChoose User Type: ");
			userType = input.nextInt();
			if(userType>=1 && userType<=3) break;
			System.out.println("Incorrect User Type.");
		} 

		if(userType == 1) {
			userTypeName = "Admin";
		}
		else if(userType == 2) {
			userTypeName = "Professor";
		}
		else {
			userTypeName = "Student";
		}

		while(true) {
			System.out.print("Enter Username: ");
			username = input.next();

			System.out.print("Enter Password: ");
			String password = input.next();
			
			String userName = "";
			String userPassword = "";
			
			try {	
				userdetails = userDao.logincheck(username, userTypeName);
				
				userName = (String) userdetails.get(1);
				userPassword = (String) userdetails.get(2);
				
				if(password.equals(userPassword)) {
					System.out.println("\nLogin Sucessful !!");
					break;
				} else {
					System.out.println("\nIncorrect password !!");
				}
			}
			catch(UserNotFoundException e) {
				System.out.println(e.getMessage()); 
				System.out.println("Kindly register before login.");
			}
			
			System.out.println("\nYou want to try again?");
			System.out.print("Enter (Yes/No) : ");
			String ans = input.next();
			if(ans.toLowerCase().equals("no")) {
				userType = 4; // Default value to Exit out from login page
				break;
			}
			System.out.println();
		}

		userdetails.add(userType);
		return userdetails;
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
	 * @return 
	 */
	public void userUpdatePassword() {
		
		System.out.print("Enter User ID: ");
		Integer userID = input.nextInt();
		input.nextLine();
		
		System.out.print("Enter Username: ");
		String username = input.nextLine();
		
		System.out.print("Enter New Password: ");
		String newPassword = input.nextLine();
		
		try {
			userDao.resetPassword(userID, username,  newPassword);
			System.out.println("\nPassword updated sucessfully !!");
		}
		catch(UserNotFoundException e) {
			System.out.println(e.getMessage());
			System.out.println("Kindly register before trying to update password :)");
		}
		
	}

}