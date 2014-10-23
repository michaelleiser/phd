package org.bfh.phd;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "department_has_staff", eager = true)
@RequestScoped
public class Department_Has_Staff implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int department_has_staff_id;
	private Department department;
	private List<Staff> staff;
	private Staff owner;
	
	public Department_Has_Staff(){
		this.staff = new ArrayList<Staff>();
	}
	
	public int getDepartment_has_staff_id() {
		return this.department_has_staff_id;
	}

	public void setDepartment_has_staff_id(int department_has_staff_id) {
		this.department_has_staff_id = department_has_staff_id;
	}
	
	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	public List<Staff> getStaff() {
		return this.staff;
	}

	public void setStaff(List<Staff> staff) {
		this.staff = staff;
	}

	public Staff getOwner() {
		return this.owner;
	}

	public void setOwner(Staff owner) {
		this.owner = owner;
	}

	@Override
	public String toString(){
		return this.department + " : " + this.staff + " : " + this.owner;
	}

	public void addStaff(Staff staff) {
		this.staff.add(staff);
	}
	
	public boolean isOwner(Staff s){
		return s.equals(this.owner);
	}
	
	public boolean isMember(Staff s){
		return this.staff.contains(s);
	}
	
}
