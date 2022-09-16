/**
 * 
 */
package com.lti.service;

/**
 * @author 10710167
 *
 */
public interface StudentInterface {

	public void displayReportCard(int studentID);
	
	public void displayAvailableCourses();
	
	public void viewRegisteredCourses(int studentID);
    
    public void feePayment(int StudentID);
    
    public void addDropCourse(int studentID);
    
    public void logout();
    
}
