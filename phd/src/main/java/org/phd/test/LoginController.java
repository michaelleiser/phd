package org.phd.test;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "loginController", eager = true)
@SessionScoped
public class LoginController implements Serializable {

	private static final long serialVersionUID = 1L;
	private static boolean loggedin = false;
	
//	@ManagedProperty(value="#{navigation}")
//	private Navigation navigation;
	
	public String login(String name, String password) {
		Boolean loggedIn = Boolean.FALSE;
		String stm = "SELECT * FROM doctor WHERE name=? AND password=?;";
		Connection con = MyConnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(stm);
			pst.setString(1, name);
			pst.setString(2, password);
			pst.execute();
			rs = pst.getResultSet();
			if (rs.next()) {
				loggedIn = Boolean.TRUE;
			}
			rs.close();
			pst.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		if (loggedIn) {
			setLoggedin(true);
			System.out.println("looged in");
			return "loggedin";
		} else {
			return "home";
		}
	}
	
	public String logout(){	
		setLoggedin(false);
		return "home";
	}
	
	public boolean getLoggedin(){
		return this.loggedin;
	}
	
	public void setLoggedin(boolean loggedin){
		this.loggedin = loggedin;
	}

	public String registernew(String name, String password) {
		String stm1 = "SELECT * FROM doctor WHERE name=?;";
		String stm2 = "INSERT INTO testdb.doctor(name, password) VALUES(?, ?);";
		Connection con = MyConnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(stm1);
			pst.setString(1, name);
			pst.execute();
			rs = pst.getResultSet();
			if(rs.next()) {
				return "home";
			}
			pst = con.prepareStatement(stm2);
			pst.setString(1, name);
			pst.setString(2, password);
			pst.execute();
			rs.close();
			pst.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return "home";
	}
	
//	public void setNavigation(Navigation navigation){
//		this.navigation = navigation;
//	}
//	
//	public Navigation getNavigation(){
//		return this.navigation;
//	}
}
