/**
 * 
 */
package com.lti.application;

import java.util.List;
import java.util.Scanner;

import com.lti.bean.Student;
import com.lti.bean.User;
import com.lti.dao.StudentDaoImplementation;
import com.lti.service.StudentSerivce;

/**
 * 
 * @author Group-02
 * Class that display Student Client Menu
 * 
 */
public class CRSMenuStudent {
	
	/**
	 * Method to create Student menu
	 * @param studentID: user (student) id obtained after logging into the system
	 * @param studentUserName: username (student) is obtained after logging into the system
	 * returns displays all the options for the student, and provides navigation
	 */
	public void startup(List userDetails) {
		Scanner input = new Scanner(System.in);
		Integer studentID = (int) userDetails.get(0);
        String studentUserName = (String) userDetails.get(1);
		
		while(true) {
			
			System.out.println("+ + + + + + + + + + + + + + + + + + + + + + + + +");
			System.out.println("+   Welcome to " + userDetails.get(1) + " Menu   +");
			System.out.println("+ + + + + + + + + + + + + + + + + + + + + + + + +\n");
			
			System.out.println();
			
			// Display the options available for student
			System.out.println("  1: View Registered Course");
			System.out.println("  2: Add or Drop Course");
			System.out.println("  3: Request Course Catalogue");
			System.out.println("  4: View Report Card");
			System.out.println("  5: Fee Payment");
			System.out.println("  6: Logout");
			
			System.out.print("\nChoose Option : ");
			int option = input.nextInt();
			StudentSerivce service = new StudentSerivce();
			
			switch(option) {
				
				// Display registered courses for student
				case 1: System.out.println("\nCourse Registered for " + studentUserName + "  :");
						service.viewRegisteredCourses( (int) studentID); 
						break;
				
				// Add/Remove course/s for student
				case 2: System.out.println("\nOpening Add or Drop Course Menu");
						service.addDropCourse( (int) studentID); 
						break;
						
				// Display course catalogue
				case 3: System.out.println("Opening Course Catalouge Menu");
						service.displayAvailableCourses();
						break;
						
				// Display Report Card
				case 4: System.out.println("Opening View Report Card Menu");
						service.displayReportCard((int) studentID);
						break;
						
				// Payment Portal
				case 5: System.out.println("Opening Fee Payment Menu");
						service.feePayment( (int) studentID);
						break;
				
				// Logout
				case 6: System.out.println("Logout Successful !!");
						break;
						
				// Choose default option
				default: System.out.println("Choose optioin 1 to 6 only.");
			}
			
			System.out.println();
			if(option==6) break;						
		}
	}
}
