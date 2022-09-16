/**
 * 
 */
package com.lti.bean;
import java.util.HashMap;

/**
 * @author 10710167
 *
 */
public class Student extends User{
	
	private HashMap<Integer, Course> courses;
	private HashMap<Integer, String> grades;
	
	public Student() {
		this.courses = new HashMap<Integer, Course>(); // CourseID --> Course
		this.grades = new HashMap<Integer, String>(); // CourseID --> Grade
	}
	
	public HashMap<Integer, Course> getCourses() {
		return courses;
	}
	
	public void setCourses(HashMap<Integer, Course> courses) {
		this.courses = courses;
	}
	
	public HashMap<Integer, String> getGrades() {
		return grades;
	}
	
	public void setGrades(HashMap<Integer, String> grades) {
		this.grades = grades;
	}
	
	public void setCourseGrade(Integer courseID, String grade) {
		grades.put(courseID, grade);
	}
	
	public String getCourseGrade(Integer courseID) {
		return grades.get(courseID);
	}
	
}	