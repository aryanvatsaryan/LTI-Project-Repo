/**
 * 
 */
package com.lti.controller;

import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.bean.Payment;
import com.lti.bean.Registration;
import com.lti.bean.semesterRegistration;
import com.lti.service.ProfessorService;
import com.lti.service.StudentSerivce;

/**
 * 
 * @author Group-02
 * Student Controller Class
 * 
 */
@RestController
public class StudentController {
	
	@Autowired
	private StudentSerivce studentService;

	/**
     * Method to handle API request for displaying registered courses for student
     * @param sid: Student ID
     * @returns Map<Integer, List>: Registered courses
     */
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/viewRegisteredCourse/{sid}")
	@ResponseBody
	public ResponseEntity<Object> viewRegisteredCourse(@PathVariable int sid) {
		
		return new ResponseEntity<>(studentService.viewRegisteredCourses(sid), HttpStatus.OK);
	}
	
	/**
     * Method to handle API request for adding courses for student semester registration
     * @param studentCourseRegistration: semesterRegistration bean object for storing all inputs from POST method
     * @returns boolean: Whether addition of courses successfull or not
     */
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.POST , value = "/addCourse")
	public ResponseEntity<Object> addCourse(@RequestBody semesterRegistration studentCourseRegistration) {
		
		return new ResponseEntity<>(studentService.addStudentCourse(studentCourseRegistration), HttpStatus.OK);
		
	}
	
	/**
     * Method to handle API request for dropping courses for student semester registration
     * @param studentCourseRegistration: semesterRegistration bean object for all inputs from DELETE method
     * @returns boolean: Whether addition of courses successfull or not
     */
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.DELETE , value = "/dropCourse")
	public ResponseEntity<Object> dropCourse(@RequestBody semesterRegistration studentCourseRegistration) {
		
		return new ResponseEntity<>(studentService.dropStudentCourse(studentCourseRegistration), HttpStatus.OK);
	}

	/**
     * Method to handle API request for requesting course catalogue
     * @returns Map<Integer, String>: Course Catalogue
     */
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/requestCourseCatalogue")
	@ResponseBody
	public ResponseEntity<Object> requestCourseCatalogue() {
		return new ResponseEntity<>(studentService.displayAvailableCourses(), HttpStatus.OK);
	}

	/**
     * Method to handle API request for viewing report card for student
     * @param sid: Student ID
     * @returns Map<Integer,String>: Student Report Card
     */
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/viewReportCard/{sid}")
	@ResponseBody
	public ResponseEntity<Object> viewReportCard(@PathVariable int sid) {
		return new ResponseEntity<>(studentService.displayReportCard(sid), HttpStatus.OK);
	}

	/**
     * Method to handle API request for fee payment for the student
     * @param feePayment: Payment bean object for storing values for fee payment
     * @returns boolean: Whether fee payment is done or not
     */
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.POST, value = "/feePayment")
	public ResponseEntity<Object> feePayment(@RequestBody Payment feePayment) {
		
		return new ResponseEntity<>(studentService.studentFeePayment(feePayment), HttpStatus.OK);
	}
	
	/**
     * Method to handle API request for logging out for the student
     * @returns String: Log out
     */
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/logoutStudent")
	@ResponseBody
	public ResponseEntity<Object> logoutStudent() {
		return new ResponseEntity<>("Logging Out to Student Menu ", HttpStatus.OK);
	}
	
}
