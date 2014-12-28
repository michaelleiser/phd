package org.bfh.phd.interfaces;

import java.util.List;

import org.bfh.phd.Department;
import org.bfh.phd.Staff;

/**
 * This is in fact a department group, which contains several staffs and at minimum has an owner.
 * Each staff has also an encrypted group key.
 * 
 * @author leism3, koblt1
 */
public interface IDepartment_Has_Staff {

	/**
	 * @return
	 * 			id of the department group
	 */
	public int getDepartment_has_staff_id();

	/**
	 * @param id
	 * 			of the department group
	 */
	public void setDepartment_has_staff_id(int id);

	/**
	 * @return
	 * 			the department of the department group
	 */
	public Department getDepartment();

	/**
	 * @param department
	 * 			of the department group
	 */
	public void setDepartment(Department department);

	/**
	 * @return
	 * 			a list of staffs of the department group
	 */
	public List<Staff> getStaffs();

	/**
	 * @param staffs
	 * 			list of staffs for the department group
	 */
	public void setStaffs(List<Staff> staffs);

	/**
	 * Add a staff to the department group.
	 * @param staff
	 * 			the staff to be added
	 */
	public void addStaff(Staff staff);
	
	/**
	 * @param name
	 * 			of the staff
	 * @return
	 * 			the selected staff if it exists or null if it does not exist
	 */
	public Staff getStaff(String name);

	/**
	 * @return
	 * 			the encrypted group keys from the staffs of the department group
	 */
	public List<String> getEncryptedGroupKeys();

	/**
	 * @param encryptedGroupKeys
	 * 			from the staffs of the department group
	 */
	public void setEncryptedGroupKeys(List<String> encryptedGroupKeys);

	/**
	 * Add an encrypted group key for a specified staff of the department group.
	 * @param key
	 * 			the encrypted group key
	 */
	public void addEncryptedGroupKey(String key);

	/**
	 * @param staff
	 * 			the staff
	 * @return
	 * 			the encrypted group key from the staff
	 */
	public String getEncryptedGroupKeyFromStaff(Staff staff);

	/**
	 * @return
	 * 			the owner of the department group
	 */
	public Staff getOwner();

	/**
	 * @param owner
	 * 			of the department group
	 */
	public void setOwner(Staff owner);

	/**
	 * @param staff
	 * 			the staff
	 * @return
	 * 			true if the staff is the owner of the department group
	 */
	public boolean isOwner(Staff staff);

	/**
	 * @param staff
	 * 			the staff
	 * @return
	 * 			true if the staff is a member of the department group
	 */
	public boolean isMember(Staff staff);

}