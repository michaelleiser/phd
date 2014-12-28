package org.bfh.phd.interfaces;

import java.util.List;

import org.bfh.phd.Department;
import org.bfh.phd.Patient;
import org.bfh.phd.Staff;

/**
 * A session controller is used for granting access on different kinds of data to different users.
 * 
 * @author leism3, koblt1
 */
public interface ISessionController {
	
	/**
	 * Log out the currently logged in user.
	 * @return
	 * 			the return page
	 */
	public String logout();
	
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
	 * The list of all departments.
	 * @return
	 * 			a list of departments
	 */
	public List<Department> getDepartments();
	
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
	 * The patient with the specified id.
	 * @param patientid
	 * 			of the patient
	 * @return
	 * 			the patient with the specified id
	 */
	public Patient getPatient(int patientid);
	
	/**
	 * Create a new patient within a department group.
	 * @param patient
	 * 			to be created
	 * @return		
	 * 			the return page
	 */
	public String createPatient(Patient patient);

	/**
	 * Update the patient.
	 * @param patient
	 * 			to be updated
	 */
	public void updatePatient(Patient patient);

	/**
	 * @return
	 * 			true if the active staff is the owner of the active group
	 */
	public boolean isOwnerOfGroup();

	/**
	 * @param p
	 * 			the patient
	 * @return
	 * 			true if the active staff is the owner of the patient
	 */
	public boolean isOwner(Patient p);

	/**
	 * @param p
	 * 			the patient
	 * @return
	 * 			true if the active staff has read access for the patient
	 */
	public boolean readAccess(Patient p);

	/**
	 * @param p
	 * 			the patient
	 * @return
	 * 			true if the active staff has write access for the patient
	 */
	public boolean writeAccess(Patient p);

	/**
	 * @param p
	 * 			the patient
	 * @return 
	 * 			true if the active staff has insert access for the patient
	 */
	public boolean insertAccess(Patient p);

}
