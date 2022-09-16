/**
 * 
 */
package com.lti.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.springframework.stereotype.Repository;

import com.lti.constant.SQLConstant;
import com.lti.exception.CourseApproved;
import com.lti.exception.DuplicateCourse;
import com.lti.exception.DuplicateProfessor;
import com.lti.exception.ExceptionPass;
import com.lti.exception.NoCourseInCatalogue;
import com.lti.exception.NoCourseInCourses;
import com.lti.exception.NoOpenCoursesForRegistration;
import com.lti.exception.NoProfessorToList;
import com.lti.exception.NoRegistration;
import com.lti.exception.NoStudentToList;
import com.lti.utils.DbUtils;


/**
 * 
 * @author Group-02
 * Class to implement Admin Dao Operations
 *
 */
@Repository
public class AdminDaoImplementation {

	Connection conn = null;

	/**
	 * Method to display Course Catalogue
	 * @param catalouge: Hashmap containing Course Catalogue List
	 * @throws NoOpenCoursesForRegistration
	 * @throws ExceptionPass
	 * @return displaying Course Catalogue List
	 */
	public Map<Integer,Integer> courseCatalog() throws ExceptionPass, NoOpenCoursesForRegistration {

		Map<Integer,Integer> catalouge = new HashMap<Integer,Integer>();

		PreparedStatement stmt = null;

		try{

			System.out.println("Connecting to database...");

			conn = DbUtils.getConnection();

			String sql = SQLConstant.SELECT_COURSES_PROF;

			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.isBeforeFirst())

			{    

				// Selecting courses based on course availability

				while(rs.next()){

					int courseid  = rs.getInt("CourseID");
					int profid = rs.getInt("proffesorId");

					catalouge.put(courseid, profid);


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

		return catalouge;

	}





	/**
	 * Method to display Professor List
	 * @param professors: Hashmap containing Professor List
	 * @throws NoProfessorToList
	 * @throws ExceptionPass
	 * @return displaying Professor List
	 */

	public Map<Integer, String> displayProfessorList() throws NoProfessorToList, ExceptionPass {

		Map<Integer, String> professors = new HashMap<Integer, String>();

		PreparedStatement stmt = null;

		try{

			System.out.println("Connecting to database...");

			conn = DbUtils.getConnection();

			String sql = SQLConstant.SELECT_PROFID_NAME;

			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.isBeforeFirst())

			{    

				// Selecting courses based on course availability

				while(rs.next()){

					int profid  = rs.getInt("ProfessorID");
					String profname = rs.getString("Name");

					professors.put(profid, profname);


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

			throw new NoProfessorToList();

		}

		finally{

			//finally block used to close resources

			try{

				if(stmt!=null)

					stmt.close();

			}catch(SQLException se2){

			}// nothing we can do

		}//end try

		return professors;

	}





	/**
	 * Method to display Student List
	 * @param students: Hashmap contaning Student List
	 * @throws NoStudentToList
	 * @throws ExceptionPass
	 * @return displaying Student List
	 */
	public Map<Integer, String> displayStudentList() throws NoStudentToList, ExceptionPass {

		Map<Integer, String> students = new HashMap<Integer, String>();

		PreparedStatement stmt = null;

		try{

			System.out.println("Connecting to database...");

			conn = DbUtils.getConnection();

			String sql = SQLConstant.SELECT_STUDENTID_NAME;

			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.isBeforeFirst())

			{    

				// Selecting courses based on course availability

				while(rs.next()){

					int sid  = rs.getInt("StudentID");
					String sname = rs.getString("Name");

					students.put(sid, sname);


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

			throw new NoStudentToList();

		}

		finally{

			//finally block used to close resources

			try{

				if(stmt!=null)

					stmt.close();

			}catch(SQLException se2){

			}// nothing we can do

		}//end try

		return students;

	}

	/**
	 * Method to display Course List
	 * @param availableCourseList: Hashmap contaning available Course List
	 * @throws NoOpenCoursesForRegistration
	 * @throws ExceptionPass
	 * return displaying available Course List
	 */

	public Map<Integer,String> courseAvailabilityCheck() throws ExceptionPass, NoOpenCoursesForRegistration {

		Map<Integer,String> availableCourseList = new HashMap<Integer,String>();

		PreparedStatement stmt = null;

		try{

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
	 * Method to Add Courses
	 * @param courseID: Course ID of Course to be added
	 * @param courseName: Name of Course to be added
	 * @param courseDesc: Description of Course to be added
	 * @param professorID: Professor assinged to Course added
	 * @param fees: Fees of the Course added
	 * return Courses are added in Course List and Course Catalouge
	 */

	public void addCourse(int courseID, String courseName, String courseDesc, int professorID, int fees) throws DuplicateCourse {

		PreparedStatement stmt = null;
		PreparedStatement stmt1 = null;	
		PreparedStatement stmt2 = null;	


		try{

			conn = DbUtils.getConnection();



			String sql = SQLConstant.INSERT_COURSE;
			String sql1 = SQLConstant.INSERT_CATALOUGE;
			stmt = conn.prepareStatement(sql);
			stmt1 = conn.prepareStatement(sql1);

			stmt.setInt(1, courseID);
			stmt.setString(2, courseName);
			stmt.setString(3, courseDesc);

			stmt1.setInt(1, courseID);
			stmt1.setInt(2, professorID);
			stmt1.setInt(3, fees);
			stmt1.setBoolean(4, true);

			int status = stmt.executeUpdate();
			int status1 = stmt1.executeUpdate();
			if(status > 0 && status1 > 0) {
				System.out.println("\nCourse added successfully !!!\n\n");
			}

			stmt.close();


		}
		catch(SQLException se){
			//Handle errors for JDBC
			//se.printStackTrace();
			throw new DuplicateCourse(courseID);
		}
		catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		finally{

			//finally block used to close resources
			try{
				if(stmt!=null) stmt.close();
			}
			catch(SQLException se2){
				// nothing we can do
			}   
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
	 * return Professor/s are registered
	 */

	public void addProfessor(int professorID, String username, String password, String name, String department, int age, String address, int mobileNumber, String gender, String role, String roleDesc) throws DuplicateProfessor {

		PreparedStatement stmt = null;
		PreparedStatement stmt1 = null;		
		PreparedStatement stmt2 = null;

		try{

			conn = DbUtils.getConnection();

			String sql = SQLConstant.INSERT_USER_A;
			String sql1 = SQLConstant.INSERT_ROLE_A;
			String sql2 = SQLConstant.INSERT_PROFESSOR_A;

			stmt = conn.prepareStatement(sql);
			stmt1 = conn.prepareStatement(sql1);
			stmt2 = conn.prepareStatement(sql2);

			stmt.setInt(1, professorID);
			stmt.setString(2, password);
			stmt.setString(3, username);

			stmt1.setInt(1, professorID);
			stmt1.setString(2, role);
			stmt1.setString(3, roleDesc);

			stmt2.setInt(1, professorID);
			stmt2.setString(2, name);
			stmt2.setString(3, department); 
			stmt2.setInt(4, age);
			stmt2.setString(5, address);
			stmt2.setInt(6, mobileNumber);
			stmt2.setString(7, gender);

			int status = stmt.executeUpdate();
			int status1 = stmt1.executeUpdate();
			int status2 = stmt2.executeUpdate();

			if(status > 0 && status1 > 0 && status2 > 0) {
				System.out.println("\n Professor added successfully !!!\n\n");
			}

			stmt.close();
			stmt1.close();
			stmt2.close();


		}
		catch(SQLException se){
			//Handle errors for JDBC
			//se.printStackTrace();
			throw new DuplicateProfessor(professorID);
		}
		catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		finally{

			//finally block used to close resources
			try{
				if(stmt!=null) stmt.close();
				if(stmt1!=null) stmt1.close();
				if(stmt2!=null) stmt2.close();
			}
			catch(SQLException se2){
				// nothing we can do
			}   
		}	
	}


	/**
	 * Method to Delete Courses
	 * @param courseID: Course id to be deleted
	 * @throws NoCourseInCourses
	 * return Courses are Deleted from Courses and Course Catalogue
	 */
	public void deleteCourse(int courseID) throws NoCourseInCourses {

		PreparedStatement stmt = null;	

		
		try{

			conn = DbUtils.getConnection();

			String sql = SQLConstant.SELECT_COURSES + courseID + "'" ;
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);


			if(rs.next()) {
				 sql = SQLConstant.DELETE_CATALOUGE + courseID + "'";
				//stmt = conn.prepareStatement(sql);
				stmt.executeUpdate(sql);


				sql = SQLConstant.DELETE_COURSES + courseID + "'";
				stmt.executeUpdate(sql);

				System.out.println("Course deleted successfully!!\n");
			}
			else {
				System.out.println("Record not found!!");
				throw new NoCourseInCourses(courseID);
			}		   

			stmt.close();


		}
		catch(SQLException se){
			//Handle errors for JDBC
				se.printStackTrace();
		//	throw new NoCourseInCourses(courseID);
		}
		catch(Exception e){
			//Handle errors for Class.forName
				//	e.printStackTrace();
			throw new NoCourseInCourses(courseID);
		}
		finally{

			//finally block used to close resources
			try{
				if(stmt!=null) stmt.close();
			}
			catch(SQLException se2){
				// nothing we can do
			}   

		}	
	}

	/**
	 * Method to Register Student for Courses
	 * @param approve: indicating if to register student
	 * @param uid: user id of Student 
	 * @param cid: course id for Student Course Registration
	 * @throws CourseApproved
	 * @throws ExceptionPass
	 * return Student are registered to the Courses
	 */

	public void courseApproval(int approve) throws CourseApproved, ExceptionPass {

		PreparedStatement stmt = null;	

		try{

			conn = DbUtils.getConnection();

			String sql = SQLConstant.SELECT_SEMESTER;
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);

			if(rs.isBeforeFirst()) {

				//				int approve = 0;
				//				Scanner input6 = new Scanner(System.in);

				System.out.println();
				while(rs.next()){
					int uid = rs.getInt("userID");
					int cid = rs.getInt("courseID");

					System.out.println(" Student: " + uid + " with Course: " + cid + "");
					System.out.println("\n  1: Approve     2: Don't Approve    3:  Go Back to Menu");
					System.out.print("\n  Choose any one: ");
					//					approve = input6.nextInt();

					if(approve == 1) {

						String sql1 = String.format(SQLConstant.UPDATE_SEMESTER, uid, cid);
						stmt = conn.prepareStatement(sql1);
						stmt.executeUpdate(sql1);

						System.out.println("\n  Course " + cid + " for student " + uid + "  approved Sucessfully \n");

					} else if (approve == 3) {
						break;
					} else if (approve == 2) {
						System.out.println("\n  Course " + cid + " for student " + uid + "  Not Approved \n");

					}
				}


				stmt.close();

			}
			else {
				throw new ExceptionPass();
			}

		}
		catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}
		catch(Exception e){
			//Handle errors for Class.forName
			//e.printStackTrace();
			throw new CourseApproved();
		}
		finally{

			//finally block used to close resources
			try{
				if(stmt!=null) stmt.close();
			}
			catch(SQLException se2){
				// nothing we can do
			}   
		}	
	}

	/**
	 * Method to Generate Report Card
	 * @param isAvailable: Yes/No input for generating report card
	 * return Student are Displayed Report Card
	 */

	public void generateReportCard(boolean isAvailable) {

		//		if(ans.toLowerCase() == "no") return;

		PreparedStatement stmt = null;

		try{

			conn = DbUtils.getConnection();


			// Let us select all the records and display them.
			//			String sql = SQLConstant.UPDATE_REPORTCARD_S1;
			//			stmt = conn.prepareStatement(sql);
			String sql = null;

			if(isAvailable) {
				sql = SQLConstant.UPDATE_REPORTCARD_S1;

			}
			else {
				sql = SQLConstant.UPDATE_REPORTCARD_S0;

			}
			stmt = conn.prepareStatement(sql);
			stmt.execute(sql);

			stmt.close();


		}
		catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}
		catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		finally{

			//finally block used to close resources
			try{
				if(stmt!=null) stmt.close();
			}
			catch(SQLException se2){
				// nothing we can do
			}   

		}	
	}


	/**
	 * Method to Register Student 
	 * @param approve: indicating approval or denial
	 * @param uid: userid of Student
	 * @param cid: name of Student
	 * @param newUserName: username of Student
	 * @param newPassword: password of Student
	 * @param newAddress: address of Student
	 * @param newGender: gender of Student
	 * @param newDepartment: department of Student
	 * @param newAge: age of Student
	 * @param newMobile: mobile number of Student
	 * @throws NoRegistration
	 * @throws ExceptionPass
	 * return Student are registered
	 */

	@SuppressWarnings("resource")
	public void studentRegister(int approve)  throws NoRegistration, ExceptionPass {
		// TODO Auto-generated method stub

		PreparedStatement stmt  = null;
		PreparedStatement stmt1 = null;	
		PreparedStatement stmt2 = null;
		PreparedStatement stmt3 = null;	
		PreparedStatement stmt4 = null;	

		try{

			conn = DbUtils.getConnection();

			String sql = SQLConstant.SELECT_REGISTRATION;
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.isBeforeFirst() ) {

				System.out.println();
				while(rs.next()){

					int uid = rs.getInt("RegistrationID");
					String cid = rs.getString("name");
					String newUserName   = rs.getString("username");
					String newPassword   = rs.getString("password");
					String newAddress    = rs.getString("address");
					String newGender     = rs.getString("gender");
					String newDepartment = rs.getString("department");
					int newAge           = rs.getInt("age");
					int newMobile        = rs.getInt("mobileNumber");

					System.out.println(" Student: " + cid + " with Student ID: " + uid + "");
					//					System.out.println("\n  1: Approve Registration     2: Don't Approve Registration   3:  Go Back to Menu");
					//					System.out.print("\n  Choose any one: ");
					//	//				approve = input6.nextInt();

					if(approve == 1) {

						String sql1 = String.format(SQLConstant.INSERT_USER_B, uid, newUserName, newPassword);

						String sql2 = String.format(SQLConstant.INSERT_STUDENT_A, uid, cid, newDepartment, newMobile, newAddress, newGender, newAge);

						String sql3 = String.format(SQLConstant.DELETE_REGISTRATION, uid);

						String sql4 = String.format(SQLConstant.INSERT_ROLE_B, uid); 

						stmt1 = conn.prepareStatement(sql1);
						stmt1.executeUpdate(sql1);

						stmt2 = conn.prepareStatement(sql2);
						stmt2.executeUpdate(sql2);

						stmt3 = conn.prepareStatement(sql3);
						stmt3.executeUpdate(sql3);

						stmt4 = conn.prepareStatement(sql4);
						stmt4.executeUpdate(sql4);

						System.out.println("\n  Student " + cid + " for student " + uid + "  Registered Sucessfully \n");

					} else if (approve == 3) {
						break;
					} else if (approve == 2) {
						System.out.println("\n  Course " + cid + " for student " + uid + "  Not Approved \n");

					}
				}

				stmt.close();

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
			throw new NoRegistration();
		}
		finally{

			//finally block used to close resources
			try{
				if(stmt!=null) stmt.close();
			}
			catch(SQLException se2){

			}	
		}
	}

}
