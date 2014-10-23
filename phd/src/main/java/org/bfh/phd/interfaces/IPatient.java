package org.bfh.phd.interfaces;

import java.util.List;

import org.bfh.phd.PatientData;

/**
 * @author leism3, koblt1
 *
 */
public interface IPatient {

	/**
	 * @return
	 */
	public int getPatientid();

	/**
	 * @param patientid
	 */
	public void setPatientid(int patientid);

	/**
	 * @return
	 */
	public String getFirstname();

	/**
	 * @param firstname
	 */
	public void setFirstname(String firstname);

	/**
	 * @return
	 */
	public String getLastname();

	/**
	 * @param lastname
	 */
	public void setLastname(String lastname);

	/**
	 * @return
	 * 			a list of data from the patient
	 */
	public List<PatientData> getPatientData();

	/**
	 * @param patientData
	 * 			a list of data for the patient
	 */
	public void setPatientData(List<PatientData> patientData);

	/**
	 * adds a new data entry for the patient
	 * 
	 * @param patientData
	 * 			a data entry
	 */
	public void addPatientData(PatientData patientData);

}