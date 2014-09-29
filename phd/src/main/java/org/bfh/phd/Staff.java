package org.bfh.phd;

public class Staff {
	
	private String name;
	private String password;
	private int role;
	
	public Staff(){
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
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
	
	@Override
	public String toString(){
		return name + " : " + role;
	}
	
}
