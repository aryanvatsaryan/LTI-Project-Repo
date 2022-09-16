/**
 * 
 */
package com.lti.dao;


import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import org.springframework.stereotype.Repository;

import com.lti.bean.Registration;
import com.lti.constant.SQLConstant;
import com.lti.exception.ExceptionPass;
import com.lti.exception.NoCourseToStudent;
import com.lti.exception.NoGradeToStudent;
import com.lti.exception.NoOpenCoursesForRegistration;
import com.lti.utils.DbUtils;

/**
 * 
 * @author Group-02
 * Class to implement Student Dao Operations
 *
 */
@Repository
public class StudentDaoImplementation implements StudentDao {

	Connection conn = null;

	/**
	 * Method to check available courses
	 * @param courseid of the available course
	 * @param coursename of the available course
	 * @throws NoOpenCoursesForRegistration
	 * @throws ExceptionPass
	 * @return Map<Integer,String>: Displaying the available courses
	 */
	public Map<Integer,String> courseAvailabilityCheck() throws ExceptionPass, NoOpenCoursesForRegistration {

		Map<Integer,String> availableCourseList = new HashMap(); 

		PreparedStatement stmt = null;

		try{

			System.out.println("Connecting to database...");
			conn = DbUtils.getConnection();

			String sql = SQLConstant.SELECT_COURSEID_AVAILABLE_S;
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);


			if (rs.isBeforeFirst()) 
			{    

				// Selecting courses based on course availability
				while(rs.next()){

					int courseid  = rs.getInt("CourseID");
					String sql1 = String.format(SQLConstant.SELECT_COURSENAME_S,courseid);
					stmt = conn.prepareStatement(sql1);
					ResultSet rs1 = stmt.executeQuery(sql1);

					while(rs1.next()){

						String coursename  = rs1.getString("courseName");  

						//System.out.println("Course ID: " + courseid + "  Course Name: " + coursename);
						availableCourseList.put(courseid, coursename);
					}

				}
			}

			else {
				throw new ExceptionPass();
			}
			//stmt.close();
		}
		catch(SQLException se){
			//Handle errors for JDBC
			//se.printStackTrace();
			throw new ExceptionPass();
		}
		catch(Exception e){
			//Handle errors for Class.forName
			//e.printStackTrace();
			throw new NoOpenCoursesForRegistration();
		}
		finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
		}//end try

		return availableCourseList;

	}


	/**
	 * Method to display the report card
	 * @param isAvailable1: trigger variable for generating report card
	 * @param checkStudentID of the student
	 * @param courseid of the graded course
	 * @param grade1: grade of the student's course
	 * @throws NoGradeToStudent
	 * @throws ExceptionPass
	 * @return Map<Integer,String>: Displaying the report card for the student
	 */
	@SuppressWarnings("null")
	public Map<Integer,String> courseGradeCheck(int checkStudentID) throws ExceptionPass, NoGradeToStudent {

		Map<Integer,String> gradeSheet = new HashMap(); 

		PreparedStatement stmt = null;
		PreparedStatement stmt1 = null;

		try{

			System.out.println("Getting Grade for " + checkStudentID + " ...");
			conn = DbUtils.getConnection();



			String sql1= SQLConstant.SELECT_REPORTCARD_AVAILABLE_S;
			stmt1 = conn.prepareStatement(sql1);
			ResultSet rs1 = stmt1.executeQuery(sql1);

			if(rs1.isBeforeFirst()) {

				rs1.next();
				int isAvailable1 = rs1.getInt("isAvailable");

				if (isAvailable1 == 0 ) {
					System.out.println("   Report Is not Generated Yet,  Please Check Later ");
					gradeSheet.put(null, null);
					return gradeSheet;
				}
			}
			else {
				System.out.println("No variable in database table for triggering report card generation!");
				gradeSheet.put(null, null);
				return gradeSheet;
			}

			String sql = String.format(SQLConstant.SELECT_GRADE_S, checkStudentID);
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.isBeforeFirst()) 
			{    
				while(rs.next()){


					//Retrieve by column name
					int courseid  = rs.getInt("CourseID");  
					String grade1 = rs.getString("grade");


					// Printing and display the elements in ArrayList
					//System.out.println("Course ID: " + courseid + ", Course Grade: " + grade1);

					gradeSheet.put(courseid, grade1);

				}

			}
			else {
				throw new ExceptionPass();
			}
		}
		catch(SQLException se){
			//Handle errors for JDBC
			//se.printStackTrace();
			throw new ExceptionPass();
		}
		catch(Exception e){
			//Handle errors for Class.forName
			//e.printStackTrace();
			throw new NoGradeToStudent(checkStudentID);
		}
		finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
				if(stmt1!=null)
					stmt1.close();
			}catch(SQLException se2){
			}// nothing we can do
		}//end try

		return gradeSheet;
	}


	/**
	 * Method to display the registered courses
	 * @param isApproved: Admin approved courses
	 * @param uid: student ID
	 * @param courseid of the registered course
	 * @param coursename of the registered course
	 * @throws NoCourseToStudent
	 * @throws ExceptionPass
	 * @return Map<Integer, List>: Displaying the registered courses for the student
	 */
	public Map<Integer, List> displayRegisteredCourse(int uid) throws ExceptionPass, NoCourseToStudent {

		Map<Integer,List> registeredCourseList = new HashMap<Integer,List>();

		PreparedStatement stmt = null;
		//PreparedStatement stmt1 = null;

		try{

			conn = DbUtils.getConnection();

			String sql = String.format(SQLConstant.SELECT_COURSEID_APPROVED_S, uid);
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.isBeforeFirst()) {   

				// Selecting course name for the approved course
				while(rs.next()){

					List courseInfoList = new ArrayList();

					//Retrieve by column name
					int courseid  = rs.getInt("courseID");
					int isApproved = rs.getInt("isApproved");
					String sql1 = String.format(SQLConstant.SELECT_COURSENAME_S,courseid);
					stmt = conn.prepareStatement(sql1);
					ResultSet rs1 = stmt.executeQuery(sql1);

					while(rs1.next()){

						//Retrieve by column name
						String coursename  = rs1.getString("courseName");  

						if(isApproved==1) {
							System.out.println("Course ID: " + courseid + "  Course Name: " + coursename + "  Course Approved");
							courseInfoList.add(coursename);
							String flag = "Course Approved";
							courseInfoList.add(flag);
						} else {
							System.out.println("Course ID: " + courseid + "  Course Name: " + coursename + "  Course not Approved");
							courseInfoList.add(coursename);
							String flag = "Course Not Approved";
							courseInfoList.add(flag);
						}

					}// inner while
					registeredCourseList.put(courseid, courseInfoList);

				}// outer while
			} 
			else {

				throw new ExceptionPass();

				//System.out.println("   You have not registered for any courses");
				//System.out.println("  Make Sure to Register Courses before Deadline"); 

			} 

		} // try wala
		catch(SQLException se){
			//Handle errors for JDBC
			//se.printStackTrace();
			throw new ExceptionPass();
		}
		catch(Exception e){
			//Handle errors for Class.forName
			//e.printStackTrace();
			throw new NoCourseToStudent(uid);
		}
		finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
		}//end try

		return registeredCourseList;

	}


	/**
	 * Method to add the available courses
	 * @param courseAvailable: Available courses in catalogue
	 * @param uid: student ID
	 * @param cid of the registered course
	 * @param sem: semester
	 * @return boolean: Adding the courses for the student
	 */
	public String addCourse(int uid, int cid, int sem) {


		PreparedStatement stmt = null;
		PreparedStatement stmt0 = null;
		PreparedStatement stmt1 = null;

		String addCourseFlag = "";

		try{

			conn = DbUtils.getConnection();

			String sql0 = String.format(SQLConstant.SELECT_COURSEID_SEM_REGISTRATION_S, uid, cid);
			stmt0 = conn.prepareStatement(sql0);
			ResultSet rs0 = stmt0.executeQuery(sql0);

			if (!rs0.isBeforeFirst()) {    

				String sql1 = String.format(SQLConstant.SELECT_CATALOGUE_COURSEAVAILABILITY_S, cid);
				stmt1 = conn.prepareStatement(sql1);
				ResultSet rs1 = stmt1.executeQuery(sql1);

				if (rs1.isBeforeFirst()) {

					rs1.next();
					int courseAvailable = rs1.getInt("CourseAvalibility");

					if (courseAvailable==1) {

						String sql = String.format(SQLConstant.INSERT_SEMESTER_REGISTRATION_S, uid, cid, sem);
						stmt = conn.prepareStatement(sql);
						stmt.executeUpdate(sql);
						addCourseFlag = "Course addition Successful";	
					} 
					else {
						addCourseFlag = "Please Select only that Course which is Available";
					} // inner inner else


				} else {	
					addCourseFlag = "Please Select Course only from Above List";

				} // inner else

			} 

			else {
				addCourseFlag = "Course already added in semester registration cart!";
			}// outer else
		}// try

		catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt0!=null)
					stmt0.close();
				if(stmt1!=null)
					stmt1.close();
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
		}//end try

		return addCourseFlag;

	}


	/**
	 * Method to remove courses
	 * @param uid: student ID
	 * @param cid of the registered course
	 * @return boolean: Removing the courses for the student
	 */
	public String removeCourse(int uid, int cid) {

		PreparedStatement stmt = null;
		PreparedStatement stmt0 = null;

		String dropCourseFlag = "";

		try{

			conn = DbUtils.getConnection();

			String sql0 = String.format(SQLConstant.SELECT_COURSEID_SEM_REGISTRATION_S, uid, cid);
			stmt0 = conn.prepareStatement(sql0);
			ResultSet rs0 = stmt0.executeQuery(sql0);

			if (rs0.isBeforeFirst()) {    

				String sql = String.format(SQLConstant.DELETE_SEMESTER_REGISTRATION_S, uid, cid);
				stmt = conn.prepareStatement(sql);
				stmt.executeUpdate(sql);
				dropCourseFlag = "Course Removed Successful";		
			} 

			else {
				dropCourseFlag = "Course not present in semester registration cart for deletion!";
				//stmt.close();
			}

		} 
		catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt0!=null)
					stmt0.close();
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
		}//end try

		return dropCourseFlag;
	}


	/**
	 * Method to calculate the total fees of the student 
	 * @param paymentDone: check variable for payment status
	 * @param uid: student ID
	 * @param courseid of the registered course
	 * @param fees: course fee
	 * @param totalfee: total fee for all registered courses
	 * @param numberofcourse: number of courses check for fee payment
	 * @return int: Adding the fees and returning total fee 
	 */
	public int calculateFee(int uid) {


		PreparedStatement stmt0 = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt1 = null;
		int totalfee = 0;
		int numberofcourse = 0;

		try{

			conn = DbUtils.getConnection();

			String sql = String.format(SQLConstant.SELECT_APPROVED_COURSEID_SEM_REGISTRATION_S, uid);
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.isBeforeFirst()) 
			{    
				while(rs.next()){

					//Retrieve by column name
					int courseid  = rs.getInt("courseID");
					numberofcourse++;
					String sql1 = String.format(SQLConstant.SELECT_FEES_S,courseid);
					stmt1 = conn.prepareStatement(sql1);
					ResultSet rs1 = stmt1.executeQuery(sql1);

					while(rs1.next()){

						//Retrieve by column name
						int fees  = rs1.getInt("fees");  
						totalfee = totalfee + fees;

						System.out.println("Course ID: " + courseid + "  Course Fee: " + fees);
					}
				}

			}
			else {
				System.out.println("   No approved courses found for payment!");
			}

		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
				if(stmt1!=null)
					stmt1.close();
			}catch(SQLException se2){
			}// nothing we can do
		}//end try


		if(numberofcourse==4) {

			System.out.println("You have registered for 4 courses");

			System.out.println("\n  Total Fee to be paid:  " + totalfee);

			try {

				conn = DbUtils.getConnection();

				String sql0 = String.format(SQLConstant.SELECT_PAYMENT_S, uid);
				stmt0 = conn.prepareStatement(sql0);
				ResultSet rs0 = stmt0.executeQuery(sql0);

				if (rs0.isBeforeFirst()) {    

					rs0.next();
					int paymentDone = rs0.getInt("paymentDone");

					if (paymentDone==1) {
						System.out.println("   Payment already done!");
						totalfee = 1; 
					}
					else {
						System.out.println("Please Proceed for the Payment");
					}
				} 
				else {
					System.out.println("Please Proceed for the Payment");
				}

			}
			catch(Exception e) {
			}

			return totalfee;

		} else if (numberofcourse < 4) {

			System.out.println("You have registered less than 4 courses");
			System.out.println("Please Register atleast 4 Courses ");
			return 0;
		} else {

			System.out.println("You have registered more than 4 courses");
			System.out.println("Please Register atmost 4 Courses ");
			return 0;

		}	 


	}

	/**
	 * Method to display the registered courses
	 * @param doPayment: check variable for student to do the payment or not
	 * @param uid: student ID
	 * @param rnd: Random class object for creating random transactionID
	 * @param transactionID: student transaction ID
	 * @param totalfee: total fee for all registered courses
	 * @param numberofcourse: number of courses check for fee payment
	 * @return boolean: Payment details of the student
	 */
	public String paymentDetails(int uid, int totalFee, String modeOfPayment) {


		PreparedStatement stmt = null;
		PreparedStatement stmt0 = null;
		//int doPayment = 0;
		Random rnd = new Random();
		int transactionID = rnd.nextInt(99999);

		String paymentDoneFlag = "";
		
		System.out.println(totalFee);

		try{
			conn = DbUtils.getConnection();

			String sql0 = String.format(SQLConstant.SELECT_PAYMENT_S, uid);
			stmt0 = conn.prepareStatement(sql0);
			ResultSet rs0 = stmt0.executeQuery(sql0);

			if (rs0.isBeforeFirst()) {    

				rs0.next();
				int paymentDone = rs0.getInt("paymentDone");

				if (paymentDone==1) {
					return "Payment Already Done!";

				} 
			}

			else {
				int doPayment = 1;
				String sql = String.format(SQLConstant.INSERT_PAYMENT_DETAILS_S, uid, doPayment, totalFee, transactionID, modeOfPayment);
				stmt = conn.prepareStatement(sql);
				stmt.executeUpdate(sql);
				stmt.close();
				paymentDoneFlag = "Payment Successfully Done!";

			} 
		}


		catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
		}//end try

		return paymentDoneFlag;

	}


	/**
	 * Method to register new student 
	 * @param newID: Takes student ID input for new student registered 
	 * @param newName: Takes student name input for new student registered 
	 * @param newAddress: Takes student name input for new student registered
	 * @param newGender: Takes student gender input for new student registered 
	 * @param newAge: Takes student age input for new student registered 
	 * @param newDepartment: Takes student department input for new student registered 
	 * @param newMobile: Takes student mobile number input for new student registered 
	 * @param newUsername: Takes student user name input for new student registered 
	 * @param newPassword: Takes student password input for new student registered 
	 * @return boolean: Registering the new student
	 */
	public String newRegistration(Registration newStudent) {

		PreparedStatement stmt = null;
		PreparedStatement stmt1 = null;
		String check = "";


		////		Scanner input3 = new Scanner(System.in);
		//
		//		System.out.print("  Enter Student ID provided by College: ");
		int newID = newStudent.getUserId();
		////		input3.nextLine();
		//
		//		System.out.print("  Enter Name: ");
		String newName = newStudent.getName();
		//
		//		System.out.print("  Enter Address: ");
		String newAddress = newStudent.getAddress();
		//
		//		System.out.print("  Gender: ");
		String newGender = newStudent.getGender();
		//
		//		System.out.print("  Age: ");
		int newAge = newStudent.getAge();
		//		input3.nextLine();
		//
		//		System.out.print("  Student Department: ");
		String newDepartment = newStudent.getDepartment();
		//
		//		System.out.print("  Mobile Number: ");
		int newMobile = newStudent.getMobileNumber();
		//		input3.nextLine();
		//
		//		System.out.print("  Choose Username: ");
		String newUsername = newStudent.getUsername();

		String newPassword = newStudent.getPassword();


		try{
			conn = DbUtils.getConnection();

			String sql1 = String.format(SQLConstant.SELECT_REGISTRATION_DETAILS_S, newID);
			stmt1 = conn.prepareStatement(sql1);
			ResultSet rs1 = stmt1.executeQuery(sql1);
			if(rs1.isBeforeFirst()) {
				return "Registration already done!";
			}



			String sql = String.format(SQLConstant.INSERT_REGISTRATION_DETAILS_S, newID, newName, newAddress, newGender, newAge, newDepartment, newMobile, newUsername, newPassword);
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate(sql);
			
			check = "Registration successfully done!";


			stmt.close();
			stmt1.close();
			//check = true;

		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do

		}//end try


		/*

 	public static void main(String[] args) {
		// TODO Auto-generated method stub

 	}

		 */

		return check;

	}
}





