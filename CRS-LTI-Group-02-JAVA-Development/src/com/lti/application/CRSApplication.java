/**
 * 
 */
package com.lti.application;

import java.util.List;
import java.util.Scanner;

import com.lti.bean.Admin;
import com.lti.bean.Professor;
import com.lti.bean.Student;
import com.lti.dao.StudentDaoImplementation;
import com.lti.dao.UserDaoImplementation;
import com.lti.service.UserService;

/**
 * 
 * @author Group-02
 * This class is used as the main entry point of the application
 * In main menu to login, register are displayed
 * 
 */
public class CRSApplication {

	/**
	 * Method to Create Main Menu
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		Scanner input = new Scanner(System.in);
		
		while(true) {
			System.out.println("+ + + + + + + + + + + + + + + + + + + + + + + + +");
			System.out.println("+      Welcome to CRS Application Group 02      +");
			System.out.println("+ + + + + + + + + + + + + + + + + + + + + + + + +\n");
			
			System.out.println("  1. Login");
			System.out.println("  2. Student Registration");
			System.out.println("  3. Forget Password");
			System.out.println("  4. Exit");
		
			System.out.print("\nChoose Option : ");
			int option = input.nextInt();
			
			UserService service = new UserService();
			UserDaoImplementation userdao = new UserDaoImplementation();
			StudentDaoImplementation studentdao = new StudentDaoImplementation();
			
			switch(option) {
			
				/**
				 * Method for Login functionality
				 * multiple exceptions are possible
				 * invalid credential exception
				 * user not found exception
				 * user not approved exception
				 */
				case 1: System.out.println("==================( Login Page )=================\n");
						@SuppressWarnings("rawtypes") 
						List userDetails = service.login();
						System.out.println("=================================================\n");
						
						Integer userType;
						try {
							String username = (String) userDetails.get(1);
							userType = (Integer) userDetails.get(3);
						}
						catch(Exception e) {
							userType = 4;
							break;
						}
						
						
						if(userType==4) break;
						switch(userType) {
							case 1: CRSMenuAdmin adminMenu = new CRSMenuAdmin();
									adminMenu.startup(userDetails);
									break;
							
							case 2: CRSMenuProfessor professorMenu = new CRSMenuProfessor();
									professorMenu.startup( (int) userDetails.get(0));
									break;
					
							case 3: CRSMenuStudent studentMenu = new CRSMenuStudent();
									studentMenu.startup(userDetails);
									break;
					
							default: System.out.println("");
						}
						break;
				
				
				/**
				 * Method to help Student register themselves, pending admin approval
				 */
				case 2: System.out.println("============( Student Registration Page )===========\n");
						System.out.println("");
						studentdao.newRegistration();
						System.out.println("\n\nStudent Registration Complete");
						System.out.println("Wait for Approval Email\n");
						System.out.println("=================================================\n");
						break;
					
				/**
				 * Method to reset password of User
				 */
				case 3: System.out.println("\n===============( Reset Password )===============\n");
						service.userUpdatePassword();
						System.out.println("=================================================\n");
						break;
						
				/**
				 * Exit from CRS Application
				 */
				case 4: System.out.println("=========( Exited from CRS Application )=========\n");
						break;
					
				/**
				 * Choosing options for main menu
				 */
				default: System.out.println("Choose option 1, 2, 3 or 4.");
			}
			
			if(option==4) break;			
		}
		input.close();
	}

}
