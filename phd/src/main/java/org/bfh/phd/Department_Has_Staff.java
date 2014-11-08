package org.bfh.phd;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.bfh.phd.interfaces.IDepartment_Has_Staff;

@ManagedBean(name = "department_has_staff", eager = true)
@RequestScoped
public class Department_Has_Staff implements Serializable, IDepartment_Has_Staff {
	
	private static final long serialVersionUID = 1L;
	
	private int department_has_staff_id;
	private Department department;
	private List<Staff> staff;
	private List<String> encryptedGroupKey;
	private Staff owner;
	
	public Department_Has_Staff(){
		this.staff = new ArrayList<Staff>();
		this.encryptedGroupKey = new ArrayList<String>();
	}
	
	@Override
	public int getDepartment_has_staff_id() {
		return this.department_has_staff_id;
	}

	@Override
	public void setDepartment_has_staff_id(int id) {
		this.department_has_staff_id = id;
	}
	
	@Override
	public Department getDepartment() {
		return this.department;
	}

	@Override
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	@Override
	public List<Staff> getStaff() {
		return this.staff;
	}

	@Override
	public void setStaff(List<Staff> staff) {
		this.staff = staff;
	}
	
	@Override
	public void addStaff(Staff staff) {
		this.staff.add(staff);
	}
	
	@Override
	public Staff getStaff(String name, String password) {
		for(Staff s : this.staff){
			if(s.getName().equals(name) && s.getPassword().equals(password)){
				return s;
			}
		}
		return null;
	}
	
	@Override
	public List<String> getEncryptedGroupKey() {
		return encryptedGroupKey;
	}

	@Override
	public void setEncryptedGroupKey(List<String> encryptedGroupKey) {
		this.encryptedGroupKey = encryptedGroupKey;
	}
	
	@Override
	public void addEncryptedGroupKey(String key){
		this.encryptedGroupKey.add(key);
	}
	
	@Override
	public String getEncryptedGroupKeyFromStaff(Staff staff){
		int index = this.staff.indexOf(staff);
		return this.encryptedGroupKey.get(index);
	}

	@Override
	public Staff getOwner() {
		return this.owner;
	}

	@Override
	public void setOwner(Staff owner) {
		this.owner = owner;
	}

	@Override
	public boolean isOwner(Staff s){
		return s.equals(this.owner);
	}
	
	@Override
	public boolean isMember(Staff s){
		return this.staff.contains(s);
	}
	
	@Override
	public String toString(){
		return this.department + " : " + this.staff + " : " + this.owner;
	}

}