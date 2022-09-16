/**
 * 
 */
package com.lti.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lti.constant.SQLConstant;
import com.lti.exception.ExceptionPass;
import com.lti.exception.UserNotFoundException;
import com.lti.ultis.DbUtils;

/**
 * @author Group 02
 * Implementation of User Dao Services
 */
public class UserDaoImplementation implements UserDao {
	
	Connection conn = null;
	
	
	/**
	 * Method for User to access login details from the database.
	 * @param username (input from user)
	 * @param password (input from user)
	 * @return List containing userID, username(database) and password(database).
	 */
	public List logincheck(String username, String userTypeName) throws UserNotFoundException {

		List userloginDetails = new ArrayList(3);
		PreparedStatement stmt = null;
		
		try{
			conn = DbUtils.getConnection();

			String sql = String.format(SQLConstant.USER_LOGIN, username);
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			if (!rs.isBeforeFirst()) {
				throw new ExceptionPass();
			}
			
			while (rs.next()) {
				
				//Retrieve by column name
				int eid  = rs.getInt("UserID");
				String name1 = rs.getString("username");
				String password1 = rs.getString("password");

				String sql1 = String.format(SQLConstant.USER_LOGIN_USERTYPE_CHECK, eid);
				stmt = conn.prepareStatement(sql1);
				ResultSet rs1 = stmt.executeQuery(sql1);

				rs1.next();
				String userTypeNameCheck = rs1.getString("Role");

				if(userTypeNameCheck.equals(userTypeName)) {
					userloginDetails.add(eid);
					userloginDetails.add(name1);
					userloginDetails.add(password1);
				}
				else {
					userloginDetails.add(null);
					userloginDetails.add(null);
					userloginDetails.add(null);
				}

			}
			
			stmt.close();
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(Exception e){
			throw new UserNotFoundException();
		}
		finally{
			try{
				if(stmt!=null) 
					stmt.close();
			} catch(SQLException se2){
			
			}
		}
		
		return userloginDetails;
	}

	
	/**
	 * Method for User to update password in the database.
	 * @return
	 */
	public void resetPassword(Integer userID, String username, String newPassword) throws UserNotFoundException {

		PreparedStatement stmt = null;
		PreparedStatement stmt1 = null;
		
		
		try{
			conn = DbUtils.getConnection();

			String sql = String.format(SQLConstant.CHECK_USERID, userID);
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			if (!rs.isBeforeFirst()) {
				throw new ExceptionPass();
			}else {
				String sql1= String.format(SQLConstant.RESET_PASSWORD , newPassword, userID, username);
				stmt1 = conn.prepareStatement(sql1);
				stmt1.executeUpdate(sql1);
			}
			stmt.close();
			stmt1.close();
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(Exception e){
			throw new UserNotFoundException();
		}
		finally{
			try{
				if(stmt!=null) 
					stmt.close();
			} catch(SQLException se2){
			
			}
		}

	}
}