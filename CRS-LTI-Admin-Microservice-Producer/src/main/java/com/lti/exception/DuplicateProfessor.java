/**
 * 
 */
package com.lti.exception;

/**
 * @author Group 02
 * Exception to check if duplicate course is present
 *
 */
public class DuplicateProfessor extends Exception{
	
	private int professorid ;

	/**
	 * Constructor
	 * @param cid: professorid
	 */
	public DuplicateProfessor(int pid)
	{
		this.professorid = pid;
	} 

	/**
	 * Getter method
	 * @return professorid
	 */
	public int getPid()
	{
		return professorid;
	}

}
