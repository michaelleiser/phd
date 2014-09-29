package org.bfh.phd;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "loginController", eager = true)
@SessionScoped
public class LoginController implements Serializable {

	private static final long serialVersionUID = 1L;
	private static boolean loggedin = false;
	private static int role = 0;
	
	private int patientid;
	private int patientdataid;

	private EntityManager em;
	
	public LoginController() {
		em = new EntityManager();
	}
	
//	@ManagedProperty(value="#{navigation}")
//	private Navigation navigation;
	
	public String login(String name, String password) {
		Staff s = em.getStaff(name, password);
		if(s != null){
			setLoggedin(true);
			setRole(s.getRole());
			return "loggedin";
		}
		return "home";
	}
	
	public String logout(){	
		setLoggedin(false);
		return "home";
	}
	
	public boolean getLoggedin(){
		return this.loggedin;
	}
	
	public void setLoggedin(boolean loggedin){
		this.loggedin = loggedin;
	}

	public String registernew(String name, String password, int i) {
		em.registernew(name, password, i);
		return "home";
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}
	
	public int getPatientid() {
		return patientid;
	}

	public void setPatientid(int patientid) {
		this.patientid = patientid;
	}
	
	public List<Patient> getPatient(){	// TODO return List wegen JSF Darstellung
		System.out.println("GetPatient " + patientid);
		List<Patient> l = new ArrayList<Patient>();
		l.add(em.getPatient(patientid));
		return l;
	}

	public List<Patient> getPatients(){
		System.out.println("GetPatients");
		List<Patient> l = em.getPatients();
		return l;
	}
	
	public String getPatientAndModify(int id){
		System.out.println("GetPatientAndModify " + id);
		patientid = id;
		return "";
	}
	
	public String getPatientDataWithId(int id){
		System.out.println("GetPatientDataWithid " + id);
		patientdataid = id;
		return "";
	}
	
	public List<PatientData> getPatientDatas() {
		System.out.println("GetPatientDatas " + patientid);
		List<PatientData> l = em.getPatientDatas(patientid);
		return l;
	}

	
	public List<PatientData> getPatientData(){
		System.out.println("GetPatientData " + patientdataid);
		List<PatientData> l = em.getPatientData(patientdataid);
		return l;
	}

	public int getPatientdataid() {
		return patientdataid;
	}

	public void setPatientdataid(int patientdataid) {
		this.patientdataid = patientdataid;
	}
	
	
	public void updatePatient(Patient p){				// TODO
		System.out.println("update pateint..." + p);
		em.updatePatient(p);
	}
	
	public void updatePatientData(PatientData pd){		// TODO
		System.out.println("update pateint data..." + pd);
		em.updatePatientData(pd);
	}
	
	
	public List<PatientData> searchPatientData(String op){		// TODO
		System.out.println("SEARCHING Patient Data..." + op);
		List<PatientData> l = em.searchPatientData(op);
		return l;
	}

	public List<Patient> searchPatient(String firstname) {		// TODO
		System.out.println("SEARCHING Patient..." + firstname);
		List<Patient> l = em.searchPatient(firstname);
		return l;
	}

	public void createPatient(Patient p) {
		em.createPatient(p);
	}

	public void createPatientData(Patient p, PatientData pd) {
		em.createPatientData(p, pd);
	}
	
//	public void setNavigation(Navigation navigation){
//		this.navigation = navigation;
//	}
//	
//	public Navigation getNavigation(){
//		return this.navigation;
//	}
}
