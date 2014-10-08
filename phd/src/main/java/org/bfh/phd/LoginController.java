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
		return em.getStaff();
	}
	public List<Staff> getStaffs(String name){
		return em.getStaffs(name);
	}

	public void addStaffToGroup(Staff staff){
		em.addStaffToGroup(staff, activePatient);
	}
	public void removeStaffFromGroup(Staff staff){
		em.removeStaffFromGroup(staff, activePatient);
	}
	
	public String login(String name, String password) {
		activeUser = em.getStaff(name, password);
		if(activeUser != null){
			setLoggedin(true);
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
		return em.getPatient(patientid);
	}

	public List<Patient> getPatients(){
		System.out.println("GetPatients");
		List<Patient> l = em.getPatient();
		return l;
	}
	
	public List<Patient> getPatientsWithRestrictions() {
		System.out.println("GetPatients");
		List<Patient> l = em.getPatientsWithRestrictions(activeUser);
		return l;
	}
	
//	public String getPatientAndModify(int id){
//		System.out.println("GetPatientAndModify " + id);
//		patientid = id;
//		return "";
//	}
	
	public String getPatientDataWithId(int id){
		System.out.println("GetPatientDataWithid " + id);
		patientdataid = id;
		return "";
	}
	
	public List<PatientData> getPatientDatas() {
		if(activePatient != null){
			System.out.println("GetPatientDatas " + activePatient.getPatientid());
			List<PatientData> l = em.getPatientDatas(activePatient.getPatientid());
			return l;
		}
		return null;
	}

	
	public List<PatientData> getPatientData(){
		System.out.println("GetPatientData " + patientdataid);
		List<PatientData> l = em.getPatientData(patientdataid);
		return l;
	}
	
	public void updatePatient(Patient p){
		System.out.println("update pateint..." + p);
		em.updatePatient(p);
	}

	public void updatePatientData(PatientData pd){
		System.out.println("update pateint data..." + pd);
		em.updatePatientData(pd);
	}
	
	
//	public List<PatientData> searchPatientData(String op){		// TODO
//		System.out.println("SEARCHING Patient Data..." + op + from + to);
//		List<PatientData> l = em.searchPatientData(op);
//		return l;
//	}
	
	public List<PatientData> searchPatientData(String op){		// TODO
		System.out.println("SEARCHING Patient Data..." + op + from + "--" + to);
		List<PatientData> l = em.searchPatientData(op, from , to);
		return l;
	}

	public List<Patient> searchPatient(String name) {		// TODO
		System.out.println("SEARCHING Patient..." + name);
		List<Patient> l = em.searchPatient(name);
		return l;
	}
	public List<Patient> searchPatientWithRestrictions(String name) {		// TODO
		System.out.println("SEARCHING Patient..." + name);
		List<Patient> l = em.searchPatientWithRestrictions(name, activeUser);
		return l;
	}

	public void createPatient(Patient p) {
		System.out.println(">> " + p);
		System.out.println(">> " + activeUser);
		em.createPatient(p, activeUser);
	}

	public void createPatientData(Patient p, PatientData pd) {
		em.createPatientData(p, pd);
	}
	
	public void getQuest(String s) {
		em.getQuestions(s);
	}

	public List<Question> getQuestions(String s) {
		return em.getQuestions(s);
	}

	public List<Answer> getAnswers(String s, int i) {
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
		return em.getGroups(activeUser, activePatient);
	}
	
	
	
	public Patient getActivePatient() {
		return activePatient;
	}

	public void setActivePatient(Patient activePatient) {
		this.activePatient = activePatient;
	}
	


	public PatientData getActivePatientData() {
		return activePatientData;
	}

	public void setActivePatientData(PatientData activePatientData) {
		this.activePatientData = activePatientData;
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
		return em.isOwner(activeUser, p);
	}

	


	public void addAnswer(String string, Knee k) {
		em.addAnswer("knee", k.getAnswers());
	}

	public List<Knee> getAnswers(String string) {
		return em.getAnswers(string);
	}

}
