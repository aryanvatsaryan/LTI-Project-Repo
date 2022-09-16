/**
 * 
 */
package com.lti.constant;


/**
 * 
 * @author Group-02
 * Class to define SQL constants
 *
 */
public class SQLConstant {

	// USER
	public final static String USER_LOGIN = "SELECT UserID, username, password FROM user WHERE username='%s'";
	public final static String USER_LOGIN_USERTYPE_CHECK = "SELECT Role FROM role WHERE RoleID='%d'";
	public final static String RESET_PASSWORD = "UPDATE user SET password = '%s' WHERE UserID = '%d' AND username = '%s'";
	public final static String CHECK_USERID = "SELECT UserID, username, password FROM user WHERE UserID = '%d'";



	// ADMIN
	public final static String DISPLAY_CATALOUGE = "SELECT * FROM catalouge";
	public final static String DISPLAY_PROFESSOR = "SELECT * FROM proffesor";
	public final static String DISPLAY_STUDENT = "SELECT * FROM student";
	public final static String DISPLAY_COURSES = "SELECT * FROM courses";
	
	public final static String INSERT_COURSE = "INSERT INTO Courses(courseId, courseName, courseDescription)" + "VALUES (?, ?, ?)";
	public final static String INSERT_CATALOUGE = "INSERT INTO catalouge(courseID, proffesorId, fees, CourseAvalibility)" + "VALUES(?, ?, ?, ?)";
	public final static String INSERT_USER_A = "INSERT INTO user(userId, password, username)" + "VALUES (?, ?, ?)";
	public final static String INSERT_ROLE_A = "INSERT INTO role(roleID, role, RoleDescription)" + "VALUES(?, ?, ?)";
	public final static String INSERT_PROFESSOR_A = "INSERT INTO proffesor(professorID, Name, Department, Age, Address, mobileNumber, Gender)" + "VALUES(?, ?, ?, ?, ?, ?, ?)";
	public final static String INSERT_USER_B = "INSERT INTO user (UserID, username, password) VALUES ('%d' , '%s', '%s') ";
	public final static String INSERT_STUDENT_A = "INSERT INTO student (StudentID, Name, Department, MobileNumber, Address, Gender, Age) VALUES ('%d' , '%s', '%s', '%d' , '%s', '%s', '%d' )";
	public final static String INSERT_ROLE_B = "INSERT INTO role (RoleID, Role, RoleDescription) VALUES ('%d', 'Student', 'This is Student')";
	
	public final static String SELECT_COURSES = "SELECT * FROM courses WHERE courseID = '";
	public final static String SELECT_SEMESTER = "SELECT userID, courseID FROM semester_registration WHERE isApproved='0' ";
	public final static String SELECT_REGISTRATION = "SELECT RegistrationID, name, address, gender, age, department, mobileNumber, username, password FROM registration";
	public final static String SELECT_COURSES_PROF = "SELECT CourseID, proffesorId FROM catalouge WHERE CourseAvalibility=1";
	public final static String SELECT_STUDENTID_NAME = "SELECT StudentID, Name FROM student";
	public final static String SELECT_PROFID_NAME = "SELECT ProfessorID, Name FROM proffesor";
	
	public final static String UPDATE_SEMESTER = "UPDATE semester_registration SET isApproved = '1' WHERE userID = '%d' AND courseID = '%d'";
	public final static String UPDATE_REPORTCARD_S1 = "UPDATE reportcard_available SET isAvailable = REPLACE(isAvailable,0, 1) WHERE ID = 'isAvailable'" ;
	public final static String UPDATE_REPORTCARD_S0 = "UPDATE reportcard_available SET isAvailable = REPLACE(isAvailable,1, 0) WHERE ID = 'isAvailable'" ;
	
	public final static String DELETE_CATALOUGE = "DELETE FROM catalouge WHERE courseID = '";
	public final static String DELETE_COURSES = "DELETE FROM courses WHERE courseID = '";
	public final static String DELETE_REGISTRATION = "DELETE FROM registration WHERE RegistrationID = '%d' ";
	

	// PROFESSOR
	public final static String SELECT_PROFESSOR_P = "SELECT ProfessorID, Name, Department FROM proffesor WHERE ProfessorID=";
	public final static String SELECT_CATALOGUE_P = "SELECT courseID FROM catalouge WHERE proffesorID=";
	public final static String SELECT_COURSE_P = "SELECT * FROM courses WHERE courseID=";
	public final static String SELECT_SEMESTER_P = "SELECT userID FROM semester_registration WHERE isApproved='1' AND courseID=";
	public final static String SELECT_SEMESTER_P_A = "SELECT userID FROM semester_registration WHERE courseID=";
	public final static String SELECT_GRADE_P = "SELECT grade FROM grade WHERE studentId=";

	public final static String INSERT_GRADE_P = "INSERT INTO grade (studentId,courseID,grade) VALUES (?,?,?);";

	// STUDENT
	public final static String SELECT_COURSEID_AVAILABLE_S = "SELECT CourseID FROM catalouge WHERE CourseAvalibility=1";
	public final static String SELECT_COURSENAME_S = "SELECT courseName FROM courses WHERE courseID=%d";
	public final static String SELECT_REPORTCARD_AVAILABLE_S = "SELECT isAvailable FROM reportcard_available WHERE ID = 'isAvailable' " ;
	public final static String SELECT_GRADE_S = "SELECT courseID, grade FROM grade WHERE studentId='%d' ";
	public final static String SELECT_COURSEID_APPROVED_S = "SELECT courseID, isApproved FROM semester_registration WHERE userID=%d";
	public final static String SELECT_COURSEID_SEM_REGISTRATION_S = "SELECT courseID FROM semester_registration WHERE userID='%d' AND courseID='%d'";
	public final static String SELECT_CATALOGUE_COURSEAVAILABILITY_S = "SELECT CourseAvalibility FROM catalouge WHERE CourseID='%d'";
	public final static String SELECT_APPROVED_COURSEID_SEM_REGISTRATION_S = "SELECT courseID FROM semester_registration WHERE userID=%d AND isApproved=1 ";
	public final static String SELECT_FEES_S = "SELECT fees FROM catalouge WHERE CourseID=%d";
	public final static String SELECT_PAYMENT_S = "SELECT studentID, paymentDone FROM payment WHERE studentID='%d'";
	public final static String SELECT_REGISTRATION_DETAILS_S = "SELECT * FROM registration WHERE RegistrationID='%d'";
	
	public final static String INSERT_SEMESTER_REGISTRATION_S = "INSERT INTO semester_registration (userID, courseID, semester, isApproved) VALUES ('%d', '%d', '%d', '0')";
	public final static String INSERT_PAYMENT_DETAILS_S = "INSERT INTO payment (studentID, paymentDone, toatalFees, transactionID, ModeOfPayment) VALUES ('%d', '%d', '%d', '%d', '%s')";
	public final static String INSERT_REGISTRATION_DETAILS_S = "INSERT INTO registration (RegistrationID, name, address, gender, age, department, mobileNumber, username, password) VALUES ('%d', '%s', '%s', '%s', '%d', '%s', '%d', '%s', '%s')";
	
	public final static String DELETE_SEMESTER_REGISTRATION_S = "DELETE FROM semester_registration WHERE userID = '%d' AND courseID = '%d'";

	public final static String UPDATE_PAYMENT_DETAILS_S = "UPDATE payment SET paymentDone = '%d', toatalFees = '%d', transactionID = '%d', ModeOfPayment = '%s' WHERE studentID = '%d' ";

}
