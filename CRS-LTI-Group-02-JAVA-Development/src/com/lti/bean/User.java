/**
 * 
 */
package com.lti.bean;

/**
 * @author 10710167
 *
 */
public class User {
	private String Username;
	private String Password;

	private String Name;
	private String Department;
	private String IP_Address;
	
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getDepartment() {
		return Department;
	}
	public void setDepartment(String department) {
		Department = department;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getIP_Address() {
		return IP_Address;
	}
	public void setIP_Address(String iP_Address) {
		IP_Address = iP_Address;
	}
	
	
}
