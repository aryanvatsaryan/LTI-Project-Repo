/**
 * 
 */
package com.lti.service;

import java.util.Map;

import com.lti.bean.Catalogue;
import com.lti.bean.Professor;
import com.lti.bean.Student;
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
 * Implementations of Admin Service
 *
 */
public interface AdminInterface {
	
	public Map<Integer,String> courseList() throws NoOpenCoursesForRegistration, ExceptionPass; 
	public String addCourse(Catalogue courseCatalogue) throws DuplicateCourse;
	public String deleteCourse(int courseID) throws NoCourseInCourses;
	
	public Map<Integer,Integer> courseCatalogue();
	public Map<Integer, String> professorList() throws NoProfessorToList, ExceptionPass;
	public String addProfessor(Professor professor) throws DuplicateProfessor;
	
	public Map<Integer, String> studentList() throws NoStudentToList, ExceptionPass;
	public void generateReportCards(boolean isAvailable);

	public boolean approveCourseRegistration(int approve) throws CourseApproved, ExceptionPass;
	public String registerStudent(int id)throws NoRegistration, ExceptionPass;
}	

