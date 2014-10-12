package org.bfh.phd;

import org.bfh.phd.interfaces.IStaff;

public class Staff implements IStaff{
	
	private int id;
	private String name;
	private String password;
	private int role;
	
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

	
}
