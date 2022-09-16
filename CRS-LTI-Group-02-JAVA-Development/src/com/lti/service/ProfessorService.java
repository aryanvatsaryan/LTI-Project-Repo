package com.lti.service;

import java.util.Scanner;
import com.lti.dao.ProfessorDaoImplementation;
import com.lti.exception.ExceptionPass;
import com.lti.exception.GradeAlreadyMarked;
import com.lti.exception.NoCourseToProfessor;
import com.lti.exception.NoStudentToCourse;

/**
 * 
 * @author Group-02
 * Implementations of Professor Operations
 *
 */
public class ProfessorService extends UserService implements ProfessorInterface {

	ProfessorDaoImplementation professor = new ProfessorDaoImplementation();

	/**
	 * Method to display the courses
	 * @param userid
	 * @throws NoCourseToProfessor
	 * @throws ExceptionPass
	 */
	@Override
	public void displayMyCourses(int userid) {

		try {
			professor.professorCourses(userid);
			//System.out.println("try works");

		}
		catch(NoCourseToProfessor e) {
			//System.out.println("catch works");
			System.out.println("\n  No course Available for Professor ID: " + e.getPid());
		}
		catch(ExceptionPass e) {

		}

	}

	/**
	 * Method to display the enrolled students
	 * @param userid
	 * @throws NoCourseToProfessor
	 * @throws ExceptionPass
	 */
	@Override
	public void viewEnrolledStudents(int userid) {
		// TODO Auto-generated method stub
		try {
			professor.studentList(userid);
		}
		catch(NoCourseToProfessor e) {
			//System.out.println("catch works");
			System.out.println("\n  No course Available for Professor ID: " + e.getPid());
		}
		catch(ExceptionPass e) {

		}
	}

	/**
	 * Method to register grades for students
	 * @param userid: Professor ID
	 * @param courseid
	 * @param sid: Student ID
	 * @param grade
	 * @throws NoCourseToProfessor
	 * @throws NoStudentToCourse
	 * @throws ExceptionPass
	 */
	@Override
	public void registerGrades(int userid) {
		// TODO Auto-generated method stub

		Scanner input = new Scanner(System.in);
		boolean courseAvailableFlag = false;

		while(true) {

			System.out.println("\n Courses to Grade: ");

			try {
				professor.professorCourses(userid);
				courseAvailableFlag = true;

			}
			catch(NoCourseToProfessor e) {
				//System.out.println("catch works");
				System.out.println("\n  No course Available for Professor ID: " + e.getPid());
			}
			catch(ExceptionPass e) {

			}

			if(!courseAvailableFlag) {
				return;
			}

			else {
				System.out.println("-------------------------------------------------------------------------------------------");

				// Choose to grade or go back option
				System.out.print("   1: Add  Grade       ");
				System.out.println("2: Go Back");
				System.out.println("");
				System.out.print("    Choose any one: ");

				int i = input.nextInt();
				System.out.println("");

				switch(i) {

				// Choose courses for grading
				case 1: System.out.println("        Courses available: ");
				System.out.println("");
				Scanner input1 = new Scanner(System.in);
				System.out.println("      Which course you want to add grade");
				System.out.print("      Enter Course ID: ");
				int courseid = input1.nextInt();
				try {
					professor.studentList(userid,courseid); //function overloaded

					// Choose student to mark grade
					System.out.print("      Enter Student ID: ");
					int sid = input1.nextInt();
					input1.nextLine();
					// Choose grade
					System.out.print("\n      Enter Grade: ");
					String grade = input1.nextLine();
					System.out.println("");
					try {
						professor.addgrade(courseid, sid, grade);
					}
					catch(GradeAlreadyMarked e) {
						//System.out.println("catch works");
						System.out.println("\n  Grade already marked for studentID: " + e.getSid());
					}
					catch(ExceptionPass e) {

					}

				}
				catch(NoStudentToCourse e) {
					System.out.println("No student available for courseID: " + e.getCid());

				}
				catch(ExceptionPass e) {

				}

				break;

				case 2: System.out.println("  Going Back");
				break;


				}
				if (i==2) {
					break;
				}	
			}

		}


	}
}
