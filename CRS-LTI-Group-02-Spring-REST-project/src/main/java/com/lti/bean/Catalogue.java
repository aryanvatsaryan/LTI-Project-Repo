/**
 * 
 */
package com.lti.bean;

/**
 * @author 10710167
 *
 */
public class Catalogue extends Course{
	
	private int professorId;
	private int fees;
	private int courseAvalibitly;
	
	public Catalogue() {
		super();
	}
	
	
	public Catalogue(int courseId, String courseName, String courseDescription,int professorId, int fees, int courseAvalibitly) {
		super(courseId, courseName,courseDescription);
		this.professorId = professorId;
		this.fees = fees;
		this.courseAvalibitly = courseAvalibitly;
	}


	public int getProfessorId() {
		return professorId;
	}
	public void setProfessorId(int professorId) {
		this.professorId = professorId;
	}
	public int getFees() {
		return fees;
	}
	public void setFees(int fees) {
		this.fees = fees;
	}
	public int getCourseAvalibitly() {
		return courseAvalibitly;
	}
	public void setCourseAvalibitly(int courseAvalibitly) {
		this.courseAvalibitly = courseAvalibitly;
	}
	

}
