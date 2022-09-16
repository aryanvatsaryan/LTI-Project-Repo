/**
 * 
 */
package com.lti.service;
import java.util.Scanner;

import com.lti.dao.AdminDaoImplementation;
import com.lti.exception.CourseApproved;
import com.lti.exception.ExceptionPass;
import com.lti.exception.NoCourseInCatalogue;
import com.lti.exception.NoCourseInCourses;
import com.lti.exception.NoProfessorToList;
import com.lti.exception.NoRegistration;
import com.lti.exception.NoStudentToList;

/**
 * 
 * @author Group-02
 * Implementations of Admin Operations
 *
 */

public class AdminService extends UserService implements AdminInterface {
	Scanner input = new Scanner(System.in);
	AdminDaoImplementation adminDao = new AdminDaoImplementation();

	/**
	 * Method to display the Course List
	 * @throws NoCourseInCourses
	 * @throws ExceptionPass
	 */
	@Override
	public void courseList() {
		try {
			adminDao.displayCourseList();
		}catch(NoCourseInCourses e) {
			e.geterror();
		}
		catch(ExceptionPass e) {

		}
	}

	/**
	 * Method to Add Courses
	 * @param courseID: Course ID of Course to be added
	 * @param courseName: Name of Course to be added
	 * @param courseDesc: Description of Course to be added
	 * @param professorID: Professor assinged to Course added
	 * @param fees: Fees of the Course added
	 * @throws NoCourseInCatalogue
	 * @throws ExceptionPass
	 */
	@Override
	public void addCourse() {

		System.out.println("Current Course Catalogue: \n");
		try {
			adminDao.displayCourseCatalogue();
			System.out.println();
		}catch(NoCourseInCatalogue e) {
			e.geterror();
		} catch (ExceptionPass e) {

		}

		System.out.println("Current Course List: \n");
		courseList();
		System.out.println();

		System.out.println("Current Professor List: \n");
		professorList();
		System.out.println();	

		System.out.println("Give Following Information: \n");

		System.out.print("Course ID: ");
		int courseID = input.nextInt();

		System.out.print("Course Name: ");
		String courseName = input.next();
		input.nextLine();

		System.out.print("Course Description: ");
		String courseDesc = input.nextLine();

		System.out.print("ProfessorID: ");
		int professorID = input.nextInt();

		System.out.print("Course Fees: ");
		int fees = input.nextInt();

		adminDao.addCourse(courseID, courseName, courseDesc, professorID, fees);

	}

	/**
	 * Method to Delete Course
	 * @param courseID: Course id to be deleted
	 */
	@Override
	public void deleteCourse() {
		// TODO Auto-generated method stub
		System.out.println("Current Course List: \n");
		courseList();

		System.out.println("Enter course IDs for the courses to be deleted: \n");

		System.out.print("Course ID: ");
		int courseID = input.nextInt();

		adminDao.deleteCourse(courseID);

	}

	/**
	 * Method to display Course Catalogue
	 * @throws NoCourseInCatalogue
	 * @throws ExceptionPass
	 */
	//	@Override
	public void courseCatalogue() {
		// TODO Auto-generated method stub
		System.out.println("Current Course Catalogue: \n");
		try {
			adminDao.displayCourseCatalogue();
			System.out.println();
		}catch(NoCourseInCatalogue e) {
			e.geterror();
		} catch (ExceptionPass e) {

		}

		System.out.println("Current Course List: \n");
		courseList();
		System.out.println();

		System.out.println("Current Professor List: \n");
		professorList();
		System.out.println();

	}

	/**
	 * Method to display Professor List
	 * @throws NoProfessorToList
	 * @throws ExceptionPass
	 */
	@Override
	public void professorList() {
		// TODO Auto-generated method stub
		try {
			adminDao.displayProfessorList();
		}catch(NoProfessorToList e) {
			e.geterror();
		} catch (ExceptionPass e) {

		}
	}

	/**
	 * Method to Add Professor/s
	 * @param professorID: userid of Professor
	 * @param name: name of Professor
	 * @param userName: username of Professor
	 * @param password: password of Professor
	 * @param address: address of Professor
	 * @param gender: gender of Professor
	 * @param department: department of Professor
	 * @param age: age of Professor
	 * @param mobileNumber: mobile number of Professor
	 * @param role: role of Professor
	 * @param roleDesc: role description of Professor
	 */
	@Override
	public void addProfessor() {
		// TODO Auto-generated method stub
		System.out.println("Current Professor List: \n");
		professorList();
		System.out.println();	

		System.out.println("Give Following Information: \n");

		System.out.print("Professor ID: ");
		int professorID = input.nextInt();

		System.out.print("Username: ");
		String username = input.next();

		System.out.print("Password: ");
		String password = input.next();
		input.nextLine();

		System.out.print("Name: ");
		String name = input.nextLine();

		System.out.print("Department: ");
		String department = input.nextLine();

		System.out.print("Age: ");
		int age = input.nextInt();
		input.nextLine();

		System.out.print("Address: ");
		String address = input.nextLine();

		System.out.print("Mobile Number: ");
		int mobileNumber = input.nextInt();

		System.out.print("Gender: ");
		String gender = input.next();

		String role = "Professor";
		String roleDesc = "This is Professor.";

		adminDao.addProfessor(professorID, username, password, name, department, age, address, mobileNumber, gender, role, roleDesc);
	}

	/**
	 * Method to display Student List
	 * @throws NoStudentToList
	 * @throws ExceptionPass
	 */
	@Override
	public void studentList() {
		// TODO Auto-generated method stub
		try {
			adminDao.displayStudentList();

		}catch(NoStudentToList e) {
			e.geterror();
		} catch (ExceptionPass e) {

		}
	}

	/**
	 * Method to Generate Report Card
	 * @param ans: Yes/No input for generating report card
	 */
	@Override
	public void generateReportCards() {
		// TODO Auto-generated method stub
		System.out.print("You want to generate report cards (Yes/No): ");
		String ans = input.next();


		adminDao.generateReportCard(ans);

	}

	/**
	 * Method to Register Student
	 * @throws NoRegistration
	 * @throws ExceptionPass
	 */
	@Override
	public void registerStudent() {
		// TODO Auto-generated method stub
		try {
		adminDao.studentRegister();
		}catch(NoRegistration e) {
			e.geterror();
		}catch(ExceptionPass ex) {
			
		}
	}
	
	/**
	 * Method to Approve Student for Courses
	 * @throws CourseApproved
	 * @throws ExceptionPass
	 */
	@Override
	public void approveCourseRegistration() {
		// TODO Auto-generated method stub

		try {
			adminDao.courseApproval();
		}
		catch(CourseApproved e) {
			e.geterror();
		} catch (ExceptionPass e) {

		}
	}

}
