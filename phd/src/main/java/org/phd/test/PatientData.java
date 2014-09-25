package org.phd.test;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "patientdata", eager = true)
@SessionScoped
public class PatientData {
	
	private int patientdata_id;
	private String firstdata;
	private String seconddata;

	public PatientData() {
		
	}
	
	public int getPatientdata_id() {
		return patientdata_id;
	}

	public void setPatientdata_id(int patientdata_id) {
		this.patientdata_id = patientdata_id;
	}

	public String getFirstdata() {
		return firstdata;
	}

	public void setFirstdata(String firstdata) {
		this.firstdata = firstdata;
	}

	public String getSeconddata() {
		return seconddata;
	}

	public void setSeconddata(String seconddata) {
		this.seconddata = seconddata;
	}

	@Override
	public String toString() {
		return patientdata_id + " : " + firstdata + " : " + seconddata;
	}
	
}
