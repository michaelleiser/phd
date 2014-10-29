package org.bfh.phd;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.bfh.phd.interfaces.IPatient;

@ManagedBean(name = "patient", eager = true)
@RequestScoped
public class Patient implements Serializable, IPatient {

	private static final long serialVersionUID = 1L;

	private int patientid;
	private String personalData;
	
	private Staff owner;
	private Department department;
	
	private boolean readaccess;
	private boolean writeaccess;
	private boolean insertaccess;
	
	private List<PatientData> patientData = new ArrayList<PatientData>();

	public Patient() {
		
	}

	@Override
	public int getPatientid() {
		return this.patientid;
	}
	
	@Override
	public void setPatientid(int id)  {
		this.patientid = id;
	}

	@Override
	public String getPersonalData(){
		return this.personalData;
	}
	
	@Override
	public void setPersonalData(String personalData){
		this.personalData = personalData;
	}
	
	public Staff getOwner() {
		return owner;
	}

	public void setOwner(Staff owner) {
		this.owner = owner;
	}
	
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	public boolean getReadaccess() {
		return readaccess;
	}

	public void setReadaccess(boolean read) {
		this.readaccess = read;
	}

	public boolean getWriteaccess() {
		return writeaccess;
	}

	public void setWriteaccess(boolean write) {
		this.writeaccess = write;
	}
	
	public boolean getInsertaccess() {
		return insertaccess;
	}

	public void setInsertaccess(boolean insertaccess) {
		this.insertaccess = insertaccess;
	}

	@Override
	public List<PatientData> getPatientData() {
		return patientData;
	}

	@Override
	public void setPatientData(List<PatientData> patientData) {
		this.patientData = patientData;
	}
	
	@Override
	public void addPatientData(PatientData patientData){
		this.patientData.add(patientData);
	}

	@Override
	public String toString(){
		return patientid + " : " + personalData;
	}

}