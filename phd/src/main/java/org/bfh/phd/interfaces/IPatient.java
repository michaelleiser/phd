package org.bfh.phd.interfaces;

import org.bfh.phd.Department;
import org.bfh.phd.Staff;

/**
 * A patient has some personal information and can be threatened by one or more doctors.
 * 
 * @author leism3, koblt1
 */
public interface IPatient {

	/**
	 * @return
	 * 			id of the patient
	 */
	public int getPatientid();

	/**
	 * @param patientid
	 * 			of the patient
	 */
	public void setPatientid(int patientid);
	
	/**
	 * @return
	 * 			personal data of the patient
	 */
	public String getPersonalData();

	/**
	 * @param personalData
	 * 			of the patient
	 */
	public void setPersonalData(String personalData);
	
	/**
	 * @return
	 * 			the owner of the patient
	 */
	public Staff getOwner();

	/**
	 * @param owner
	 * 			of the patient
	 */
	public void setOwner(Staff owner);

	/**
	 * @return
	 * 			the department of the patient
	 */
	public Department getDepartment();

	/**
	 * @param department
	 * 			of the patient
	 */
	public void setDepartment(Department department);

	/**
	 * @return
	 * 			true if the patient is read accessible
	 */
	public boolean getReadaccess();

	/**
	 * Set read access value for the patient.
	 * @param readaccess
	 */
	public void setReadaccess(boolean readaccess);

	/**
	 * @return
	 * 			true if the patient is write accessible
	 */
	public boolean getWriteaccess();

	/**
	 * Set write access value for the patient.
	 * @param writeaccess
	 */
	public void setWriteaccess(boolean writeaccess);

	/**
	 * @return
	 * 			true if the patient data is insert accessible
	 */
	public boolean getInsertaccess();

	/**
	 * Set insert access value for the patient.
	 * @param insertaccess
	 */
	public void setInsertaccess(boolean insertaccess);
	
}