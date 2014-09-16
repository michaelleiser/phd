package org.phd.test;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "userData", eager = true)
@RequestScoped
public class UserData implements Serializable {
	
	private String name;
	private String password;

	public UserData() {

	}

	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String login() {	
		return new LoginController().login(this.name, this.password);
	}
	
	public String logout(){
		return new LoginController().logout();
	}

	public String registernew(){
		return new LoginController().registernew(this.name, this.password);
	}
	
}
