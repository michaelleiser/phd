package org.bfh.phd.interfaces;

import java.util.List;

import org.bfh.phd.Department_Has_Staff;
import org.bfh.phd.Patient;
import org.bfh.phd.Staff;

/**
 * @author leism3, koblt1
 *
 */
public interface IEntityManager {

	/**
	 * @return
	 * 			a list of all staffs
	 */
	public List<Staff> getStaffs();

	/**
	 * @param id
	 * 			of the staff
	 * @return
	 * 			a staff with the specified id
	 */
	public Staff getStaff(int id);

	/**
	 * @param name
	 * 			of the staff
	 * @param password
	 * 			of the staff
	 * @return
	 * 			a staff with the specified name and password or null if it does not exist
	 */
	public Staff getStaff(String name, String password);

	/**
	 * @param name
	 * 			to filter the return list
	 * @return
	 * 			a list of all staffs that contain the name
	 */
	public List<Staff> getStaffs(String name);

	/**
	 * @param activeUser
	 * 			the active user
	 * @return
	 * 			a list of all patients readable by the active user
	 */
	public List<Patient> getPatients(Staff activeUser);

	/**
	 * @return
	 * 			a list of all patients
	 */
	public List<Patient> getPatient();

	/**
	 * @param activeUser
	 * 			the active user
	 * @param patientid
	 * 			of the patient
	 * @return
	 * 			a patient with the the specified id readable by the active user
	 */
	public Patient getPatient(Staff activeUser, int patientid);

	/**
	 * Register a new staff.
	 * @param staff
	 * 			to be registered
	 * @param admin
	 * 			if is admin
	 * @return
	 * 			the registered staff
	 */
	public Staff registernew(Staff staff, boolean admin);

	/**
	 * Update the active user.
	 * @param activeUser
	 * 			the active user
	 */
	public void updateStaff(Staff activeUser);

	/**
	 * Update the patient which is writable by the active user.
	 * @param patient
	 * 			to be updated
	 * @param activeUser
	 * 			the active user
	 */
	public void updatePatient(Patient patient, Staff activeUser);

	/**
	 * @param dhs
	 * 			the department group
	 * @param activeUser
	 * 			the active user
	 * @return
	 * 			a list of all patients in the department group readable by the active user
	 */
	public List<Patient> searchPatients(Department_Has_Staff dhs, Staff activeUser);

	/**
	 * Create a new patient in the department group with the active user as the owner.
	 * @param patient
	 * 			the patient to be created
	 * @param dhs
	 * 			the department group
	 * @param activeUser
	 * 			the active user
	 */
	public void createPatient(Patient patient, Department_Has_Staff dhs, Staff activeUser);

}
