/**
 * 
 */
package com.lti.service;

import java.util.List;
import java.util.Map;

import com.lti.bean.Grade;
import com.lti.exception.ExceptionPass;

/**
 * @author Group-02
 * Interface for Professor Service
 *
 */
public interface ProfessorInterface {
		
	public Map<Integer,String> displayMyCourses(int userid);
	
	public Map<Integer, List<Integer>> viewEnrolledStudents(int userid);
	
	public String registerGrades(Grade grade) throws ExceptionPass;

}

