/**
 * 
 */
package com.lti.exception;

/**
 * @author Group-02
 * Exception to check if no course is alloted to professor
 *
 */
public class NoCourseToProfessor extends Exception {

	private int professorID;
	
	/**
	 * Constructor
	 * @param pid: ProfessorID
	 */
	public NoCourseToProfessor(int pid)
	{
		this.professorID = pid;
		//System.out.println("\n  No course Available for ");
	} 
	
	/**
	 * Getter method
	 * @return professorID
	 */
	public int getPid()
	{
		return professorID;
	}

}


