/**
 * 
 */
package com.lti.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.lti.dao.StudentDaoImplementation;
import com.lti.dao.UserDaoImplementation;
import com.lti.exception.ExceptionPass;
import com.lti.exception.NoCourseToProfessor;
import com.lti.exception.NoCourseToStudent;
import com.lti.exception.NoGradeToStudent;
import com.lti.exception.NoOpenCoursesForRegistration;

/**
 * 
 * @author Group-02
 * Implementations of Student Operations
 *
 */
public class StudentSerivce extends UserService implements StudentInterface{

	StudentDaoImplementation studentservice = new StudentDaoImplementation();

	/**
	 * Method to display the available courses
	 * @throws NoOpenCoursesForRegistration
	 * @throws ExceptionPass
	 */
	public void displayAvailableCourses() {

		try {
			studentservice.courseAvailabilityCheck();
			//System.out.println("try works");

		}
		catch(NoOpenCoursesForRegistration e) {
			//System.out.println("catch works");
			System.out.println("   No Courses open for enrollment based on availability!");
		}
		catch(ExceptionPass e) {

		}
	}


	/**
	 * Method to display the available courses
	 * @param studentID: student ID
	 * @throws NoGradeToStudent
	 * @throws ExceptionPass
	 */
	public void displayReportCard(int studentID) {

		try {
			studentservice.courseGradeCheck(studentID);
			//System.out.println("try works");

		}
		catch(NoGradeToStudent e) {
			//System.out.println("catch works");
			System.out.println("\n Grades are not available now for student ID: " + e.getSid() + "Please check later!");
		}
		catch(ExceptionPass e) {

		}

	}

	/**
	 * Method to display the registered courses
	 * @param studentID: student ID
	 * @throws NoCourseToStudent
	 * @throws ExceptionPass
	 */
	public void viewRegisteredCourses(int studentID) {

		try {
			studentservice.displayRegisteredCourse(studentID);
			//System.out.println("try works");

		}
		catch(NoCourseToStudent e) {
			//System.out.println("catch works");
			System.out.println("\n  No courses are registered for student ID: " + e.getSid());
			System.out.println("  Make Sure to Register Courses before Deadline");
		}
		catch(ExceptionPass e) {

		}
	}

	/**
	 * Method to add/remove courses for the student
	 * @param studentID: student ID
	 * @throws NoOpenCoursesForRegistration
	 * @throws ExceptionPass
	 */
	@Override
	public void addDropCourse(int studentID) {	

		Scanner input = new Scanner(System.in);

		while(true) {

			System.out.println("\n Currently Registered Courses: ");
			viewRegisteredCourses(studentID);
			System.out.println("-------------------------------------------------------------------------------------------");
			System.out.println("                                    ADD or Remove");	

			System.out.print("   1: Add         ");
			System.out.print("2: Remove         ");
			System.out.println("3: Go Back");
			System.out.println("");
			System.out.print("    Choose any one: ");

			int i = input.nextInt();
			System.out.println("");

			switch(i) {


			case 1:
				boolean courseOpenFlag = false;
				System.out.println("        Courses available: ");
				System.out.println("");

				try {
					studentservice.courseAvailabilityCheck();
					courseOpenFlag = true;
					//System.out.println("try works");

				}
				catch(NoOpenCoursesForRegistration e) {
					//System.out.println("catch works");
					System.out.println("   No Courses open for enrollment based on availability!");
				}
				catch(ExceptionPass e) {

				}

				if(!courseOpenFlag) {
					break;
				}

				else {
					Scanner input1 = new Scanner(System.in);
					System.out.println("      Which course you want to add");
					System.out.print("      Enter Course ID: ");
					int addcourse = input1.nextInt();
					System.out.print("\n      Enter Semester: ");
					int sem = input1.nextInt();
					System.out.println("");
					studentservice.addCourse(studentID, addcourse, sem);	
					System.out.println("  Course addition Successful");
				}
				break;

			case 2: 
				System.out.println("      Which course you want to Remove");
				System.out.print("      Enter Course ID:  ");
				Scanner input2 = new Scanner(System.in);
				int removecourse = input2.nextInt();
				System.out.println("");
				studentservice.removeCourse(studentID, removecourse);	
				System.out.println("  Course Removed Successful");
				break;

			case 3: break;
			}
			if (i==3) {
				break;
			}		

		}

	}


	public void feePayment(int studentID) {
		System.out.println(" \n\n         Fee Payment Page\n");

		int totalFee = studentservice.calculateFee(studentID);

		if(totalFee!=0) {

			studentservice.paymentDetails(studentID, totalFee);
		}
	}

}


