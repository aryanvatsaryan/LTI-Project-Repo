/**
 * 
 */
package com.lti.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.bean.Grade;
import com.lti.service.ProfessorService;

/**
 * 
 * @author Group-02
 * Professor Controller Class
 * 
 */
@RestController
public class ProfessorController {

	@Autowired
	private ProfessorService professorService;

	/**
	 * Method to handle API request for displaying courses for Professor
	 * @param id: Professor ID
	 * @returns Map<Integer, String>: CourseId, CourseName
	 */

	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/displayMyCourseList/{id}")
	@ResponseBody
	public ResponseEntity displayMyCourseList(@PathVariable int id) {
		return new ResponseEntity(professorService.displayMyCourses(id), HttpStatus.OK);


	}


	/**
	 * Method to handle API request for displaying Enrolled Students in courses for Professor
	 * @param id: Professor ID
	 * @returns Map<Integer, List<Integer>>: CourseId, List of StudentIds registered in that course
	 */

	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/displayEnrolledStudents/{id}")
	@ResponseBody
	public ResponseEntity displayEnrolledStudents(@PathVariable int id) {
		return new ResponseEntity(professorService.viewEnrolledStudents(id), HttpStatus.OK);
	}



	/**
	 * Method to handle API request for Marking Grades of students registered by course Professor
	 * @param studentGrade: Grade bean object for storing all inputs ProfID, StudentID, CourseID, Grade from POST method
	 * @param Map<Integer, List<Integer>>: CourseId, List of StudentIds registered under the Professor
	 * @param List<Integer>: List of students registered and approved under given course
	 * @returns String: Status of Marking Grade
	 */

	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.POST, value = "/allocateGradesforCourses")

	// Getting input from body Postman in studentGrade object of Grade Bean
	public ResponseEntity allocateGradesforCourses(@RequestBody Grade studentGrade) {

		String result = "";

		// Course and its students list for the given professor
		Map<Integer, List<Integer>> enrolled = new HashMap<Integer, List<Integer>>();
		enrolled = professorService.viewEnrolledStudents(studentGrade.getProfid());

		// Checking if the given course is available for given professor
		if( enrolled.containsKey( studentGrade.getCourseid() ) ) {

			// Getting List of Students Registered under that course and Professor
			List<Integer> student = new ArrayList<Integer>();
			student = enrolled.get(studentGrade.getCourseid());

			// Checking If the given student is registered under that course
			if ( student.contains(studentGrade.getStudentid()) ) {
				result = professorService.registerGrades(studentGrade);
			} else {
				result = "Student is not registered in given course";
			}

		} else {
			result = "Course not availabe for professor";
		}

		return new ResponseEntity(result, HttpStatus.OK);
	}


	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/logoutProfessor")
	@ResponseBody
	public String logoutProfessor() {
		return "Logging Out to Professor Menu ";
	}
}
