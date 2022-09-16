/**
 * 
 */
package com.lti.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lti.constant.SQLConstant;
import com.lti.exception.ExceptionPass;
import com.lti.exception.GradeAlreadyMarked;
import com.lti.exception.NoCourseToProfessor;
import com.lti.utils.DbUtils;

/**
 * 
 * @author Group-02
 * Class to implement Professor Dao Operations
 *
 */
@Repository
public class ProfessorDaoImplementation {



	Connection conn = null;

	/**
	 * Method to display professor details
	 * @param userid of the professor
	 * return displaying professor details
	 */
	public void professordetails(int userid) {

		PreparedStatement stmt = null;

		try {
			conn = DbUtils.getConnection();

			String sql = SQLConstant.SELECT_PROFESSOR_P+ userid;
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()){

				int pid  = rs.getInt("ProfessorID");
				String name1 = rs.getString("Name");
				String department1 = rs.getString("Department");

				System.out.println("ID   		: " + pid);
				System.out.println("Name		: " + name1);
				System.out.println("Department	: " + department1);
				System.out.println();

			}
			//stmt.close();
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if(stmt!=null) stmt.close();
			}
			catch(SQLException se2){

			}
		}
		return;
	}






	/**
	 * Method to display courses alloted to professor
	 * @param userid: Professor Id
	 * @throws NoCourseToProfessor
	 * @throws ExceptionPass
	 * @return Map<Integer,String>: displaying courseId, CourseName alloted to professor
	 */
	public Map<Integer,String> professorCourses(int userid) throws NoCourseToProfessor, ExceptionPass {

		Map<Integer,String> courselist = new HashMap<Integer,String>(); 

		PreparedStatement stmt = null;

		try{

			conn = DbUtils.getConnection();

			String sql1 = SQLConstant.SELECT_CATALOGUE_P + userid;
			stmt = conn.prepareStatement(sql1);
			ResultSet rs1 = stmt.executeQuery(sql1);

			if(rs1.isBeforeFirst() ) {

				// Selecting courses based on courseID from courses table
				while(rs1.next()) {

					int cid1 =rs1.getInt("courseID");
					String sql = SQLConstant.SELECT_COURSE_P +cid1;
					stmt = conn.prepareStatement(sql);
					ResultSet rs = stmt.executeQuery(sql);

					// Displaying courses alloted for professor
					while(rs.next()){
						int cid = rs.getInt("courseID");
						String cname = rs.getString("courseName");

						courselist.put(cid,cname);

					}
				} 

			} else {
				throw new ExceptionPass(); 
			}
		}
		catch(SQLException se){
			//se.printStackTrace();
			throw new ExceptionPass(); 				
		}
		catch(Exception e){
			//e.printStackTrace();
			throw new NoCourseToProfessor(userid); 
		}

		finally {
			try {
				if(stmt!=null) stmt.close();
			}
			catch(SQLException se2){
			}
		}
		return courselist;
	}






	/**
	 * Method to display enrolled students for courses alloted to professor
	 * @param userid: Professor ID
	 * @param cid: (courseID) alloted for the professor
	 * @param sid: (studentID) of the student alloted under a course 
	 * @throws NoCourseToProfessor
	 * @throws ExceptionPass
	 * @return Map<Integer, List<Integer>>: Students enrolled for courses alloted to professor
	 */
	public Map<Integer, List<Integer>> studentList(int userid) throws NoCourseToProfessor, ExceptionPass {


		Map<Integer,List<Integer>> studentcourselist = new HashMap<Integer,List<Integer>>(); 

		PreparedStatement stmt = null;

		try{


			conn = DbUtils.getConnection();

			String sql = SQLConstant.SELECT_CATALOGUE_P + userid;
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);

			if(rs.isBeforeFirst() ) {


				int i=1;

				// Display enrolled students under each courseID
				while(rs.next()){
					List<Integer> studentlist = new ArrayList<Integer>();

					int cid = rs.getInt("courseID");
					System.out.println("======================================================================");
					System.out.println( i++ +"|| CoureseId : "+cid );

					sql = SQLConstant.SELECT_SEMESTER_P + cid;
					stmt = conn.prepareStatement(sql);
					ResultSet rs2 = stmt.executeQuery(sql);

					if(rs2.isBeforeFirst() ) {


						while(rs2.next()) {
							int sid = rs2.getInt("userID");
							System.out.println("     StudentId : "+sid );

							studentlist.add(sid);

						}
						studentcourselist.put(cid,studentlist);
					}
					else {

						System.out.println("\n  No Student/s Available for courseID: " + cid);
					}
				}
			}
			else {
				throw new ExceptionPass(); 
			}

		}
		catch(SQLException se){
			//se.printStackTrace();
			throw new ExceptionPass(); 	
		}
		catch(Exception e){
			//e.printStackTrace();
			throw new NoCourseToProfessor(userid);
		}
		finally {
			try {
				if(stmt!=null) stmt.close();
			}
			catch(SQLException se2){

			}
		}
		return studentcourselist;
	}



	/**
	 * Method to grade students for courseID alloted to professor
	 * @param grade: Grade of student for course
	 * @param courseid: courseID alloted for the professor
	 * @param sid: studentID of the student alloted under a course
	 * @throws GradeAlreadyMarked
	 * @throws ExceptionPass
	 * return Grading and displaying grades for students
	 */
	public void addgrade(int courseid,int sid,String grade) throws GradeAlreadyMarked, ExceptionPass {

		PreparedStatement stmt = null;
		try {

			System.out.println("Connection to database...");
			conn = DbUtils.getConnection();
			String sql;        
			sql= SQLConstant.SELECT_GRADE_P + sid + " AND courseID=" + courseid;
			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);
			//value available
			if (rs.isBeforeFirst()) {   
				throw new ExceptionPass();
			}
			else {
				stmt.close();
				sql = SQLConstant.INSERT_GRADE_P;
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, sid);
				stmt.setInt(2, courseid);
				stmt.setString(3, grade);
				stmt.executeUpdate();
				System.out.println("----------------------------------------------------------------------");
				System.out.println("        Grade addition Successful");
			}
			//stmt.close();

		}
		catch(SQLException se){
			//se.printStackTrace();
			throw new ExceptionPass();
		}
		catch(Exception e){
			//e.printStackTrace();
			throw new GradeAlreadyMarked(sid);
		}
		finally{
			try{
				if(stmt!=null) stmt.close();
			}
			catch(SQLException se2){
			}
		}
	}
}