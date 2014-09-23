package org.phd.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "loginController", eager = true)
@SessionScoped
public class LoginController implements Serializable {

	private static final long serialVersionUID = 1L;
	private static boolean loggedin = false;
	private static String role = "default";
	
	private int patientid;

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

	public String registernew(String name, String password, String role) {
		em.registernew(name, password, role);
		return "home";
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public int getPatientid() {
		return patientid;
	}

	public void setPatientid(int patientid) {
		this.patientid = patientid;
	}
	
	public List<Patient> getPatient(){	// TODO return List wegen JSF Darstellung
		List<Patient> l = new ArrayList<Patient>();
		l.add(em.getPatient(patientid));
		return l;
	}
	
	public List<Patient> getPatients(){
		List<Patient> l = em.getPatients();
		return l;
	}
	
	
//	public void setNavigation(Navigation navigation){
//		this.navigation = navigation;
//	}
//	
//	public Navigation getNavigation(){
//		return this.navigation;
//	}
}
