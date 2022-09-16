/**
 * 
 */
package com.lti.controller;

import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lti.bean.Catalogue;
import com.lti.bean.Professor;
import com.lti.bean.Student;
import com.lti.bean.User;
import com.lti.service.AdminService;

/**
 * 
 * @author Group-02
 * Admin Controller Class
 * 
 */

@RestController
public class AdminController {

	@Autowired
	private AdminService adminService;

	/**
	 * Method to handle API request for display course List
	 * @returns Course List
	 */	
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/viewCourseList")
	@ResponseBody
	public ResponseEntity<Object> viewCourseList() {
		//	return adminService.courseList();
		return new ResponseEntity(adminService.courseList(), HttpStatus.OK);
	}

	/**
	 * Method to handle API request for Adding Course to Course List
	 * @param courseCatalogue
	 * @returns boolean for successful addition of the Course to the Course List
	 */
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.PUT, value = "/addCoursetoList")
	public ResponseEntity<Object> addCoursetoList(@RequestBody Catalogue courseCatalogue ) {

		return new ResponseEntity(adminService.addCourse(courseCatalogue), HttpStatus.OK);
	}

	/**
	 * Method to handle API request for Deleting Course from Course List
	 * @param cid: Course ID
	 * @returns boolean for successful deletion of the Course form the Course List
	 */
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.DELETE, value = "/deleteCoursefromList/{cid}")
	@ResponseBody
	public ResponseEntity<Object> deleteCoursefromList(@PathVariable int cid) {
	
		return new ResponseEntity(adminService.deleteCourse(cid), HttpStatus.OK);
	}

	/**
	 * Method to handle API request for Approval of Student Course Registration
	 * @param id: input indicating approval or denial
	 * @returns boolean for successful approval or denial
	 */
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/approveCourseRegistrations/{id}")
	@ResponseBody
	public ResponseEntity<Object> approveCourseRegistrations(@PathVariable int id) {
	//	return adminService.approveCourseRegistration(id); //It return that DAO method is called
		return new ResponseEntity(adminService.approveCourseRegistration(id), HttpStatus.OK);
	}

	/**
	 * Method to handle API request for display Course Catalogue
	 * @returns Course Catalogue List
	 */	
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/courseCatalogue")
	@ResponseBody
	public ResponseEntity<Object> courseCatalogue() {
		return new ResponseEntity(adminService.courseCatalogue(), HttpStatus.OK);
	}

	/**
	 * Method to handle API request for display Professor List
	 * @returns Professor List
	 */	
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/viewProfessorList")
	@ResponseBody
	public ResponseEntity<Object> viewProfessorList() {
		return new ResponseEntity(adminService.professorList(), HttpStatus.OK);
	}

	/**
	 * Method to handle API request for Adding Professor to List
	 * @param professor
	 * @returns boolean for successful addition of Professor to List
	 */
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.PUT, value = "/addProfessortoList")
	public ResponseEntity<Object> addProfessortoList(@RequestBody Professor professor) {
		return new ResponseEntity(adminService.addProfessor(professor), HttpStatus.OK);
	}

	/**
	 * Method to handle API request for display Student List
	 * @returns Student List
	 */	
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/viewStudentList")
	@ResponseBody
	public ResponseEntity<Object> viewStudentList() {
		return new ResponseEntity(adminService.studentList(), HttpStatus.OK);
	}

	/**
	 * Method to handle API request for registering student
	 * @param id: input indicating approval or denial of registration
	 * @returns boolean for successful approval or denial of registration
	 */
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.PUT, value = "/addStudenttoList/{id}")
	public ResponseEntity<Object> addStudenttoList(@PathVariable int id) {
		return new ResponseEntity(adminService.registerStudent(id), HttpStatus.OK);
	}

	/**
	 * Method to handle API request for generating report card
	 * @param flag: input indicating approval or denial of generation of report card
	 * @returns String for successful approval or denial of generation of report card
	 */
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/generateReportCard/{flag}")
	@ResponseBody
	public String generateReportCard(@PathVariable boolean flag) {
		adminService.generateReportCards(flag);
		if(flag) return "Report Card Generated";
		else return "Report Card would not Available";
	}

	/**
	 * Method to handle API request for Logging out
	 */
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/logoutAdmin")
	@ResponseBody
	public String logoutAdmin() {
		return "Logging Out to Admin Menu ";
	}
}
