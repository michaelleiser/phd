package org.bfh.phd.interfaces;

import java.util.List;

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
	public List<Staff> getStaff();

	/**
	 * @param id
	 * 			id of the staff
	 * @return
	 * 			a staff with the specified id
	 */
	public Staff getStaff(int id);

	/**
	 * @param name
	 * 			the name of the staff
	 * @param password
	 * 			the password of the staff
	 * @return
	 * 			a staff with the specified name and password
	 */
	public Staff getStaff(String name, String password);

	/**
	 * @param name
	 * 			the name to filter the return list
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
	public List<Patient> getPatient(Staff activeUser);

	/**
	 * @return
	 * 			a list of all patients
	 */
	public List<Patient> getPatient();

	/**
	 * @param activeUser
	 * 			the active user
	 * @param patientid
	 * 			the id of the patient
	 * @return
	 * 			a patient with the the specified id readable by the active user
	 */
	public Patient getPatient(Staff activeUser, int patientid);

	/**
	 * registers a new staff
	 * 
	 * @param name
	 * 			the name of the staff
	 * @param password
	 * 			the password of the staff
	 * @param i
	 * 			the role of the staff
	 */
	public void registernew(String name, String password, int i);

	/**
	 * updates the active user
	 * 
	 * @param activeUser
	 * 			the active user
	 */
	public void updateStaff(Staff activeUser);

	/**
	 * updates the patient writeable by the active user
	 * 
	 * @param activeUser
	 * 			the active user
	 * @param p
	 * 			the patient to be updated
	 */
	public void updatePatient(Staff activeUser, Patient p);

	/**
	 * @param activeUser
	 * 			the active user
	 * @param name
	 * 			the name to be filtered
	 * @return
	 * 			a list of all patients readable by the active user that contain the name
	 */
	public List<Patient> searchPatient(Staff activeUser, String name);

	/**
	 * creates a new patient from the active user
	 * 
	 * @param p
	 * 			the patient to be created
	 * @param activeUser
	 * 			the active user
	 */
	public void createPatient(Patient p, Staff activeUser);

}
