package org.bfh.phd;

import java.io.Serializable;
import java.sql.SQLException;
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

import org.bfh.crypto.MyCrypto;
import org.bfh.phd.interfaces.IAnswer;
import org.bfh.phd.interfaces.IFilledQuestionnaire;
import org.bfh.phd.interfaces.ILoginController;
import org.bfh.phd.interfaces.IQuestion;
import org.bfh.phd.questionary.QuestionnairTools;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 * @author leism3, koblt1
 *
 */
@ManagedBean(name = "loginController", eager = true)
@SessionScoped
public class LoginController implements Serializable, ILoginController{

	private static final long serialVersionUID = 1L;
	private boolean loggedin = false;

	private Staff activeUser;
	private Patient activePatient;
	private PatientData activePatientData;
	private int questionnaireId;
	private String questionnaireName = "";

	private String departmentselected;
	private Department_Has_Staff activeDepartment_Has_Staff;
	
	private Date to;
	private EntityManager em;
	private DownloadManager dm;


//	private Logger log;
	 
	public LoginController() {
		this.em = new EntityManager();
		this.dm = new DownloadManager();
		this.nonce = UUID.randomUUID().toString();
		
//		initLogger(); TODO write the loggincontroller
	}
		
//	private void initLogger() {
//		log = Logger.getLogger(LoginController.class.getName());
//		FileHandler fh;
//		try {
//			fh = new FileHandler(new Tools().getLogPath(), true);
//			fh.setFormatter(new SimpleFormatter());
//			log.addHandler(fh);
//		} catch (SecurityException e) {
//			em.setExeption(e);
//		} catch (IOException e) {
//			em.setExeption(e);
//		}
//	}

	public void setStaff(Staff s){
		System.out.println("SET STAFF");
		this.activeUser = s;
	}
	
	public Staff getStaff(){
		System.out.println("GET STAFF");
		return this.activeUser;
	}
	
	public List<Staff> getStaffs(){
		System.out.println("GET STAFFS");
		if(this.loggedin){
			return em.getStaffs();
		}
		return null;
	}
	
	public List<Staff> getStaffs(String name){
		System.out.println("GET STAFFS name");
		if(this.loggedin){
			return em.getStaffs(name);
		}
		return null;
	}
	
	@Override
	public String login(String name, String password) {
		System.out.println("LOGIN");
		if(activeDepartment_Has_Staff != null){
			activeUser = activeDepartment_Has_Staff.getStaff(name);
			if(activeUser != null && activeUser.getActivated() && password.equals(MyCrypto.SHA256(nonce + "" + activeUser.getPassword()))){
				setLoggedin(true);			
				return "/restricted/loggedin?faces-redirect=true";
			}
		}
		return "/home?faces-redirect=true";
	}
	
	@Override
	public String logout(){
		System.out.println("LOGOUT");
		setLoggedin(false);
		if(FacesContext.getCurrentInstance() != null){
			FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		}
		return "/home?faces-redirect=true";
	}
	
	public boolean getLoggedin(){
		return this.loggedin;
	}
	
	private void setLoggedin(boolean loggedin){
		this.loggedin = loggedin;
	}

	@Override
	public String registerNew(Staff s) {
		System.out.println("REGISTER NEW");
		if(!this.activeDepartment_Has_Staff.getStaffs().contains(s)){
			boolean activated = false;
			Staff ss = em.registerNew(s, activated);
			em.addToDepartment(this.departmentselected, ss);
		}
		return "/home?faces-redirect=true";
	}
	@Override
	public String registerNewWithDepartment(Staff s, Department d, String key) {
		System.out.println("REGISTER NEW");
		if(!em.getDepartments().contains(d)){
			boolean activated = true;
			Staff ss = em.registerNew(s, activated);
			em.createDepartment(d, ss, key);
		}
		return "/home?faces-redirect=true";
	}
		
	public Patient getPatient(int patientid){
		System.out.println("GetPatient " + patientid);
		if(this.loggedin && (activeUser.getRole() == 1)){
			return em.getPatient(activeUser, patientid);
		}
		return null;
	}

	public List<Patient> getPatients(){
		System.out.println("GetPatients");
		if(this.loggedin && (activeUser.getRole() == 1)){
			List<Patient> l = em.getPatients(activeUser);
			return l;
		}
		return null;
	}
	
	public List<ListOfQuestionnari> getPatientDatas(){
		if(this.loggedin && (this.activePatient != null)){
		List<ListOfQuestionnari> l = em.searchDatas(this.activePatient.getPatientid());
			return l;
		}
		return null;
	}
	
	@Override
	public void updateStaff(Staff s, String secret){
		System.out.println("update staff..." + s);
		if(this.loggedin){
			em.updateStaff(s);
			if(s.getRole() == 1){
				em.setGroupKey(s, activeDepartment_Has_Staff,  secret);	
			}
		}
	}
	
	@Override
	public void updatePatient(Patient p){
		System.out.println("update patient..." + p);
		if(this.loggedin){
			em.updatePatient(p, activeUser);
		}
	}

	public List<Patient> searchPatients() {
		System.out.println("SEARCHING Patient...");
		if(this.loggedin && (activeUser.getRole() == 1)){
			List<Patient> l = em.searchPatients(activeDepartment_Has_Staff, activeUser);
			return l;
		}
		return null;
	}

	@Override
	public String createPatient(Patient p) {
		System.out.println("CREATE Patient..." + p);
		if(this.loggedin && (activeUser.getRole() == 1)){
			em.createPatient(p, activeDepartment_Has_Staff, activeUser);
			return "/restricted/loggedin?faces-redirect=true";
		}
		return null;
	}
	
	public IFilledQuestionnaire getFilledQuestion(){
		return em.getFilledQuestionnaire2(questionnaireId);
	}
	
	public IFilledQuestionnaire getNewQuestion(){
		if(this.loggedin && (activeUser.getRole() == 1)){
		return em.getEmptyQuestionnaire(questionnaireName);
		}else{return null;}
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
	
	public void safe(ActionEvent evt){
		System.out.println("->"+evt );
	}
	
	public void saveUpdate(){
		System.out.println("Save Update");
		if(this.loggedin && (activeUser.getRole() == 1)){
			em.updateQuestionnaireHasAnswer();
		}
	}
	
	public void saveFilledQuestionnaire(){
		System.out.println("Save filledquestionnaire");
		if(this.loggedin && (activeUser.getRole() == 1) && (!questionnaireName.equals(""))){
			em.addAnswer(activePatient);
		}
	}

	public Patient getActivePatient() {
		System.out.println("ActivePatient");
		if(this.loggedin && (activeUser.getRole() == 1)){
			return activePatient;
		}
		return null;
	}

	public void setActivePatient(Patient activePatient) {
		if(this.loggedin && (activeUser.getRole() == 1)){
			this.activePatient = activePatient;
		}
	}

	public PatientData getActivePatientData() {
		if(this.loggedin){
			return activePatientData;
		}
		return null;
	}

	public void setActivePatientData(PatientData activePatientData) {
		if(this.loggedin){
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

	public boolean isOwner(Patient p){
		System.out.println("IsOwner");
		if(this.loggedin && (activeUser.getRole() == 1)){
			return em.isOwner(p, activeUser);
		}
		return false;
	}
	
	public boolean isOwnerOfGroup(Staff s){
		return activeDepartment_Has_Staff.getOwner().equals(s);
	}

	public boolean readAccess(Patient p){
		System.out.println("ReadAccess");
		if(this.loggedin && (activeUser.getRole() == 1)){
			return em.readaccess(p, activeUser);
		}
		return false;
	}
	
	public boolean writeAccess(Patient p){
		System.out.println("WriteAccess");
		if(this.loggedin && (activeUser.getRole() == 1)){
			return em.writeaccess(p, activeUser);
		}
		return false;
	}
	
	public boolean insertAccess(Patient p){
		System.out.println("InsertAccess");
		if(this.loggedin && (activeUser.getRole() == 1)){
			return em.insertaccess(p, activeUser);
		}
		return false;
	}
	
	
	public void setPatientid(ListOfQuestionnari list){
		this.questionnaireId = list.getQuestId();
		this.questionnaireName = list.getTypOfQuest();
	}

	public ListOfQuestionnari getPatientid(){
		return null;
	}

	public int getQuestionnariId() {
		return questionnaireId;
	}

	public void setQuestionnariId(int questionnariId) {
		this.questionnaireId = questionnariId;
	}
	
	public void addQuestionnaireTemplate(String typ,String question,String nameOfTemplate,List<String> pos, int fragenr){
		if(this.loggedin && (activeUser.getRole() == 1)){
		em.addQuestionnaireTemplate(typ, question, nameOfTemplate, pos, fragenr);
		}
	}
	

	
	public List<Department> getDepartments(){
		return em.getDepartments();
	}

	public String getDepartmentselected() {
		return this.departmentselected;
	}

	public void setDepartmentselected(String departmentselected) {
		this.departmentselected = departmentselected;
		for(Department d : this.getDepartments()){
			if(d.getName().equals(departmentselected)){
				Department_Has_Staff dhs = em.getDepartment_Has_Staff(d);
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

	public List<Staff> searchStaffsInGroup(String name) {
		System.out.println("SEARCHING Staff..." + name);
		if(this.loggedin){
			List<Staff> l = em.searchStaffs(name, activeDepartment_Has_Staff, activeUser);
			return l;
		}
		return null;
	}
	
	@Override
	public void activateStaff(Staff s, String secret){
		System.out.println("ACTIVATE Staff..." + s);
		if(this.loggedin && activeDepartment_Has_Staff.getOwner().equals(activeUser)){
			em.setActivateStaff(s, true);
			if(s.getRole() == 1){
				em.setGroupKey(s, activeDepartment_Has_Staff,  secret);		
			}
		}
	}
	
	@Override
	public void deactivateStaff(Staff s){
		System.out.println("DEACTIVATE Staff..." + s);
		if(this.loggedin && activeDepartment_Has_Staff.getOwner().equals(activeUser)){
			em.setActivateStaff(s, false);
			if(s.getRole() == 1){
				em.setGroupKey(s, activeDepartment_Has_Staff, null);
			}		
		}
	}

	public List<QuestionnairTools> getTemplate(String name) {
		if(this.loggedin && (activeUser.getRole() == 1)){
		return em.getTemplate(name);
		}
		return null;
	}

	public void deletTemplateQuestion(QuestionnairTools q) {
		if(this.loggedin && (activeUser.getRole() == 1)){
		try {
			em.deletTemplateQuestion(q);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
	}

	public void editQuestion(QuestionnairTools q) {
		if(this.loggedin && (activeUser.getRole() == 1)){
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
	private String nonce;
	
	public String getNonce(){
		return nonce;
	}
	
	public void setNonce(String nonce){
		this.nonce = nonce;
	}

	public String getSaltFromStaff(String name){
		if(activeDepartment_Has_Staff != null && activeDepartment_Has_Staff.getStaff(name) != null){
			return activeDepartment_Has_Staff.getStaff(name).getSalt();
		}
		return null;
	}
	
	public void renew(String key, String staffs, String patients) throws ParseException{
		if(this.loggedin){
			
			// update my own key
			this.activateStaff(this.activeUser, key);

			// update staff's keys
			if(!staffs.equals("")){
				JSONParser parser = new JSONParser();
				Object obj = parser.parse(staffs);
				JSONObject json = (JSONObject) obj;
				Set set = json.keySet();
				for (Iterator i = set.iterator(); i.hasNext();) {
					int id = Integer.parseInt((String) i.next());
					Staff s = em.getStaff(id);
					if (s.getActivated()) {
						String key2 = (String) json.get("" + id);
						this.activateStaff(s, key2);
					}
				}
			}

			// update patient's personal data
			if(!patients.equals("")){
				JSONParser parser = new JSONParser();
				Object obj = parser.parse(patients);
				JSONObject json = (JSONObject) obj;
				Set set = json.keySet();
				for (Iterator i = set.iterator(); i.hasNext();) {
					int id = Integer.parseInt((String) i.next());
					Patient p = em.getPatient(this.activeUser, id);
					String personalData = (String) json.get("" + id);
					p.setPersonalData(personalData);
					this.updatePatient(p);
				}
			}
		}
	}

	public void export() {
		String template = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("template");
		if (this.loggedin && ((activeUser.getRole() == 2) || (activeUser.getRole() == 1))) {
			try {
				dm.export(template);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}