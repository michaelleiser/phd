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
	
	private static Staff activeUser;
	private Patient activePatient;
	private PatientData activePatientData;
	private int questionnaireId;
	private String s = "knee";

	private Date to;
	private EntityManager em;
	
	
	//----- Methods----
	
	public LoginController() {
		em = new EntityManager();
	}
		
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

	public int getPatientdataid() {
		return 0;
		//TODO delet this
	}
	public void setPatientdataid(int patientdataid) {
		//this.patientdataid = patientdataid;
		//TODO delet this
	}
		
	public Patient getPatient(int patientid){
		System.out.println("GetPatient " + patientid);
		if((this.loggedin == true) && (activeUser.getRole() == 1)){
			return em.getPatient(activeUser, patientid);
		}
		return null;
	}

	public List<Patient> getPatients(){
		System.out.println("GetPatients");
		if((this.loggedin == true) && (activeUser.getRole() == 1)){
			List<Patient> l = em.getPatient(activeUser);
			return l;
		}
		return null;
	}
	
	public String getPatientDataWithId(int id){		
		System.out.println("Not Implementet LoginController line 140 ");
		//TODO delet
		return "";
	}
	
	public List<PatientData> getPatientDatas() {
		System.out.println("Not Implementet LoginController line 146 ");
		//TODO delet
		return null;
	}

	public List<ListOfQuestionnari> getPatientData(){
		if((this.loggedin == true) && (activePatient != null)){
		List<ListOfQuestionnari> l = em.searchData(activePatient.getPatientid());
			return l;
		}
		return null;
	}
	
	public void updateStaff(Staff activeUser){
		System.out.println("update staff..." + activeUser);
		if(this.loggedin == true){
			em.updateStaff(activeUser);
		}
	}
	
	public void updatePatient(Patient p){
		System.out.println("update patient..." + p);
		if(this.loggedin == true){
			em.updatePatient(activeUser, p);
		}
	}

	public void updateQuestionnaire(Questionnari q){
		if(this.loggedin == true && activeUser.getRole() == 1){
			em.updateQuestionnaire(q);
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
			List<Patient> l = em.searchPatient(activeUser, name);
			return l;
		}
		return null;
	}

	public String createPatient(Patient p) {
		System.out.println(">> " + p);
		System.out.println(">> " + activeUser);
		if((this.loggedin == true) && (activeUser.getRole() == 1)){
			em.createPatient(p, activeUser);
			return "/loggedin";
		}
		return null;
	}

	public void createPatientData(Patient p, PatientData pd) {
		System.out.println("Not Implementet LoginController line 227 ");
		//TODO delet
//		if(this.loggedin == true && activeUser.getRole() == 1){
//			em.createPatientData(p, pd);
//		}
	}
	
	public List<Tools> getFilledQuestion(){
		return em.getFilledQuestion(questionnaireId);
	}

	public List<Question> getQuestions() {
		return em.getQuestions(s);
	}
	
	public List<Question> getQuestions(String s) {
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

	public boolean readAccess(Patient p){
		if(this.loggedin == true && activeUser.getRole() == 1){
			return em.readaccess(activeUser, p);
		}
		return false;
	}
	public boolean writeAccess(Patient p){
		if(this.loggedin == true && activeUser.getRole() == 1){
			return em.writeaccess(activeUser, p);
		}
		return false;
	}
	public boolean insertAccess(Patient p){
		if(this.loggedin == true && activeUser.getRole() == 1){
			return em.insertaccess(activeUser, p);
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
	
	public void setPatientid(int i){
		this.questionnaireId = i;
	}

	public int getPatientid(){
		return this.questionnaireId;
	}

	public int getQuestionnariId() {
		return questionnaireId;
	}

	public void setQuestionnariId(int questionnariId) {
		this.questionnaireId = questionnariId;
	}
}
