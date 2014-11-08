package org.bfh.phd.interfaces;

import java.util.List;

import org.bfh.phd.Department;
import org.bfh.phd.Staff;

/**
 * @author leism3, koblt1
 *
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
	public List<Staff> getStaff();

	/**
	 * @param staff
	 * 			list of staffs for the department group
	 */
	public void setStaff(List<Staff> staff);

	/**
	 * Add a staff to the department group.
	 * @param staff
	 * 			the staff to be added
	 */
	public void addStaff(Staff staff);
	
	/**
	 * @param name
	 * 			of the staff
	 * @param password
	 * 			of the staff
	 * @return
	 * 			the selected staff if it exists or null if it does not exist
	 */
	public Staff getStaff(String name, String password);

	/**
	 * @return
	 * 			the encrypted group keys from the staffs of the department group
	 */
	public List<String> getEncryptedGroupKey();

	/**
	 * @param encryptedGroupKey
	 * 			from the staffs of the department group
	 */
	public void setEncryptedGroupKey(List<String> encryptedGroupKey);

	/**
	 * Add an encrypted group key for a specified staff of the department group.
	 * @param key
	 * 			the encrypted group key
	 */
	public void addEncryptedGroupKey(String key);

	/**
	 * @param staff
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
	 * @return
	 * 			true if the staff is the owner of the department group
	 */
	public boolean isOwner(Staff staff);

	/**
	 * @param staff
	 * @return
	 * 			true if the staff is a member of the department group
	 */
	public boolean isMember(Staff staff);

}