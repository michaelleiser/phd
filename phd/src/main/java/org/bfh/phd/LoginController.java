package org.bfh.phd;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.bfh.crypto.MYCRYPTO;
import org.bfh.phd.interfaces.IAnswer;
import org.bfh.phd.interfaces.IFilledQuestionnaire;
import org.bfh.phd.interfaces.ILoginController;
import org.bfh.phd.interfaces.IQuestion;
import org.bfh.phd.questionary.QuestionnairTools;


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
	private String questionnaireName;

	private String departmentselected;
	private Department_Has_Staff activeDepartment_Has_Staff;
	
	private Date to;
	private EntityManager em;


//	private Logger log;
	 
	public LoginController() {
		this.em = new EntityManager();
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
		if(this.loggedin == true){
			return em.getStaffs();
		}
		return null;
	}
	
	public List<Staff> getStaffs(String name){
		System.out.println("GET STAFFS name");
		if(this.loggedin == true){
			return em.getStaffs(name);
		}
		return null;
	}
	
	@Override
	public String login(String name, String password) {
		System.out.println("LOGIN");
		if(activeDepartment_Has_Staff != null){
			activeUser = activeDepartment_Has_Staff.getStaff(name);
			if(activeUser != null && activeUser.getActivated() && password.equals(MYCRYPTO.SHA256(nonce + "" + activeUser.getPassword()))){
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
	public String registernew(Staff s) {
		System.out.println("REGISTER NEW");
		if(!this.activeDepartment_Has_Staff.getStaffs().contains(s)){
			boolean activated = false;
			Staff ss = em.registernew(s, activated);
			em.addToDepartment(this.departmentselected, ss);
		}
		return "/home?faces-redirect=true";
	}
	@Override
	public String registernewWithDepartment(Staff s, Department d, String key) {
		System.out.println("REGISTER NEW");
		if(!em.getDepartments().contains(d)){
			boolean activated = true;
			Staff ss = em.registernew(s, activated);
			em.createDepartment(d, ss, key);
		}
		return "/home?faces-redirect=true";
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
			List<Patient> l = em.getPatients(activeUser);
			return l;
		}
		return null;
	}
	
	public List<ListOfQuestionnari> getPatientDatas(){
		if((this.loggedin == true) && (this.activePatient != null)){
		List<ListOfQuestionnari> l = em.searchDatas(this.activePatient.getPatientid());
			return l;
		}
		return null;
	}
	
	@Override
	public void updateStaff(Staff s, String secret){
		System.out.println("update staff..." + s);
		if(this.loggedin == true){
			em.updateStaff(s);
			if(s.getRole() == 1){
				em.setGroupKey(s, activeDepartment_Has_Staff,  secret);	
			}
		}
	}
	
	@Override
	public void updatePatient(Patient p){
		System.out.println("update patient..." + p);
		if(this.loggedin == true){
			em.updatePatient(p, activeUser);
		}
	}
	
	public void updatePatientData(PatientData pd){
//		System.out.println("update pateint data..." + pd);
//		if(this.loggedin == true){		// TODO rW access
//			em.updatePatientData(pd);
//		}
	}
	
	public List<ListOfQuestionnari> searchPatientDatas(String op){		// TODO
//		System.out.println("SEARCHING Patient Data..." + op + from + "--" + to);
//		if(this.loggedin == true){
//			List<ListOfQuestionnari> l = em.searchPatientDatas(op, from , to);
//			return l;
//		}
		return null;
	}

	public List<Patient> searchPatients() {
		System.out.println("SEARCHING Patient...");
		if(this.loggedin == true && activeUser.getRole() == 1){
			List<Patient> l = em.searchPatients(activeDepartment_Has_Staff, activeUser);
			return l;
		}
		return null;
	}

	@Override
	public String createPatient(Patient p) {
		System.out.println("CREATE Patient..." + p);
		if((this.loggedin == true) && (activeUser.getRole() == 1)){
			em.createPatient(p, activeDepartment_Has_Staff, activeUser);
			return "/restricted/loggedin?faces-redirect=true";
		}
		return null;
	}
	
	public IFilledQuestionnaire getFilledQuestion(){
		return em.getFilledQuestionnaire2(questionnaireId);
	}
	
	public void updateAnswer(IQuestion iq){
		System.out.println("answer");
		System.out.println(iq.getAnswer());
		
		em.updateAnswer(iq);
	}

//	@SuppressWarnings("rawtypes")
//	public List<IQuestion> getQuestions() {
//		return em.getQuestions(s);
//	}
	
	@SuppressWarnings("rawtypes")
	public List<IQuestion> getQuestions(String s) {
		return em.getQuestions(s);
	}

	public List<IAnswer> getAnswers(int i) {
		return em.getAnswers(i);
	}
	
	public void safe(ActionEvent evt){
		System.out.println("->"+evt );
	}

	public Patient getActivePatient() {
		System.out.println("ActivePatient");
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

	public boolean isOwner(Patient p){
		System.out.println("IsOwner");
		if(this.loggedin == true && activeUser.getRole() == 1){
			return em.isOwner(p, activeUser);
		}
		return false;
	}
	
	public boolean isOwnerOfGroup(Staff s){
		return activeDepartment_Has_Staff.getOwner().equals(s);
	}

	public boolean readAccess(Patient p){
		System.out.println("ReadAccess");
		if(this.loggedin == true && activeUser.getRole() == 1){
			return em.readaccess(p, activeUser);
		}
		return false;
	}
	
	public boolean writeAccess(Patient p){
		System.out.println("WriteAccess");
		if(this.loggedin == true && activeUser.getRole() == 1){
			return em.writeaccess(p, activeUser);
		}
		return false;
	}
	
	public boolean insertAccess(Patient p){
		System.out.println("InsertAccess");
		if(this.loggedin == true && activeUser.getRole() == 1){
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
		if(this.loggedin == true && activeUser.getRole() == 1){
		em.addQuestionnaireTemplate(typ, question, nameOfTemplate, pos, fragenr);
		}
	}
	
	
	

	
//	public String getEncryptedData(String s){
//		MYCRYPTO aes = new MYCRYPTO();
//		String encrypted = null;
//		try {
//			encrypted = aes.AESencode(s);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return encrypted;
//	}
//	public String getEncryptedData(){
//		String message = "This is encrypted session key ;)";
//		return this.getEncryptedData(message);
//	}
//	
//	public String getDecryptedData(String s){
//		MYCRYPTO aes = new MYCRYPTO();
//		String decrypted = null;
//		try {
//			decrypted = aes.AESdecode(s);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return decrypted;
//	}
//	public void getDecryptedData(){
//		if(in != null){
//			getDecryptedData(in);
//		}
//	}
//	
//	public String getIn() {
//		return in;
//	}
//
//	public void setIn(String in) {
//		this.in = in;
//	}
//
//	private String in;
	


	
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
		if(this.loggedin == true && activeUser.getRole() == 1){
			List<Staff> l = em.searchStaffs(name, activeDepartment_Has_Staff, activeUser);
			return l;
		}
		return null;
	}
	
	@Override
	public void activateStaff(Staff s, String secret){
		System.out.println("ACTIVATE Staff..." + s);
		if(this.loggedin == true && activeDepartment_Has_Staff.getOwner().equals(activeUser)){
			em.setActivateStaff(s, true);
			if(s.getRole() == 1){
				em.setGroupKey(s, activeDepartment_Has_Staff,  secret);		
			}
		}
	}
	
	@Override
	public void deactivateStaff(Staff s){
		System.out.println("DEACTIVATE Staff..." + s);
		if(this.loggedin == true && activeDepartment_Has_Staff.getOwner().equals(activeUser)){
			em.setActivateStaff(s, false);
			if(s.getRole() == 1){
				em.setGroupKey(s, activeDepartment_Has_Staff, null);
			}		
		}
	}

	public List<QuestionnairTools> getTemplate(String name) {
		if(this.loggedin == true && activeUser.getRole() == 1){
		return em.getTemplate(name);
		}
		return null;
	}

	public void deletTemplateQuestion(QuestionnairTools q) {
		if(this.loggedin == true && activeUser.getRole() == 1){
		try {
			em.deletTemplateQuestion(q);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
	}

	public void editQuestion(QuestionnairTools q) {
		if(this.loggedin == true && activeUser.getRole() == 1){
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

	public void addAnswer(IFilledQuestionnaire f) {
		em.addAnswer(activePatient, f);
	}

	
//	String pub = "-----BEGIN PUBLIC KEY-----MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCSCF1HymTdYHQAoBcBmvt/dRbcevoqV1RlEIryp+R95pBhA3tqZv8Qv6w3AyFi0DPrquREc6bUywCZ7sJE7JstQOP3ETSDxSvqtvGTaogtll7icgxTexA+sLt28E7E4TIcvZqXweQ8XneW62yDDlk5yhcG9xTZT1289d66YbvH7wIDAQAB-----END PUBLIC KEY-----";
//	String priv = "-----BEGIN RSA PRIVATE KEY-----MIICWwIBAAKBgQCSCF1HymTdYHQAoBcBmvt/dRbcevoqV1RlEIryp+R95pBhA3tqZv8Qv6w3AyFi0DPrquREc6bUywCZ7sJE7JstQOP3ETSDxSvqtvGTaogtll7icgxTexA+sLt28E7E4TIcvZqXweQ8XneW62yDDlk5yhcG9xTZT1289d66YbvH7wIDAQABAoGAPcTt9/TjT0SCLNWKhbJRmSsk3WPjN0+zMgCaVWOw4ZRKE88OQAaK80GwDaD0WUCqBZBGd7HXqoCno3T7lX3jcNBDPr4D88BwyDLpmQgkPk5qCQ3pgwQYOaXsEYnA9Apr6VZtdBqjO3u94oiuWGv1XYdEgfWF6BysEE0nDbD7/OkCQQDDFZDdYFZh+OQn97Wc2CmI3BaLWHikxXUJ/I093YQKZRO26opKW29ZQMDjvGaMitIoxSh6gCp2TfU23JyYgMM1AkEAv6HEW91lVoYuBZ5NmnDRQ51qk0dYf+qGlhkPOq4TeVET5tRNxxuy7D+6CK1bwQCjVkbgo1mcYc0DKUxk1r5/EwJAUTgoUNJsBGwP6UfrF7qzSCSBSlByIf+HY7n+v9P6xi0g0RXCr4Rzzk/0PpxQgZDGQG0dFitIAmsgfU/J7oAlRQJAKrhTX+9hMgLDq7j4r99Kp3omUiLrlcigrEF15az85mSuvRzDIgoIvyYNwPV0qPgNcaRnW8MUW7EqbUB8kmrxRQJAM1VdxBYVlZfKo/iAHaiziQcax27VA7p12N7+xp4lJLGInK5YYdGJrkNE04BBZiYtfWBSPseuFET95RMS56iWVQ==-----END RSA PRIVATE KEY-----";
//	
////	public String getEncryptedRSAData(String plaintext){
////		PublicKey publicKey = pub;
////		
////	    byte[] cipherText = CopyOfRSA.encrypt(plaintext, publicKey);
////		return null;
////	}
////
////	public String getDecryptedRSAData(String ciphertext){
////		PrivateKey privateKey = priv;
////		
////		String plaintext = CopyOfRSA.decrypt(ciphertext, privateKey);
////		return null;
////	}

	public List<FilledQuestionnaire> getFilledQuestionnaires(){
		return em.getFilledQuestionnaires();
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
	
}