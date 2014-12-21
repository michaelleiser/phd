package org.bfh.phd.interfaces;

import java.util.List;

import org.bfh.phd.Department;
import org.bfh.phd.Patient;
import org.bfh.phd.Staff;

/**
 * @author leism3, koblt1
 *
 */
public interface ILoginController {

	/**
	 * Log in a user with the specified name and password.
	 * @param name
	 * 			of the user
	 * @param password
	 * 			of the user
	 * @return
	 * 			the return page
	 */
	public String login(String name, String password);

	/**
	 * Log out the currently logged in user.
	 * @return
	 * 			the return page
	 */
	public String logout();

	/**
	 * Register a new staff in a specified department.
	 * @param staff
	 * 			to be registered
	 * @return
	 * 			the newly created staff
	 */
	public String registerNew(Staff staff);

	/**
	 * Register a new staff in a new department.
	 * @param staff
	 * 			to be registered
	 * @param department
	 * 			to be registered within
	 * @param groupKey
	 * 			of the department group
	 * @return
	 * 			the newly created staff
	 */
	public String registerNewWithDepartment(Staff staff, Department department, String groupKey);

	/**
	 * Activate a staff within a department group and provide the group key to decrypt patient information.
	 * @param staff
	 * 			to be activated
	 * @param groupKey
	 * 			of the department group
	 */
	public void activateStaff(Staff staff, String groupKey);

	/**
	 * Deactivate a staff within a department group and remove the provided group key.
	 * @param staff
	 * 			to be deactivated
	 */
	public void deactivateStaff(Staff staff);

	/**
	 * The list of all staffs.
	 * @return
	 * 			a list of staffs
	 */
	public List<Staff> getStaffs();

	/**
	 * The list of all staffs, that contain the filter name.
	 * @param name
	 * 			to be filtered
	 * @return
	 * 			a list of staffs
	 */
	public List<Staff> getStaffs(String name);

	/**
	 * The list of all staffs in the active group, that contain the filter name.
	 * @param name
	 * 			to be filtered
	 * @return
	 * 			a list of staffs
	 */
	public List<Staff> searchStaffsInGroup(String name);

	/**
	 * Update a staff.
	 * @param staff
	 * 			to be updated
	 * @param groupKey
	 * 			of the department group
	 */
	public void updateStaff(Staff staff, String groupKey);

	/**
	 * The list of all patients.
	 * @return
	 * 			a list of patients
	 */
	public List<Patient> getPatients();

	/**
	 * The list of all patients in the active group.
	 * @return
	 * 			a list of patients
	 */
	public List<Patient> searchPatients();

	/**
	 * Create a new patient within a department group.
	 * @param patient
	 * 			to be created
	 * @return		
	 * 			the return page
	 */
	public String createPatient(Patient patient);

	/**
	 * Update a patient.
	 * @param patient
	 * 			to be updated
	 */
	public void updatePatient(Patient patient);

	
	
	
	


	/**
	 * @return
	 * 			true if active staff is owner of active group
	 */
	public boolean isOwnerOfGroup();

	/**
	 * @param p
	 * 			the patient
	 * @return
	 * 			true if active staff is owner of patient
	 */
	public boolean isOwner(Patient p);

	/**
	 * 
	 * @param p
	 * 			the patient
	 * @return
	 * 			true if active staff has read access
	 */
	public boolean readAccess(Patient p);

	/**
	 * @param p
	 * 			the patient
	 * @return
	 * 			true if active staff has write access
	 */
	public boolean writeAccess(Patient p);

	/**
	 * @param p
	 * 			the patient
	 * @return 
	 * 			true if active staff has insert access
	 */
	public boolean insertAccess(Patient p);
	
}