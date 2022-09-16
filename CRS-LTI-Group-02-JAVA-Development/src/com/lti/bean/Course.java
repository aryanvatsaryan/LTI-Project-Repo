package com.lti.bean;

public class Course {
	private int ID;
	private String courseName;
	private String professorUserName;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getProfessorUserName() {
		return professorUserName;
	}
	public void setProfessorUserName(String professorUserName) {
		this.professorUserName = professorUserName;
	}
	
	
}
