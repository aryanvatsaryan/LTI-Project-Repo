/**
 * 
 */
package com.lti.application;

import java.util.Scanner;
import com.lti.dao.ProfessorDaoImplementation;
import com.lti.service.ProfessorService;

/**
 * 
 * @author Group-02
 * Class that display Professor Client Menu
 * 
 */
public class CRSMenuProfessor {


	ProfessorDaoImplementation professor = new ProfessorDaoImplementation();

	/**
	 * Method to create Professor menu
	 * @param userid: user (professor) id obtained after logging into the system
	 * returns displays all the options for the professor, and provides navigation
	 */
	public void startup(int userid) {

		Scanner input = new Scanner(System.in);
		while(true) {
			System.out.println("+ + + + + + + + + + + + + + + + + + + + + + + + +");
			System.out.println("+           Welcome to Professor Menu           +");
			System.out.println("+ + + + + + + + + + + + + + + + + + + + + + + + +\n");

			professor.professordetails(userid);

			//Display the options available for the Professor
			System.out.println("  1: Display My Course List");
			System.out.println("  2: Display Enrolled Students");
			System.out.println("  3: Allocate Grades for Courses");
			System.out.println("  4: Logout");

			System.out.print("\nChoose Option : ");
			int option = input.nextInt();

			ProfessorService service = new ProfessorService();

			switch(option) {

			// Display all courses for professor
			case 1: 
				System.out.println("----------------------------------------------------------------------");
				System.out.println("My Course List: \n");
				service.displayMyCourses(userid);
				break;

				// Display enrolled students coursewise
			case 2: 
				System.out.println("----------------------------------------------------------------------");
				System.out.println("Coursewise Enrolled Student List: \n");
				service.viewEnrolledStudents(userid);
				break;

				// Add Grade/s to student/s coursewise
			case 3: 
				System.out.println("----------------------------------------------------------------------");
				System.out.println("Coursewise Grade Allocation for Students: \n");
				service.registerGrades(userid);
				break;

				// Logging out
			case 4: System.out.println("Logout Successful !!");
			break;

			// Choose default option
			default: System.out.println("Choose optioin 1 to 4 only.");
			}

			System.out.println();
			if(option==4) break;						
		}	

	}		

}			
