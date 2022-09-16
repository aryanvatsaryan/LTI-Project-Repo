/**
 * 
 */
package com.lti.service;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.bean.Catalogue;
import com.lti.bean.Professor;
import com.lti.bean.Student;
import com.lti.dao.AdminDaoImplementation;
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

/**
 * 
 * @author Group-02
 * Implementations of Admin Operations
 *
 */

@Service
public class AdminService extends UserService implements AdminInterface {
	Scanner input = new Scanner(System.in);

	@Autowired
	private AdminDaoImplementation adminDao;

	
	/**
	 * Method to display the Course List
	 * @param availableCourseList: List containing course list
	 * @throws NoCourseInCourses
	 * @throws ExceptionPass
	 * @return Available Course List
	 */
	@Override
	public Map<Integer,String> courseList() {
		Map<Integer,String> availableCourseList = new HashMap<Integer,String>();

		try {
			availableCourseList = adminDao.courseAvailabilityCheck();
		}
		catch(NoOpenCoursesForRegistration e) {
			System.out.println("   No Courses open for enrollment based on availability!");
		}
		catch(ExceptionPass e) {

		}

		return availableCourseList;
	}





	/**
	 * Method to Add Courses
	 * @param courseCatalogue
	 * @param courseID: Course ID of Course to be added
	 * @param courseName: Name of Course to be added
	 * @param courseDesc: Description of Course to be added
	 * @param professorID: Professor assinged to Course added
	 * @param fees: Fees of the Course added
	 * @throws DuplicateCourse
	 * @return check to check addition of course
	 */
	@Override
	public String addCourse(Catalogue courseCatalogue) {
		String check="";

		int courseID = courseCatalogue.getCourseId();
		String courseName = courseCatalogue.getCourseName();
		String courseDesc = courseCatalogue.getCourseDescription();
		int professorID = courseCatalogue.getProfessorId();
		int fees = courseCatalogue.getFees();

		try {
			adminDao.addCourse(courseID, courseName, courseDesc, professorID, fees);
			check = "Course Added Sucessfully.";
		}
		catch(DuplicateCourse e) {
			check = "Course already present for id:"+ e.getCid();
		}

		return check;
	}

	/**
	 * Method to Delete Course
	 * @param courseID: Course id to be deleted
	 * @throws NoCourseInCourses
	 * @return check to check if course is deleted
	 */
	@Override
	public String deleteCourse(int courseID) {
		String check = "";
		try {
			adminDao.deleteCourse(courseID);
			check = "Course Deleted Sucessfully";
		}catch(NoCourseInCourses e) {
			check = "No such Course with course id :"+e.getCid()+" in Course List";
		}
		return check;
	}

	/**
	 * Method to display Course Catalogue
	 * @param catalouge
	 * @throws NoCourseInCatalogue
	 * @throws ExceptionPass
	 * @return Catalogue List
	 */
	//	@Override
	public Map<Integer,Integer> courseCatalogue() {

		Map<Integer,Integer> catalouge = new HashMap<Integer,Integer>();

		try {

			catalouge = adminDao.courseCatalog();

			//System.out.println("try works");

		}

		catch(NoOpenCoursesForRegistration e) {

			//System.out.println("catch works");

			System.out.println("   No Courses available in Catalouge");

		}

		catch(ExceptionPass e) {

		}

		return catalouge;

	}

	/**
	 * Method to display Professor List
	 * @param profList
	 * @throws NoProfessorToList
	 * @throws ExceptionPass
	 * @return List of Professor
	 */
	@Override
	public Map<Integer, String> professorList() {


		Map<Integer, String> profList = new HashMap<Integer, String>();

		try {

			profList = adminDao.displayProfessorList();

			//System.out.println("try works");

		}

		catch(NoProfessorToList e) {

			//System.out.println("catch works");

			System.out.println("   No Professor Available");

		}

		catch(ExceptionPass e) {

		}

		return profList;

	}

	/**
	 * Method to Add Professor/s
	 * @param professor
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
	 * @return check if professor is added to professor list
	 */
	@Override
	public String addProfessor(Professor professor) {
		// TODO Auto-generated method stub
		
		String check = "";
		
		int professorID = professor.getUserId();
		String username = professor.getUsername();
		String password = professor.getPassword();
		String name = professor.getName();
		String department = professor.getDepartment();
		int age = professor.getAge();
		String address = professor.getAddress();
		int mobileNumber = professor.getMobileNumber();
		String gender = professor.getGender();
		
		String role = "Professor";
		String roleDesc = "This is Professor.";

		try {
			adminDao.addProfessor(professorID, username, password, name, department, age, address, mobileNumber, gender, role, roleDesc);
			check = "Professor Added Sucessfully";
		} catch (DuplicateProfessor e) {
			check = "Professor of ProfessorID: "+e.getPid()+" already present!";
		}
		
		return check;
	}

	/**
	 * Method to display Student List
	 * @param studentlist
	 * @throws NoStudentToList
	 * @throws ExceptionPass
	 * @return Student List
	 */
	@Override
	public Map<Integer, String> studentList() {


		Map<Integer, String> studentlist = new HashMap<Integer, String>();

		try {

			studentlist = adminDao.displayStudentList();
		}

		catch(NoStudentToList e) {

			System.out.println("   No Student Available");

		}

		catch(ExceptionPass e) {

		}

		return studentlist;

	}

	/**
	 * Method to Generate Report Card
	 * @param isAvailable: Yes/No input for generating report card
	 * return student can display report card
	 */
	@Override
	public void generateReportCards(boolean isAvailable) {
//		System.out.print("You want to generate report cards (Yes/No): ");
//		String ans = input.next();
		adminDao.generateReportCard(isAvailable);
	}

	/**
	 * Method to Register Student
	 * @param id: input indicating approval or denial of registration
	 * @throws NoRegistration
	 * @throws ExceptionPass
	 * @return check if student is registered
	 */
	@Override
	public String registerStudent(int id) {
		String check = "";
		
		try {
			adminDao.studentRegister(id);
			check = "Registrations Approved";
		}catch(NoRegistration e) {
			e.geterror();
			check = "No registeration Pending";
		}catch(ExceptionPass ex) {

		}
		return check;
	}

	/**
	 * Method to Approve Student for Courses
	 * @param approve: input indicating approval or denial
	 * @throws CourseApproved
	 * @throws ExceptionPass
	 * @return check if course registration is approved
	 */
	@Override
	public boolean approveCourseRegistration(int approve) {
		boolean check = false;
		try {
			adminDao.courseApproval(approve);
			check = true;//this is to indicate that above method is called
		}
		catch(CourseApproved e) {
			e.geterror();
		} catch (ExceptionPass e) {

		}
		return check;
	}

}
