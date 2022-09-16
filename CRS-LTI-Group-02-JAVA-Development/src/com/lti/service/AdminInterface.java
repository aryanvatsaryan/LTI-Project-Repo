/**
 * 
 */
package com.lti.service;

import com.lti.exception.CourseApproved;
import com.lti.exception.ExceptionPass;
import com.lti.exception.NoCourseInCatalogue;
import com.lti.exception.NoCourseInCourses;
import com.lti.exception.NoProfessorToList;
import com.lti.exception.NoRegistration;
import com.lti.exception.NoStudentToList;

/**
 * @author Group-02
 *
 */
public interface AdminInterface {
	
	public void courseList() throws NoCourseInCourses, ExceptionPass; 
	public void addCourse() throws NoCourseInCatalogue, ExceptionPass;
	public void deleteCourse();
	
	public void courseCatalogue();
	public void professorList() throws NoProfessorToList, ExceptionPass;
	public void addProfessor();
	
	public void studentList() throws NoStudentToList, ExceptionPass;
	public void generateReportCards();

	public void approveCourseRegistration() throws CourseApproved, ExceptionPass;
	public void registerStudent() throws NoRegistration, ExceptionPass;
}	

