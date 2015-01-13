package org.bfh.phd;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

import org.bfh.crypto.MyCrypto;
import org.bfh.phd.interfaces.IAnswer;
import org.bfh.phd.interfaces.IFilledQuestionnaire;
import org.bfh.phd.interfaces.ILoginController;
import org.bfh.phd.interfaces.IQuestion;
import org.bfh.phd.interfaces.ISessionController;
import org.bfh.phd.questionnaire.QuestionnaireTools;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * The Login controller is used for login, logout, registering, accessibility on patients and patient's datas, session management, request filtering etc..
 * 
 * @author leism3, koblt1
 */
@ManagedBean(name = "loginController", eager = true)
@SessionScoped
public class LoginController implements Serializable, ILoginController, ISessionController {

	private static final long serialVersionUID = 1L;
	private String nonce;
	private boolean loggedin = false;

	private Staff activeUser;
	private Patient activePatient;
	private int questionnaireId;
	private String questionnaireName = "";

	private String departmentselected;
	private Department_Has_Staff activeDepartment_Has_Staff;
	
	private EntityManager em;
	private DownloadManager dm;

	private Navigation navigation;
 
	public LoginController() {
		this.em = new EntityManager();
		this.dm = new DownloadManager();
		this.nonce = UUID.randomUUID().toString();
		this.navigation = new Navigation();
	}

	@Override
	public String login(String name, String password) {
		System.out.println(new Date() + " : LOGIN : " + name + " : " + this.activeDepartment_Has_Staff);
		if(activeDepartment_Has_Staff != null){
			activeUser = activeDepartment_Has_Staff.getStaff(name);
			if(activeUser != null && activeUser.getActivated() && password.equals(MyCrypto.SHA256(nonce + "" + activeUser.getPassword())) && checkToken()){
				setLoggedin(true);			
				return navigation.redirectToLoggedin();
			}
		}
		return navigation.redirectToHome();
	}

	@Override
	public String logout(){
		System.out.println(new Date() + " : LOGOUT : " + this.activeUser);
		if(this.loggedin && checkToken()){
			setLoggedin(false);
			if(FacesContext.getCurrentInstance() != null){
				FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
			}
			return navigation.redirectToHome();
		}
		return null;
	}

	@Override
	public String registerNew(Staff s) {
		System.out.println(new Date() + " : REGISTER NEW : " + s);
		if(!this.activeDepartment_Has_Staff.getStaffs().contains(s)){
			boolean activated = false;
			Staff ss = em.registerNew(s, activated);
			em.addToDepartment(this.departmentselected, ss);
		}
		if(FacesContext.getCurrentInstance() != null){
			FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		}
		return navigation.redirectToHome();
	}

	@Override
	public String registerNewWithDepartment(Staff s, Department d, String key) {
		System.out.println(new Date() + " : REGISTER NEW : " + s + " : " + d);
		if(!em.getDepartments().contains(d)){
			boolean activated = true;
			Staff ss = em.registerNew(s, activated);
			em.createDepartment(d, ss, key);
		}
		if(FacesContext.getCurrentInstance() != null){
			FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		}
		return navigation.redirectToHome();
	}

	@Override
	public void activateStaff(Staff s, String groupKey){
		if(this.loggedin && activeDepartment_Has_Staff.getOwner().equals(activeUser) && checkToken()){
			em.setActivateStaff(s, true);
			if(s.getRole() == Staff.DOCTOR){
				em.setGroupKey(s, activeDepartment_Has_Staff,  groupKey);		
			}
			this.setDepartmentselected(this.departmentselected);
		}
	}

	@Override
	public void deactivateStaff(Staff s){
		if(this.loggedin && activeDepartment_Has_Staff.getOwner().equals(activeUser) && checkToken()){
			em.setActivateStaff(s, false);
			if(s.getRole() == Staff.DOCTOR){
				em.setGroupKey(s, activeDepartment_Has_Staff, null);
			}
			this.setDepartmentselected(this.departmentselected);
		}
	}

	/**
	 * Responsible for the renewal of the group key.
	 * Renews the own group key, the group key of the other staffs and saves the new encrypted patients.
	 * 
	 * @param key
	 * 			of the active user
	 * @param staffs
	 * 			JSON string of the encrypted group keys from all staffs
	 * @param patients
	 * 			JSON string of all the encrypted patients
	 * @throws ParseException
	 * 			on invalid JSON string
	 */
	public void renew(String key, String staffs, String patients) {
		if(this.loggedin && checkToken()){
			
			// update my own key
			if(this.activeUser.getRole() == Staff.DOCTOR){
				this.activateStaff(this.activeUser, key);
			}

			// update staff's keys
			if(!staffs.equals("")){
				JSONParser parser = new JSONParser();
				Object obj = null;
				try {
					obj = parser.parse(staffs);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				JSONObject json = (JSONObject) obj;
				Set set = json.keySet();
				for (Iterator i = set.iterator(); i.hasNext();) {
					int id = Integer.parseInt((String) i.next());
					Staff s = em.getStaff(id);
					if ((s != null) && s.getActivated() && (s.getRole() == Staff.DOCTOR)) {
						String key2 = (String) json.get("" + id);
						this.activateStaff(s, key2);
					}
				}
			}
	
			// update patient's personal data
			if(!patients.equals("")){
				JSONParser parser = new JSONParser();
				Object obj = null;
				try {
					obj = parser.parse(patients);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				JSONObject json = (JSONObject) obj;
				Set set = json.keySet();
				for (Iterator i = set.iterator(); i.hasNext();) {
					int id = Integer.parseInt((String) i.next());
					Patient p = getPatient(id);
					if(p != null){
						String personalData = (String) json.get("" + id);
						p.setPersonalData(personalData);
						this.updatePatient(p);
					}
				}
			}
		}
	}
	
	@Override
	public List<Department> getDepartments(){
		List<Department> l = em.getDepartments();
		Collections.sort(l);
		return l;
	}

	@Override
	public List<Staff> getStaffs(){
		if(this.loggedin && checkToken()){
			return em.getStaffs();
		}
		return null;
	}
	
	@Override
	public List<Staff> getStaffs(String name){
		if(this.loggedin && checkToken()){
			return em.getStaffs(name);
		}
		return null;
	}
	
	@Override
	public List<Staff> searchStaffsInGroup(String name) {
		if(this.loggedin && checkToken()){
			List<Staff> l = em.searchStaffsInGroup(name, activeDepartment_Has_Staff, activeUser);
			return l;
		}
		return null;
	}

	@Override
	public void updateStaff(Staff s, String groupKey){
		if(this.loggedin && checkToken()){
			em.updateStaff(s);
			if(s.getRole() == Staff.DOCTOR){
				em.setGroupKey(s, activeDepartment_Has_Staff,  groupKey);	
			}
		}
	}

	@Override
	public List<Patient> getPatients(){
		if(this.loggedin && (activeUser.getRole() == Staff.DOCTOR) && checkToken()){
			List<Patient> list = new ArrayList<Patient>();
			for(Patient p : em.getPatients()){
				if(p.getReadaccess() || (p.getOwner().equals(activeUser))){
					list.add(p);
				}
			}
			return list;
		}
		return null;
	}
	
	@Override
	public List<Patient> searchPatients() {
		if(this.loggedin && (activeUser.getRole() == Staff.DOCTOR) && checkToken()){
			List<Patient> patient = new ArrayList<Patient>();
			for(Patient p : em.getPatients()){
				if(p.getDepartment().equals(activeDepartment_Has_Staff.getDepartment())){
					if(p.getReadaccess() || (p.getOwner().equals(activeUser))){
						patient.add(p);		
					}		
				}
			}
			return patient;
		}
		return null;
	}

	@Override
	public Patient getPatient(int patientid){
		if(this.loggedin && (activeUser.getRole() == Staff.DOCTOR) && checkToken()){
			for(Patient p : em.getPatients()){
				if((p.getReadaccess() || (p.getOwner().equals(activeUser))) && (p.getPatientid() == patientid)){
					return p;
				}
			}
		}
		return null;
	}

	@Override
	public String createPatient(Patient p) {
		if(this.loggedin && (activeUser.getRole() == Staff.DOCTOR) && checkToken()){
			em.createPatient(p, activeDepartment_Has_Staff, activeUser);
			return navigation.redirectToLoggedin();
		}
		return null;
	}

	@Override
	public void updatePatient(Patient p){
		if(this.loggedin && writeAccess(p) && checkToken()){
			em.updatePatient(p);
		}
	}

	public List<ListOfQuestionnaire> getPatientDatas(){
		if(this.loggedin && (this.activePatient != null) && checkToken()){
			List<ListOfQuestionnaire> l = em.searchDatas(this.activePatient.getPatientid());
			return l;
		}
		return null;
	}
	
	public IFilledQuestionnaire getFilledQuestion(){
		if(this.loggedin && (activeUser.getRole() == Staff.DOCTOR) && checkToken()){
		return em.getFilledQuestionnaire2(questionnaireId);
		}
		return null;
	}
	
	public IFilledQuestionnaire getNewQuestion(){
		if(this.loggedin && (activeUser.getRole() == Staff.DOCTOR) && checkToken()){
			return em.getEmptyQuestionnaire(questionnaireName);
		}
		return null;
	}
	
	public void updateAnswer(@SuppressWarnings("rawtypes") IQuestion iq){
		em.updateAnswer(iq);
	}
	
	@SuppressWarnings("rawtypes")
	public List<IQuestion> getQuestions(String s) {
		return em.getQuestions(s);
	}

	@SuppressWarnings("rawtypes")
	public List<IAnswer> getAnswers(int i) {
		return em.getAnswers(i);
	}
	
	public void saveUpdate(){
		if(this.loggedin && (activeUser.getRole() == Staff.DOCTOR) && checkToken()){
			em.updateQuestionnaireHasAnswer();
		}
	}
	
	public void saveFilledQuestionnaire(){
		if(this.loggedin && (activeUser.getRole() == Staff.DOCTOR) && (!questionnaireName.equals("")) && checkToken()){
			em.addAnswer(activePatient);
		}
	}

	public void setPatientid(ListOfQuestionnaire list){
		this.questionnaireId = list.getQuestId();
		this.questionnaireName = list.getTypOfQuest();
	}

	public ListOfQuestionnaire getPatientid(){
		return null;
	}

	public int getQuestionnariId() {	//TODO name !!!
		return questionnaireId;
	}

	public void setQuestionnariId(int questionnaireId) {
		this.questionnaireId = questionnaireId;
	}
	
	public void addQuestionnaireTemplate(String type,String question,String nameOfTemplate,List<String> pos, int fragenr){
		if(this.loggedin && (activeUser.getRole() == Staff.DOCTOR) && checkToken()){
			em.addQuestionnaireTemplate(type, question, nameOfTemplate, pos, fragenr);
		}
	}
	
	public List<QuestionnaireTools> getTemplate(String name) {
		if(this.loggedin && (activeUser.getRole() == Staff.DOCTOR) && checkToken()){
			return em.getTemplate(name);
		}
		return null;
	}

	public void deleteTemplateQuestion(QuestionnaireTools q) {
		if(this.loggedin && (activeUser.getRole() == Staff.DOCTOR) && checkToken()){
			try {
				em.deleteTemplateQuestion(q);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void editQuestion(QuestionnaireTools q) {
		if(this.loggedin && (activeUser.getRole() == Staff.DOCTOR) && checkToken()){
			try {
				em.editQuestion(q);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void changeQuestionNr(String templateNameSelected, int eNumber) {
		em.changeQuestionNr(templateNameSelected, eNumber);
	}

	public List<String> getTemplateNames() {
		return em.getTemplateNames();
	}

	public void addAnswer() {
		em.addAnswer(activePatient);
	}

	public List<FilledQuestionnaire> getFilledQuestionnaires(){
		return em.getFilledQuestionnaires();
	}
	
	public String getQuestselected() {
		return questionnaireName;
	}

	public void setQuestselected(String questselected) {
		this.questionnaireName = questselected;
	}
	
	public void questChanged(ActionEvent evt) {
		UIComponent comp = evt.getComponent();
		questionnaireName = (String) comp.getAttributes().get("value");
	}
	
	public void export() {
		String template = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("template");
		if (this.loggedin && ((activeUser.getRole() == Staff.DOCTOR) || (activeUser.getRole() == Staff.STATISTICIAN)) && checkToken()) {
			try {
				dm.export(template);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public String getNonce(){
		return nonce;
	}
	
	public void setNonce(String nonce){
		this.nonce = nonce;
	}

	/**
	 * @param name
	 * 			of the staff
	 * @return
	 * 			the salt of a specific staff in a department
	 */
	public String getSaltFromStaff(String name){
		if(activeDepartment_Has_Staff != null && activeDepartment_Has_Staff.getStaff(name) != null){
			return activeDepartment_Has_Staff.getStaff(name).getSalt();
		}
		return null;
	}
	
	public boolean getLoggedin(){
		return this.loggedin;
	}

	private void setLoggedin(boolean loggedin){
		this.loggedin = loggedin;
	}
	
	/**
	 * @return
	 * 			true if security token is correct
	 */
	private boolean checkToken() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		String token = (String) request.getAttribute("securitytoken");
		return this.nonce.equals(token);
	}

	public void setActiveUser(Staff s){
		this.activeUser = s;
	}
	
	public Staff getActiveUser(){
		return this.activeUser;
	}

	public Patient getActivePatient() {
		if(this.loggedin && (activeUser.getRole() == Staff.DOCTOR) && checkToken()){
			return activePatient;
		}
		return null;
	}

	public void setActivePatient(Patient activePatient) {
		if(this.loggedin && (activeUser.getRole() == Staff.DOCTOR) && checkToken()){
			this.activePatient = activePatient;
		}
	}

	public String getDepartmentselected() {
		return this.departmentselected;
	}

	public void setDepartmentselected(String departmentselected) {
		this.departmentselected = departmentselected;
		List<Department_Has_Staff> list = em.get_Department_Has_Staffs();
		for(Iterator i = list.iterator() ; i.hasNext() ; ){
			Department_Has_Staff dhs = (Department_Has_Staff) i.next();
			if(dhs.getDepartment().getName().equals(departmentselected)){
				this.setDepartment_Has_Staff(dhs);
			}
		}
	}

	public Department_Has_Staff getDepartment_Has_Staff(){
		return this.activeDepartment_Has_Staff;
	}

	public void setDepartment_Has_Staff(Department_Has_Staff activeDepartment_Has_Staff){
		this.activeDepartment_Has_Staff = activeDepartment_Has_Staff;
	}

	@Override
	public boolean isOwnerOfGroup(){
		return activeDepartment_Has_Staff.getOwner().equals(activeUser);
	}

	@Override
	public boolean isOwner(Patient p){
		if(this.loggedin && (activeUser.getRole() == Staff.DOCTOR) && checkToken()){
			if (p.getOwner().equals(activeUser)) {
				return true;
			}
			return false;
		}
		return false;
	}

	@Override
	public boolean readAccess(Patient p){
		if(this.loggedin && (activeUser.getRole() == Staff.DOCTOR) && checkToken()){
			if (isOwner(p)) {
				return true;
			}
			return p.getReadaccess();
		}
		return false;
	}

	@Override
	public boolean writeAccess(Patient p){
		if(this.loggedin && (activeUser.getRole() == Staff.DOCTOR) && checkToken()){
			if (isOwner(p)) {
				return true;
			}
			return p.getWriteaccess();
		}
		return false;
	}

	@Override
	public boolean insertAccess(Patient p){
		if(this.loggedin && (activeUser.getRole() == Staff.DOCTOR) && checkToken()){
			if (isOwner(p)) {
				return true;
			}
			return p.getInsertaccess();
		}
		return false;
	}

}