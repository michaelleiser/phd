package org.bfh.phd;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.bfh.phd.interfaces.IConnection;

/**
 * This class makes an authenticated connection to a specific database.
 * 
 * @author leism3, koblt1
 */
public class MyConnection implements IConnection {

	@Override
	public Connection getConnection() {		
		Properties prop = new Properties();
		String propFile = "config.properties";
		InputStream input = Patient.class.getClassLoader().getResourceAsStream(propFile);
		try {
			prop.load(input);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String url = prop.getProperty("URL");
		String user = prop.getProperty("USER");
		String password = prop.getProperty("PASSWORD");
		String driver = prop.getProperty("JDBC-DRIVER");
			
		Connection con = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

}
