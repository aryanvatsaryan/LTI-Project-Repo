/**
 * 
 */
package com.lti.service;

import java.util.List;
import java.util.Map;

import com.lti.bean.Payment;
import com.lti.bean.semesterRegistration;

/**
 * 
 * @author Group-02
 * Interface for Student Service
 *
 */
public interface StudentInterface {

	public Map<Integer,String> displayReportCard(int studentID);
	
	public Map<Integer,String> displayAvailableCourses();
	
	public Map<Integer, List> viewRegisteredCourses(int studentID);
    
	public String studentFeePayment(Payment feePayment);
    
    public String addStudentCourse(semesterRegistration studentCourseRegistration);
    
    public String dropStudentCourse(semesterRegistration studentCourseRegistration);
    
    public void logout();
    
}
