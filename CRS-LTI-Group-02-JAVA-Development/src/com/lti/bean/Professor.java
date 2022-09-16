/**
 * 
 */
package com.lti.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author 10710167
 *
 */
public class Professor extends User {
	
	private HashMap<Integer, Course> courses; // CourseID --> Course (object)
	private HashMap<Integer, List<Student>> enrolledStudents; // CourseID --> List of Students
	
	public Professor() {
		this.courses = new HashMap<Integer, Course>();
		this.enrolledStudents = new HashMap<Integer, List<Student>>();
	}
	
	/**
	 * @param courses
	 * @param enrolledStudents
	 */
	public Professor(HashMap<Integer, Course> courses, HashMap<Integer, List<Student>> enrolledStudents) {
		this.courses = courses;
		this.enrolledStudents = enrolledStudents;
	}
	
	
	public HashMap<Integer, Course> getCourses() {
		return courses;
	}
	public void setCourses(HashMap<Integer, Course> courses) {
		this.courses = courses;
	}
	public HashMap<Integer, List<Student>> getEnrolledStudents() {
		return enrolledStudents;
	}
	public List<Student> getStudentList(Integer courseID) {
		List<Student> studentList = enrolledStudents.get(courseID);
		if(studentList==null) {
			studentList = new ArrayList<Student>(0);
			enrolledStudents.put(courseID, studentList);
		}
		return studentList;
	}
	public void setEnrolledStudents(HashMap<Integer, List<Student>> enrolledStudents) {
		this.enrolledStudents = enrolledStudents;
	}
	
	
}
