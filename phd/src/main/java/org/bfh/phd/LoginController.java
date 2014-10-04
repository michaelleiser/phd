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
	
	private Patient activePatient;
	private PatientData activePatientData;

	private EntityManager em;
	
	public LoginController() {
		em = new EntityManager();
	}
	
//	@ManagedProperty(value="#{navigation}")
//	private Navigation navigation;
	
//	private Staff staff;
//	
//	public void setStaff(Staff s){
//		staff = s;
//	}
//	public Staff getStaff(){
//		return this.staff;
//	}
	
	public String login(String name, String password) {
		Staff staff = em.getStaff(name, password);
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
			
			first2size = l.size();
			
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

		first1size = l.size();
		
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

	
	
	
	
	
	
	
	
	
	private int pagesize = 10;
	
	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	private int pagenr1 = 1;
	public int getPagenr1() {
		return pagenr1;
	}
	public void setPagenr1(int pagenr1) {
		this.pagenr1 = pagenr1;
	}
	
	private int pagenr2 = 1;
	public int getPagenr2() {
		return pagenr2;
	}
	public void setPagenr2(int pagenr2) {
		this.pagenr2 = pagenr2;
	}

	
	private int first1 = 0;
	private int first1size = 0;
	public String forward1(){
		first1 = first1 + pagesize;
		pagenr1++;
		return null;
	}
	public String backward1(){
		first1 = first1 - pagesize;
		if(first1 < 0){
			first1 = 0;
		}
		pagenr1--;
		return null;
	}	
	public int getFirst1() {
		return first1;
	}
	public void setFirst1(int first1) {
		this.first1 = first1;
	}
	
	private int first2 = 0;
	private int first2size = 0;
	public String forward2(){
		first2 = first2 + pagesize;
		pagenr2++;
		return null;
	}
	public String backward2(){
		first2 = first2 - pagesize;
		if(first2 < 0){
			first2 = 0;
		}
		pagenr2--;
		return null;
	}
	public int getFirst2() {
		return first2;
	}
	public void setFirst2(int first2) {
		this.first2 = first2;
	}
	public int getFirst1size() {
		return first1size;
	}

	public void setFirst1size(int first1size) {
		this.first1size = first1size;
	}

	public int getFirst2size() {
		return first2size;
	}

	public void setFirst2size(int first2size) {
		this.first2size = first2size;
	}
	public boolean hasPrevious1(){
		return first1 > 0;
	}
	public boolean hasNext1(){
		return ((first1size - first1) - pagesize) > 0;
	}
	public boolean hasPrevious2(){
		return first2 > 0;
	}
	public boolean hasNext2(){
		return ((first2size - first2) - pagesize) > 0;
	}

}
