package org.bfh.phd;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.bfh.phd.questionary.Answer;
import org.bfh.phd.questionary.Elbow;
import org.bfh.phd.questionary.Knee;
import org.bfh.phd.questionary.Question;

@ManagedBean(name = "loginController", eager = true)
@SessionScoped
public class LoginController implements Serializable {

	private static final long serialVersionUID = 1L;
	private static boolean loggedin = false;
	
//	private int patientid;			// TODO remove this
	private int patientdataid;		// TODO remove this
	
	private static Staff activeUser;
	private Patient activePatient;
	private PatientData activePatientData;
	private int patientenid;
	private String s = "knee";

	private EntityManager em;
	
	public LoginController() {
		em = new EntityManager();
	}
	
////	@ManagedProperty(value="#{navigation}")
////	private Navigation navigation;
	
	
	public void setStaff(Staff s){
		activeUser = s;
	}
	public Staff getStaff(){
		return this.activeUser;
	}
	
	public List<Staff> getStaffs(){
		if(this.loggedin == true){
			return em.getStaff();
		}
		return null;
	}
	public List<Staff> getStaffs(String name){
		if(this.loggedin == true){
			return em.getStaffs(name);
		}
		return null;
	}
	public List<Staff> getStaffsNotInGroup(String name){
		if(this.loggedin == true){
			return em.getStaffsNotInGroup(activeUser, activePatient, name);
		}
		return null;
	}

	public void addStaffToGroup(Staff staff){
		if(this.loggedin == true && this.isRWAccess(activePatient)){
			em.addStaffToGroup(staff, activePatient);
		}
	}
	public void removeStaffFromGroup(Staff staff){
		if(this.loggedin == true && this.isRWAccess(activePatient)){
			em.removeStaffFromGroup(staff, activePatient);
		}
	}
	
	public String login(String name, String password) {
		activeUser = em.getStaff(name, password);
		if(activeUser != null){
			setLoggedin(true);
			return "/loggedin";
		}
		return "/home";
	}
	
	public String logout(){	
		setLoggedin(false);
		return "/home";
	}
	
	public boolean getLoggedin(){
		return this.loggedin;
	}
	
	private void setLoggedin(boolean loggedin){
		this.loggedin = loggedin;
	}

	public String registernew(String name, String password, int i) {
		em.registernew(name, password, i);
		return "/home";
	}

//	public int getPatientid() {
//		return patientid;
//	}
//	public void setPatientid(int patientid) {
//		this.patientid = patientid;
//	}
	public int getPatientdataid() {
		return patientdataid;
	}
	public void setPatientdataid(int patientdataid) {
		this.patientdataid = patientdataid;
	}
	
	
	public Patient getPatient(int patientid){
		System.out.println("GetPatient " + patientid);
		if(this.loggedin == true && activeUser.getRole() == 1){
			return em.getPatient(patientid);
		}
		return null;
	}

	public List<Patient> getPatients(){
		System.out.println("GetPatients");
		if(this.loggedin == true && activeUser.getRole() == 1){
			List<Patient> l = em.getPatient();
			return l;
		}
		return null;
	}
	
	public List<Patient> getPatientsWithRestrictions() {
		System.out.println("GetPatients");
		if(this.loggedin == true && activeUser.getRole() == 1){
			List<Patient> l = em.getPatientsWithRestrictions(activeUser);
			return l;
		}
		return null;
	}
	
//	public String getPatientAndModify(int id){
//		System.out.println("GetPatientAndModify " + id);
//		patientid = id;
//		return "";
//	}
	
	// TODO if possible remove this method
	public String getPatientDataWithId(int id){		
		System.out.println("GetPatientDataWithid " + id);
		
		patientdataid = id;
		return "";
	}
	
	public List<PatientData> getPatientDatas() {
		if(this.loggedin == true && activePatient != null){
			System.out.println("GetPatientDatas " + activePatient.getPatientid());
			List<PatientData> l = em.getPatientDatas(activePatient.getPatientid());
			return l;
		}
		return null;
	}

	
	public List<PatientData> getPatientData(){
		System.out.println("GetPatientData " + patientdataid);
		if(this.loggedin == true){
			List<PatientData> l = em.getPatientData(patientdataid);
			return l;
		}
		return null;
	}
	
	public void updatePatient(Patient p){
		System.out.println("update pateint..." + p);
		if(this.loggedin == true){		// TODO rW access
			em.updatePatient(p);
		}
	}

	public void updatePatientData(PatientData pd){
		System.out.println("update pateint data..." + pd);
		if(this.loggedin == true){		// TODO rW access
			em.updatePatientData(pd);
		}
	}
	
	
//	public List<PatientData> searchPatientData(String op){		// TODO
//		System.out.println("SEARCHING Patient Data..." + op + from + to);
//		List<PatientData> l = em.searchPatientData(op);
//		return l;
//	}
	
	public List<PatientData> searchPatientData(String op){		// TODO
		System.out.println("SEARCHING Patient Data..." + op + from + "--" + to);
		if(this.loggedin == true){
			List<PatientData> l = em.searchPatientData(op, from , to);
			return l;
		}
		return null;
	}

	public List<Patient> searchPatient(String name) {		// TODO
		System.out.println("SEARCHING Patient..." + name);
		if(this.loggedin == true && activeUser.getRole() == 1){
			List<Patient> l = em.searchPatient(name);
			return l;
		}
		return null;
	}
	public List<Patient> searchPatientWithRestrictions(String name) {		// TODO
		System.out.println("SEARCHING Patient..." + name);	
		if(this.loggedin == true && activeUser.getRole() == 1){
			List<Patient> l = em.searchPatientWithRestrictions(name, activeUser);
			return l;
		}
		return null;
	}

	public String createPatient(Patient p) {
		System.out.println(">> " + p);
		System.out.println(">> " + activeUser);
		if(this.loggedin == true && activeUser.getRole() == 1){
			em.createPatient(p, activeUser);
			return "/loggedin";
		}
		return null;
	}

	public void createPatientData(Patient p, PatientData pd) {
//		if(this.loggedin == true && activeUser.getRole() == 1){
//			em.createPatientData(p, pd);
//		}
	}
	
	public List<Tools> getFilledQuestion(){
		return em.getFilledQuestion(patientenid);
	}

	public List<Question> getQuestions() {
		return em.getQuestions(s);
	}

	public List<Answer> getAnswers(int i) {
		return em.getAnswers(s, i);
	}
	
	public void safe(ActionEvent evt){
		System.out.println("->"+evt );
	}
	
//	public void setNavigation(Navigation navigation){
//		this.navigation = navigation;
//	}
//	
//	public Navigation getNavigation(){
//		return this.navigation;
//	}
	
	

	public List<Group> getGroups(){
		if(this.loggedin == true && activeUser.getRole() == 1){
			return em.getGroups(activeUser, activePatient);
		}
		return null;
	}
	
	
	
	public Patient getActivePatient() {
		if(this.loggedin == true && activeUser.getRole() == 1){
			return activePatient;
		}
		return null;
	}

	public void setActivePatient(Patient activePatient) {
		if(this.loggedin == true && activeUser.getRole() == 1){
			this.activePatient = activePatient;
		}
	}
	


	public PatientData getActivePatientData() {
		if(this.loggedin == true){
			return activePatientData;
		}
		return null;
	}

	public void setActivePatientData(PatientData activePatientData) {
		if(this.loggedin == true){
			this.activePatientData = activePatientData;
		}
	}
	
	private Date from;
	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}

	private Date to;

	public List<Elbow> searchPatientData2(String op) {
		System.out.println("SEARCHING Patient Data..." + op + from + "--" + to);
		List<Elbow> l = em.searchPatientData2(op);
		return l;
	}
	public List<Knee> searchPatientData3(String op) {
		System.out.println("SEARCHING Patient Data..." + op + from + "--" + to);
		List<Knee> l = em.searchPatientData3(op);
		return l;
	}

	
	
	public boolean isOwner(Patient p){
		if(this.loggedin == true && activeUser.getRole() == 1){
			return em.isOwner(activeUser, p);
		}
		return false;
	}
	public boolean isRWAccess(Patient p){
		if(this.loggedin == true && activeUser.getRole() == 1){
			return em.rwAccess(activeUser, p);
		}
		return false;
	}
	


	public void addAnswer(String string, Knee k) {
		if(this.loggedin == true && activeUser.getRole() == 1){
			em.addAnswer("knee", k.getAnswers());
		}
	}

	public List<Knee> getAnswers(String string) {
		if(this.loggedin == true){
			return em.getAnswers(string);
		}
		return null;
	}

	public List<Patient> getPatientsWithRWAccess() {
		if(this.loggedin == true){			// TODO rw access
			return em.getPatientsWithRWAccess(activeUser);
		}
		return null;
	}

}
