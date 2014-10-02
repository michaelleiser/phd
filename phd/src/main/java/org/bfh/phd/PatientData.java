package org.bfh.phd;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "patientdata", eager = true)
@RequestScoped
public class PatientData {
	
	private int patientdata_id;
	private String firstdata;
	private String seconddata;
	private Date inserttime;

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

	public Date getInserttime() {
		return inserttime;
	}

	public void setInserttime(Date inserttime) {
		this.inserttime = inserttime;
	}
	
}