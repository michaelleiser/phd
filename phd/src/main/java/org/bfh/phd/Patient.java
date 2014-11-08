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
	
	@Override
	public Staff getOwner() {
		return this.owner;
	}

	@Override
	public void setOwner(Staff owner) {
		this.owner = owner;
	}
	
	@Override
	public Department getDepartment() {
		return this.department;
	}

	@Override
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	@Override
	public boolean getReadaccess() {
		return this.readaccess;
	}

	@Override
	public void setReadaccess(boolean readaccess) {
		this.readaccess = readaccess;
	}

	@Override
	public boolean getWriteaccess() {
		return this.writeaccess;
	}

	@Override
	public void setWriteaccess(boolean writeaccess) {
		this.writeaccess = writeaccess;
	}
	
	@Override
	public boolean getInsertaccess() {
		return this.insertaccess;
	}

	@Override
	public void setInsertaccess(boolean insertaccess) {
		this.insertaccess = insertaccess;
	}

	@Override
	public List<PatientData> getPatientData() {
		return this.patientData;
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
		return this.patientid + " : " + this.personalData;
	}

}