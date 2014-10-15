package org.bfh.phd;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.bfh.phd.interfaces.IStaff;

@ManagedBean(name = "staff", eager = true)
@SessionScoped
public class Staff implements IStaff{
	
	private int id;
	private String name;
	private String password;
	private int role;
	
	private String publicKey;
	private String privateKey;
	
	public Staff(){
		
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int getRole() {
		return role;
	}
	
	@Override
	public void setRole(int role) {
		this.role = role;
	}
	
	@Override
	public String toString(){
		return name + " : " + role;
	}

	@Override
	public String getPublicKey() {
		return publicKey;
	}

	@Override
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	@Override
	public String getPrivateKey() {
		return privateKey;
	}

	@Override
	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	@Override
	public String login() {	
		return new LoginController().login(this.name, this.password);
	}

	@Override
	public String registernew(){
		return new LoginController().registernew(this.name, this.password, this.role);
	}
}
