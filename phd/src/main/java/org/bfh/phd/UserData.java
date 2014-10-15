package org.bfh.phd;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.bfh.phd.interfaces.IUserData;

@ManagedBean(name = "userData", eager = true)
@SessionScoped
public class UserData implements Serializable, IUserData {

//	private static final long serialVersionUID = 1L;
//
//	private String name;
//	private String password;
//	private int role;
//
//	public UserData() {
//
//	}
//
//	@Override
//	public String getName() {
//		return this.name;
//	}
//	
//	@Override
//	public void setName(String name) {
//		this.name = name;
//	}
//	
//	@Override
//	public String getPassword() {
//		return this.password;
//	}
//
//	@Override
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	@Override
//	public int getRole() {
//		return this.role;
//	}
//
//	@Override
//	public void setRole(int role) {
//		this.role = role;
//	}
//	
//	@Override
//	public String login() {	
//		return new LoginController().login(this.name, this.password);
//	}
//
//	@Override
//	public String registernew(){
//		return new LoginController().registernew(this.name, this.password, this.role);
//	}

}
