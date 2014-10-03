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
	private static int role = 0;
	
	private int patientid;
	private int patientdataid;

	private EntityManager em;
	
	public LoginController() {
		em = new EntityManager();
	}
	
//	@ManagedProperty(value="#{navigation}")
//	private Navigation navigation;
	
	private Staff staff;
	
	public void setStaff(Staff s){
		staff = s;
	}
	public Staff getStaff(){
		return this.staff;
	}
	
	public String login(String name, String password) {
		staff = em.getStaff(name, password);
		if(staff != null){
			setLoggedin(true);
			setRole(staff.getRole());
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
	
	
	
	private int first1 = 0;
	private int first2 = 0;
	
	public String forward1(){
		first1 = first1 + 10;
		return null;
	}
	
	public String backward1(){
		first1 = first1 - 10;
		if(first1 < 0){
			first1 = 0;
		}
		return null;
	}	
	
	public int getFirst1() {
		return first1;
	}

	public void setFirst1(int first1) {
		this.first1 = first1;
	}
	
	public String forward2(){
		first2 = first2 + 10;
		return null;
	}
	
	public String backward2(){
		first2 = first2 - 10;
		if(first2 < 0){
			first2 = 0;
		}
		return null;
	}
	
	public int getFirst2() {
		return first2;
	}

	public void setFirst2(int first2) {
		this.first2 = first2;
	}


	
	private Patient activePatient;

	public Patient getActivePatient() {
		return activePatient;
	}

	public void setActivePatient(Patient activePatient) {
		this.activePatient = activePatient;
	}
	
	
	private PatientData activePatientData;

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
}
