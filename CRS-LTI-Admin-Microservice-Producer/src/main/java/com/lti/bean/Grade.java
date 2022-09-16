/**
 * 
 */
package com.lti.bean;

/**
 * @author 10710167
 *
 */
public class Grade {
	
	private int studentid;
	private int courseid;
	private String grade;
	private int profid;
	
	
	public int getProfid() {
		return profid;
	}
	public void setProfid(int profid) {
		this.profid = profid;
	}
	public int getStudentid() {
		return studentid;
	}
	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}
	public int getCourseid() {
		return courseid;
	}
	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
//	public Grade(int sid, int cid, String grade) {
//
//		this.studentid = sid;
//		this.courseid = cid;
//		this.grade = grade;
//	}
	
}
