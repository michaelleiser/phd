package org.phd.test;

public class Staff {
	
	private String name;
	private String password;
	private int role;
	
	public Staff(){
		
	}
	
	public Staff(String name, String password, int role){
		this.name = name;
		this.password = password;
		this.role = role;
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
