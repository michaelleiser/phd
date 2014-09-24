package org.phd.test;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "userData", eager = true)
@SessionScoped							// evtl nur RequestScoped
public class UserData implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private String password;
	private int role;

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

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}
	
	public String login() {	
		return new LoginController().login(this.name, this.password);
	}

	public String logout(){
		return new LoginController().logout();
	}

	public String registernew(){
		return new LoginController().registernew(this.name, this.password, this.role);
	}

}
