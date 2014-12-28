package org.bfh.phd;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.bfh.phd.interfaces.IDepartment_Has_Staff;

/**
 * This is in fact the department group, which contains several staffs and at minimum has an owner. 
 * Each staff has also an encrypted group key.
 * 
 * @author leism3, koblt1
 */
@ManagedBean(name = "department_has_staff", eager = true)
@RequestScoped
public class Department_Has_Staff implements Serializable, IDepartment_Has_Staff {
	
	private static final long serialVersionUID = 1L;
	
	private int department_has_staff_id;
	private Department department;
	private List<Staff> staffs;
	private List<String> encryptedGroupKeys;
	private Staff owner;
	
	public Department_Has_Staff(){
		this.staffs = new ArrayList<Staff>();
		this.encryptedGroupKeys = new ArrayList<String>();
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
	public List<Staff> getStaffs() {
		return this.staffs;
	}

	@Override
	public void setStaffs(List<Staff> staffs) {
		this.staffs = staffs;
	}
	
	@Override
	public void addStaff(Staff staff) {
		this.staffs.add(staff);
	}
	
	@Override
	public Staff getStaff(String name) {
		for(Staff s : this.staffs){
			if(s.getName().equals(name)){
				return s;
			}
		}
		return null;
	}
	
	@Override
	public List<String> getEncryptedGroupKeys() {
		return encryptedGroupKeys;
	}

	@Override
	public void setEncryptedGroupKeys(List<String> encryptedGroupKeys) {
		this.encryptedGroupKeys = encryptedGroupKeys;
	}
	
	@Override
	public void addEncryptedGroupKey(String key){
		this.encryptedGroupKeys.add(key);
	}
	
	@Override
	public String getEncryptedGroupKeyFromStaff(Staff staff){
		int index = this.staffs.indexOf(staff);
		return this.encryptedGroupKeys.get(index);
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
		return this.staffs.contains(s);
	}
	
	@Override
	public String toString(){
		return this.department + " : " + this.staffs + " : " + this.owner;
	}

}