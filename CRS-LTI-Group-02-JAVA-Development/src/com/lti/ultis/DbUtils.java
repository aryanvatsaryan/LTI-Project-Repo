/**
 * 
 */
package com.lti.ultis;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 
 * @author Group-02
 * Class that establishes connection with database 
 * 
 */

public class DbUtils {


	private static Connection connection = null;

	/**
	 * Method to get Connection to database
	 * @param driver: gets driver client to connect to the database
	 * @param url: gets URL of the database
	 * @param user: gets user credential
	 * @param password: gets password credential
	 * @returns Connection which is an object establishing connection to the database
	 */

	public static Connection getConnection() {

		if (connection != null)
			return connection;
		else {
			try {
				Properties prop = new Properties();
				InputStream inputStream = DbUtils.class.getClassLoader().getResourceAsStream("./config.properties");
				prop.load(inputStream);
				String driver = prop.getProperty("driver");
				String url = prop.getProperty("url");
				String user = prop.getProperty("user");
				String password = prop.getProperty("password");
				Class.forName(driver);
				connection = DriverManager.getConnection(url, user, password);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return connection;
		}
	}
}
