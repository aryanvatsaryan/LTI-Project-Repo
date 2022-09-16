package com.lti.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.bean.Grade;
import com.lti.dao.ProfessorDaoImplementation;
import com.lti.exception.ExceptionPass;
import com.lti.exception.GradeAlreadyMarked;
import com.lti.exception.NoCourseToProfessor;
import com.lti.exception.NoStudentToCourse;

/**
 * 
 * @author Group-02
 * Implementations of Professor Operations
 *
 */
@Service
public class ProfessorService extends UserService implements ProfessorInterface {

	@Autowired
	private ProfessorDaoImplementation professor;

	/**
	 * Method to display the courses
	 * @param userid: Professor ID
	 * @param Map<Integer,String>: required output courseId, courseName
	 * @throws NoCourseToProfessor
	 * @throws ExceptionPass
	 * @return Map<Integer,String>: CourseIDs and Course names
	 */
	@Override
	public Map<Integer,String> displayMyCourses(int userid) {

		Map<Integer,String> clist = new HashMap<Integer,String>(); 

		try {
			clist = professor.professorCourses(userid);
			//System.out.println("try works");

		}
		catch(NoCourseToProfessor e) {
			//System.out.println("catch works");
			clist.put(1, "No course Available for Professor ID:" + userid);
			System.out.println("\n  No course Available for Professor ID: " + e.getPid());
		}
		catch(ExceptionPass e) {

		}
		return clist;

	}



	/**
	 * Method to display the enrolled students
	 * @param userid: Professor ID
	 * @param Map<Integer, List<Integer>>: required output CourseId, List of students registered
	 * @throws NoCourseToProfessor
	 * @throws ExceptionPass
	 * @return Map<Integer, List<Integer>>: CourseId, List of students registered
	 */
	@Override
	public Map<Integer, List<Integer>> viewEnrolledStudents(int userid) {

		Map<Integer, List<Integer>> courseStudentList = new HashMap<Integer, List<Integer>>();
		try {
			courseStudentList = professor.studentList(userid);
		}
		catch(NoCourseToProfessor e) {
			//System.out.println("catch works");
			System.out.println("\n  No course Available for Professor ID: " + e.getPid());
		}
		catch(ExceptionPass e) {

		}
		return courseStudentList;
	}





	/**
	 * Method to register grades for students
	 * @param userid: Professor ID
	 * @param courseid: Course ID
	 * @param sid: Student ID
	 * @param grade: Grade marked for Student
	 * @throws NoCourseToProfessor
	 * @throws NoStudentToCourse
	 * @throws ExceptionPass
	 * @return String: Status of Marking Grade
	 */
	//so1-> switch option 1
	@Override
	public String registerGrades(Grade grades) {

		int courseid = grades.getCourseid();
		int sid      = grades.getStudentid();
		String grade = grades.getGrade();

		String check = "";

		try {
			professor.addgrade(courseid, sid, grade);
			check = "Grade Marked for stident: " + sid;

		}
		catch(GradeAlreadyMarked e) {
			//System.out.println("catch works");
			check = "Grade already marked for studentID: " + sid;
		}
		catch(ExceptionPass e) {

		}


		return check;
	}


}

