package org.bfh.phd;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.bfh.phd.interfaces.IConnection;

public class MyConnection implements IConnection {

	@Override
	public Connection getConnection() {
		
//		FacesContext fc = FacesContext.getCurrentInstance();
//		String driver = fc.getExternalContext().getInitParameter("JDBC-DRIVER");
			
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
//		String url = "jdbc:mysql://localhost:3306/testdb";
//		String user = "user";
//		String password = "user";
//		String driver = "com.mysql.jdbc.Driver";
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
//			System.out.println("Connection completed");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

}
