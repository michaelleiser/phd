package org.bfh.phd;

public class Group {

	private Staff staff;
	private Patient patient;
	private boolean owner;
	
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
	
	@Override
	public String toString(){
		return staff + ":" + patient + ":" + owner;
	}
	
}
