/**
 * 
 */
package com.lti.dao;


import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

import com.lti.constant.SQLConstant;
import com.lti.exception.ExceptionPass;
import com.lti.exception.NoCourseToStudent;
import com.lti.exception.NoGradeToStudent;
import com.lti.exception.NoOpenCoursesForRegistration;
import com.lti.ultis.DbUtils;

/**
 * 
 * @author Group-02
 * Class to implement Student Dao Operations
 *
 */
public class StudentDaoImplementation implements StudentDao {

	Connection conn = null;

	/**
	 * Method to check available courses
	 * @param courseid of the available course
	 * @param coursename of the available course
	 * @throws NoOpenCoursesForRegistration
	 * @throws ExceptionPass
	 * return displaying the available courses
	 */
	public void courseAvailabilityCheck() throws ExceptionPass, NoOpenCoursesForRegistration {

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

						System.out.println("Course ID: " + courseid + "  Course Name: " + coursename);
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

	}


	/**
	 * Method to display the report card
	 * @param isAvailable1: trigger variable for generating report card
	 * @param checkStudentID of the student
	 * @param courseid of the graded course
	 * @param grade1: grade of the student's course
	 * @throws NoGradeToStudent
	 * @throws ExceptionPass
	 * return displaying the report card for the student
	 */
	@SuppressWarnings("null")
	public void courseGradeCheck(int checkStudentID) throws ExceptionPass, NoGradeToStudent {


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
					return;
				}
			}
			else {
				System.out.println("No variable in database table for triggering report card generation!");
				return;
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
					System.out.println("Course ID: " + courseid + ", Course Grade: " + grade1);

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
	}


	/**
	 * Method to display the registered courses
	 * @param isApproved: Admin approved courses
	 * @param uid: student ID
	 * @param courseid of the registered course
	 * @param coursename of the registered course
	 * @throws NoCourseToStudent
	 * @throws ExceptionPass
	 * return displaying the registered courses for the student
	 */
	public void displayRegisteredCourse(int uid) throws ExceptionPass, NoCourseToStudent {


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
						} else {
							System.out.println("Course ID: " + courseid + "  Course Name: " + coursename + "  Course not Approved");
						}

					}// inner while
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

	}


	/**
	 * Method to add the available courses
	 * @param courseAvailable: Available courses in catalogue
	 * @param uid: student ID
	 * @param cid of the registered course
	 * @param sem: semester
	 * return adding the courses for the student
	 */
	public void addCourse(int uid, int cid, int sem) {

		PreparedStatement stmt = null;
		PreparedStatement stmt0 = null;
		PreparedStatement stmt1 = null;

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
						System.out.println("  Course addition Successful");						
					} 
					else {
						System.out.println("  Please Select only that Course which is Available ");
					} // inner inner else


				} else {	
					System.out.println("  Please Select Course only from Above List ");

				} // inner else

			} 

			else {
				System.out.println("   Course already added in semester registration cart!");
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

	}

	
	/**
	 * Method to remove courses
	 * @param uid: student ID
	 * @param cid of the registered course
	 * return removing the courses for the student
	 */
	public void removeCourse(int uid, int cid) {

		PreparedStatement stmt = null;
		PreparedStatement stmt0 = null;

		try{

			conn = DbUtils.getConnection();

			String sql0 = String.format(SQLConstant.SELECT_COURSEID_SEM_REGISTRATION_S, uid, cid);
			stmt0 = conn.prepareStatement(sql0);
			ResultSet rs0 = stmt0.executeQuery(sql0);

			if (rs0.isBeforeFirst()) {    

				String sql = String.format(SQLConstant.DELETE_SEMESTER_REGISTRATION_S, uid, cid);
				stmt = conn.prepareStatement(sql);
				stmt.executeUpdate(sql);
				System.out.println("  Course Removed Successful");		
			} 

			else {
				System.out.println("   Course not present in semester registration cart for deletion!");
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
	}

	
	/**
	 * Method to display the registered courses
	 * @param paymentDone: check variable for payment status
	 * @param uid: student ID
	 * @param courseid of the registered course
	 * @param fees: course fee
	 * @param totalfee: total fee for all registered courses
	 * @param numberofcourse: number of courses check for fee payment
	 * return adding the fees and returning total fee 
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
						totalfee = 0; 
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
	 * return payment details of the student
	 */
	public void paymentDetails(int uid, int totalFee) {


		PreparedStatement stmt = null;
		PreparedStatement stmt0 = null;
		PreparedStatement stmt1 = null;
		int doPayment = 0;
		Random rnd = new Random();
		int transactionID = rnd.nextInt(99999);
		String paymentMode = "";

		Scanner input2 = new Scanner(System.in);


		System.out.print("Do you want to do the Payment now (yes/no) : ");
		String check = input2.nextLine();

		if(check.contentEquals("yes")) {

			doPayment = 1;
			System.out.println("Please Choose Payment Mode");
			System.out.print("1: Credit Card   ");
			System.out.print(" 2: Debit Card   ");
			System.out.println(" 3: UPI  ");
			System.out.print("Choose anyone: ");
			paymentMode = input2.nextLine();

			System.out.println(" \n Payment Successful ");

		} else {
			System.out.println("Please complete the payment before Deadline");
		}

		try{
			conn = DbUtils.getConnection();

			String sql0 = String.format(SQLConstant.SELECT_PAYMENT_S, uid);
			stmt0 = conn.prepareStatement(sql0);
			ResultSet rs0 = stmt0.executeQuery(sql0);

			if (rs0.isBeforeFirst()) {    

				rs0.next();
				int paymentDone = rs0.getInt("paymentDone");

				if (paymentDone==0 & doPayment==0) {
					return;
				} else if (paymentDone==0 & doPayment==1) {

					String sql1 = String.format(SQLConstant.UPDATE_PAYMENT_DETAILS_S, doPayment, totalFee, doPayment*transactionID, paymentMode, uid);
					stmt1 = conn.prepareStatement(sql1);
					stmt1.executeUpdate(sql1);
					stmt1.close();

				} else {

					String sql = String.format(SQLConstant.INSERT_PAYMENT_DETAILS_S, uid, doPayment, totalFee, doPayment*transactionID, paymentMode);
					stmt = conn.prepareStatement(sql);
					stmt.executeUpdate(sql);
					stmt.close();

				}
			} else {
				String sql = String.format(SQLConstant.INSERT_PAYMENT_DETAILS_S, uid, doPayment, totalFee, doPayment*transactionID, paymentMode);
				stmt = conn.prepareStatement(sql);
				stmt.executeUpdate(sql);
				stmt.close();
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
	 * return registering the new student
	 */
	public void newRegistration() {

		PreparedStatement stmt = null;

		Scanner input3 = new Scanner(System.in);

		System.out.print("  Enter Student ID provided by College: ");
		int newID = input3.nextInt();
		input3.nextLine();

		System.out.print("  Enter Name: ");
		String newName = input3.nextLine();

		System.out.print("  Enter Address: ");
		String newAddress = input3.nextLine();

		System.out.print("  Gender: ");
		String newGender = input3.nextLine();

		System.out.print("  Age: ");
		int newAge = input3.nextInt();
		input3.nextLine();

		System.out.print("  Student Department: ");
		String newDepartment = input3.nextLine();

		System.out.print("  Mobile Number: ");
		int newMobile = input3.nextInt();
		input3.nextLine();

		System.out.print("  Choose Username: ");
		String newUsername = input3.nextLine();

		System.out.print("  Password: ");
		String newPassword = input3.nextLine();
		System.out.print("");

		try{

			conn = DbUtils.getConnection();

			String sql = String.format(SQLConstant.INSERT_REGISTRATION_DETAILS_S, newID, newName, newAddress, newGender, newAge, newDepartment, newMobile, newUsername, newPassword);
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate(sql);

			stmt.close();

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
	}

	/*

 	public static void main(String[] args) {
		// TODO Auto-generated method stub

 	}

	 */


}





