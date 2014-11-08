package org.bfh.phd.interfaces;

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
	public String registernew(Staff staff);

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
	public String registernewWithDepartment(Staff staff, Department department, String groupKey);

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
	 * Update a staff.
	 * @param staff
	 * 			to be updated
	 */
	public void updateStaff(Staff staff);

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
	
}