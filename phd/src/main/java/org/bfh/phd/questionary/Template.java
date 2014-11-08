package org.bfh.phd.questionary;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import org.bfh.phd.EntityManager;
import org.bfh.phd.LoginController;
import org.bfh.phd.Questionnari;

@ManagedBean(name = "template", eager = true)
@SessionScoped
public class Template {
	
	private static int i = 1;
	private int j = 0;
	private int eNumber = 0;
	private QuestionnairTools eQuestion = new QuestionnairTools();
	private String name;
	private String templatename = "";
	private String questionString;
	private String answerString;
	private static List<String> templateName;
	private static String templateNameSelected;
	private List<QuestionnairTools> test = new ArrayList<QuestionnairTools>();
	private List<String> answerRadioButton = new ArrayList<String>();
	private List<String> answerCheckbox = new ArrayList<String>();
	private List<QuestionnairTools> edit = new ArrayList<QuestionnairTools>();
	private QuestionnairTools t = new QuestionnairTools();
	private static EntityManager em;
	
	
	static{
		init();
	}
	private static void init(){
		em = new EntityManager();
		templateName = new ArrayList<String>();
		templateName = em.getTemplateNames();	
	}
	
	private void clear(){
		name = templatename = questionString = answerString = "";
		answerRadioButton.clear();
		answerCheckbox.clear();
		test.clear();
		edit.clear();
	}
	
	public Template(){
	}
		
	public boolean isEmpty(){
		return templatename.isEmpty();
	}
	
	public boolean isNotEmpty(){
		return !templatename.isEmpty();
	}
	
	public boolean isTemplateEmpty(){
		return !test.isEmpty();
	}
	
	public List<QuestionnairTools> getQuestion() {
		return test;
	}
	
	public void add(){
		t = new QuestionnairTools();
		eQuestion = new QuestionnairTools();
		answerCheckbox.clear();
		answerRadioButton.clear();
	}
	
	public void safe(LoginController lc){
		int i = 1;
		for(QuestionnairTools t:test){
		lc.addQuestionnaireTemplate(t.getType(), t.getQuestion(),templatename, t.getAnswer(), i);
		i++;
		}
		init();
		clear();
	}
	
	public void saveAddEdit(LoginController lc){
		lc.changeQuestionNr(templateNameSelected, eNumber);
		lc.addQuestionnaireTemplate(eQuestion.getType(), eQuestion.getQuestion(), templateNameSelected , eQuestion .getAnswer(), eNumber+1);
		eNumber = 0;
		setTemplates();
		add();
	}
		
	public void addString(final AjaxBehaviorEvent event){
		t.setQuestion(questionString);
		t.setType("String");
		t.addPossibleAnswer("-");
		if(event.getComponent().getAttributes().get("edit").equals("1")){
			t.setId(i);
			eQuestion = t;
		}else{
			t.setId(i);	
			test.add(t);	
		}
		i++;
	}
		
	public void addRadioButton(final AjaxBehaviorEvent event){
		t.setQuestion(questionString);
		t.setType("RadioButton");
		for(String s: answerRadioButton){
			if(s != ""){
				t.addPossibleAnswer(s);
			}
		}
		if(event.getComponent().getAttributes().get("edit").equals("1")){
			t.setId(i);
			eQuestion = t;
		}else{
			t.setId(i);
			test.add(t);	
		}
		i++;
		answerRadioButton.clear();
	}
				
	public void addCheckbox(final AjaxBehaviorEvent event){
		t.setQuestion(questionString);
		t.setType("Checkbox");
		for(String s: answerCheckbox){
			if(s != ""){
		t.addPossibleAnswer(s);
			}
		}
		if(event.getComponent().getAttributes().get("edit").equals("1")){
			t.setId(i);
			eQuestion = t;

		}else{
			t.setId(i);
			test.add(t);	
		}
		i++;
		answerCheckbox.clear();
	}
	
	public List<String> getTemplateNames(){
		return templateName;
	}

	public String templateNameChanged(ActionEvent evt) {
		UIComponent comp = evt.getComponent();
		templateNameSelected = (String) comp.getAttributes().get("value");
		return "";
	}

	public String getTemplateNameselected() {
		return templateNameSelected;
	}

	public void setTemplateNameselected(String questselected) {
		this.templateNameSelected = questselected;
		setTemplates();
	}
	
	public void addName(final AjaxBehaviorEvent event) {
	}
	
	public void addQuestion(final AjaxBehaviorEvent event){	
	}
	
	public void addAnswer(final AjaxBehaviorEvent event){
	}
	
	public String getQuestionString(){
		return questionString;
	}
	
	public void setQuestionString(String questionString){
		this.questionString = questionString;
	}

	public String getAnswerString() {
		return answerString;
	}
	public void setAnswerString(String answerString) {
		this.answerString = answerString;
	}
	
	public String getAnswerRadioButton() {
		return "";
	}
	
	public void setAnswerRadioButton(String answerRadioButton) {
		addRadioButton(answerRadioButton);
	}
	
	public String getAnswerCheckbox() {
		return "";
	}
	
	public void setAnswerCheckbox(String answerCheckbox) {
		addCheckBox(answerCheckbox);
	}
	
	private void addCheckBox(String s){
		this.answerCheckbox.add(s);
	}
	
	private void addRadioButton(String test){
		this.answerRadioButton.add(test);
	}
		
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
		
	public String nameToTemp(){
		templatename = this.name;
		return "";
	}
	
	private void setTemplates(){
		edit.clear();
		edit = em.getTemplate(templateNameSelected);
	}
	
	public List<QuestionnairTools> getTemplates(){
		return edit;
	}
	
	public void editTemplate(){
		System.out.println("edit Question");
	}
	
	public void deletQuestion(LoginController lc, QuestionnairTools q){
		lc.deletTemplateQuestion(q);
		setTemplates();
	}
	
	public void deletTemplate(LoginController lc){
		System.out.println("not implemented");
	}
	
	public void saveEdit(LoginController lc){
		for(QuestionnairTools q:edit){
			if(q.isEditable()){
				System.out.println(q.getAnswer());
				q.setEditable(false);
				lc.editQuestion(q);
			}
		}
	}
	
	public String editAction(QuestionnairTools q){
		q.setEditable(true);
		return null;
	}
	
	public String addAction(QuestionnairTools q){
		eNumber = q.getId();
		return null;
	}

	public int getE() {
		return eNumber;
	}

	public void setE(int e) {
		this.eNumber = e;
	}
}
