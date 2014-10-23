package org.bfh.phd;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "department", eager = true)
@RequestScoped
public class Department implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int department_id;
	private String name;
	
	public int getDepartment_id() {
		return this.department_id;
	}

	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString(){
		return this.name;
	}
	
}
