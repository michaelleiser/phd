package org.bfh.phd.questionary;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import org.bfh.phd.EntityManager;

@ManagedBean(name = "template", eager = true)
@ViewScoped
public class Template {
	
	private static int i = 1;
	private String templatename = "";
	private String questionString;
	private List<String> s = new ArrayList<String>();
	private List<Test> test = new ArrayList<Test>();
	private List<String> answerRadioButton = new ArrayList<String>();
	private List<String> answerCheckbox = new ArrayList<String>();
	private List<String> posAnswer = new ArrayList<String>();
	
	
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
	
	public List<Test> getQuestion() {
		return test;
	}
	
	public void add(){
		System.out.println("-------------------");
		System.out.println("add");
	}
	
	public void safe(){
		System.out.println("******");
		EntityManager em = new EntityManager();
		for(Test t:test){
		em.addQuestionnaireTemplate(t.getType(), t.getQuestion(),templatename, t.getAnswer());
		}
		System.out.println("Save");
		System.out.println(templatename);
	}
	
	public void addString(final AjaxBehaviorEvent event){
		Test t = new Test();
		t.setQuestion(questionString);
		t.setType("String");
		t.addPossibleAnswer("");
		t.setId(i);
		test.add(t);
		i++;
		System.out.println("String" + t.getAnswer());		
	}
		
	public void addRadioButton(final AjaxBehaviorEvent event){
		Test t = new Test();
		t.setQuestion(questionString);
		t.setType("RadioButton");
		for(String s: answerRadioButton){
			if(s != ""){
		t.addPossibleAnswer(s);
			}
		}
		t.setId(i);
		test.add(t);
		i++;
		System.out.println(answerRadioButton);		
		System.out.println("Radio Button" + t.getAnswer());
	}
			
	public void addCheckbox(final AjaxBehaviorEvent event){
		Test t = new Test();
		t.setQuestion(questionString);
		t.setType("Checkbox");
		for(String s: answerCheckbox){
			if(s != ""){
		t.addPossibleAnswer(s);
			}
		}
		t.setId(i);
		test.add(t);
		System.out.println(answerCheckbox.size());
		System.out.println("Check Box" + t.getAnswer());
	}
	
	public void addName(final AjaxBehaviorEvent event) {
		System.out.println("fragebogenname");
		System.err.println(templatename);	
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

	private String answerString;
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
		
	private String name;
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
		
		public class Test{
			private int id = 0;
			private String question;
			private String type;
			private List<String> list = new ArrayList<String>();
			//--- INNER CLASS  ----
				
			public void setId(int i){
				id = i;
			}
			
			public void setQuestion(String question){
				this.question = question;
			}
			
			public String getQuestion(){
				return this.question;
			}
			
			public void setType(String type){
				this.type = type;
			}
	
			public String getType(){
				return this.type;
			}
			
			public void addPossibleAnswer(String answer){
				this.list.add(answer);
			}
			
			public List<String> getAnswer(){
				return this.list;
			}
			
			public int getId(){
				return this.id;
			}
		}
}
