package org.bfh.phd;

public class Group {

	private Staff staff;
	private Patient patient;
	private boolean owner;
	private boolean rwaccess;
	
	public Staff getStaff() {
		return staff;
	}
	
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	
	public Patient getPatient() {
		return patient;
	}
	
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	public boolean getOwner() {
		return owner;
	}
	
	public void setOwner(boolean owner) {
		this.owner = owner;
	}
	
	public boolean getRwaccess() {
		return rwaccess;
	}

	public void setRwaccess(boolean rwaccess) {
		this.rwaccess = rwaccess;
	}
	
	@Override
	public String toString(){
		return staff + ":" + patient + ":" + owner + ":" + rwaccess;
	}
	
}
