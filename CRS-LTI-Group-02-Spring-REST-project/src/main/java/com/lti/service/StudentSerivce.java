/**
 * 
 */
package com.lti.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.bean.Payment;
import com.lti.bean.semesterRegistration;
import com.lti.dao.ProfessorDaoImplementation;
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
@Service
public class StudentSerivce extends UserService implements StudentInterface{

	@Autowired
	private StudentDaoImplementation student;

	//StudentDaoImplementation studentservice = new StudentDaoImplementation();

	/**
	 * Method to display the available courses
	 * @throws NoOpenCoursesForRegistration
	 * @throws ExceptionPass
	 * @return Map<Integer,String>: Returning available courses hashmap
	 */
	public Map<Integer,String> displayAvailableCourses() {

		Map<Integer,String> availableCourseList = new HashMap();

		try {
			availableCourseList = student.courseAvailabilityCheck();
			//System.out.println("try works");

		}
		catch(NoOpenCoursesForRegistration e) {
			//System.out.println("catch works");
			System.out.println("   No Courses open for enrollment based on availability!");
		}
		catch(ExceptionPass e) {

		}

		return availableCourseList;
	}


	/**
	 * Method to display the available courses
	 * @param studentID: student ID
	 * @throws NoGradeToStudent
	 * @throws ExceptionPass
	 * @return Map<Integer,String>: Returning report card hashmap
	 */
	public Map<Integer,String> displayReportCard(int studentID) {

		Map<Integer,String> gradeSheet = new HashMap();

		try {
			gradeSheet = student.courseGradeCheck(studentID);
			//System.out.println("try works");

		}
		catch(NoGradeToStudent e) {
			//System.out.println("catch works");
			
			System.out.println("\n Grades are not available now for student ID: " + e.getSid() + "Please check later!");
			gradeSheet.put(0, "Grades are not available now for student ID: " + studentID + " Please check later!");
		}
		catch(ExceptionPass e) {

		}

		return gradeSheet;
	}

	/**
	 * Method to display the registered courses
	 * @param studentID: student ID
	 * @throws NoCourseToStudent
	 * @throws ExceptionPass
	 * @return Map<Integer,List>: Returning registerd courses for a student hashmap
	 */
	public Map<Integer, List> viewRegisteredCourses(int studentID) {

		Map<Integer,List> registeredCourseList = new HashMap<Integer,List>();

		try {
			registeredCourseList = student.displayRegisteredCourse(studentID);
			//System.out.println("try works");

		}
		catch(NoCourseToStudent e) {
			//System.out.println("catch works");
			System.out.println("\n  No courses are registered for student ID: " + e.getSid());
			System.out.println("  Make Sure to Register Courses before Deadline");
		}
		catch(ExceptionPass e) {

		}

		return registeredCourseList;
	}

	/**
	 * Method to add courses for the student
	 * @param studentID: student ID
	 * @throws NoOpenCoursesForRegistration
	 * @throws ExceptionPass
	 * @return boolean: Whether the course is added or not
	 */
	@Override
	public String addStudentCourse(semesterRegistration studentCourseRegistration) {	

		//Scanner input = new Scanner(System.in);

		Map<Integer,String> availableCourseList = new HashMap();

		boolean openCoursFlag = false;
		String checkFlag = "";
		System.out.println("        Courses available: ");
		System.out.println("");

		try {
			availableCourseList = student.courseAvailabilityCheck();
			openCoursFlag = true;
			//System.out.println("try works");

		}
		catch(NoOpenCoursesForRegistration e) {
			//System.out.println("catch works");
			System.out.println("   No Courses open for enrollment based on availability!");
		}
		catch(ExceptionPass e) {

		}

		if(!openCoursFlag) {
			return "No Courses open for enrollment based on availability!";
		}

		else {
			//Scanner input1 = new Scanner(System.in);
			//System.out.println("      Which course you want to add");
			//System.out.print("      Enter Course ID: ");
			int courseID = studentCourseRegistration.getCourseID();
			int studentID = studentCourseRegistration.getUserID();
			//System.out.print("\n      Enter Semester: ");
			int sem = studentCourseRegistration.getSemester();
			//System.out.println("");
			checkFlag = student.addCourse(studentID, courseID, sem);
			//System.out.println("  Course addition Successful");
		}

		return checkFlag;

	}


	/**
	 * Method to remove courses for the student
	 * @param studentID: student ID
	 * @throws NoOpenCoursesForRegistration
	 * @throws ExceptionPass
	 * @return boolean: When courses for a student has dropped or not
	 */
	@Override
	public String dropStudentCourse(semesterRegistration studentCourseRegistration) {	

		//Scanner input = new Scanner(System.in);

		Map<Integer,List> registeredCourseList = new HashMap<Integer,List>();

		boolean dropCourseFlag = false;
		String checkFlag = "";
		int courseID = studentCourseRegistration.getCourseID();
		int studentID = studentCourseRegistration.getUserID();
		System.out.println("        Courses available: ");
		System.out.println("");

		try {

			registeredCourseList = student.displayRegisteredCourse(studentID);
			for (int courseid : registeredCourseList.keySet()) 
				if(courseid == courseID) {
					dropCourseFlag = true;
				}
			//System.out.println("try works");

		}
		catch(NoCourseToStudent e) {
			//System.out.println("catch works");
			System.out.println("Course ID " + courseID + " not registered for student ID: " + studentID);
		}
		catch(ExceptionPass e) {

		}

		if(!dropCourseFlag) {
			return "Course ID " + courseID + " not registered for student ID: " + studentID;
		}

		else {

			System.out.println("      Which course you want to Remove");
			System.out.print("      Enter Course ID:  ");
			//Scanner input2 = new Scanner(System.in);
			//int courseID = input2.nextInt();

			System.out.println("");
			checkFlag = student.removeCourse(studentID, courseID);	
		}

		return checkFlag;

	}


	/**
	 * Method to do payment for the student
	 * @param studentID: student ID
	 * @return boolean: When fee payment for student is done or not
	 */
	public String studentFeePayment(Payment feePayment) {
		System.out.println(" \n\n         Fee Payment Page\n");

		int studentID = feePayment.getStudentID();
		String modeOfPayment = feePayment.getModeOfPayment();

		int totalFee = student.calculateFee(studentID);
		
		System.out.println(totalFee);

		String paymentCompletedFlag = "";

		if(totalFee!=0) {

			paymentCompletedFlag = student.paymentDetails(studentID, totalFee, modeOfPayment);
			return paymentCompletedFlag;

		}
		else if(totalFee == 1) {
			return "Payment already done!";
		}
		
		if(paymentCompletedFlag.equals("")) {
			return "Check if you have registered for 4 courses are approved or not!";
		}
		return paymentCompletedFlag;
	}

}


