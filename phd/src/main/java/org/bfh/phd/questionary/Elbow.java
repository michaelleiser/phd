//package org.bfh.phd.questionary;
//
//import java.io.Serializable;
//import java.util.Arrays;
//import java.util.List;
//
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
//import javax.faces.bean.ViewScoped;
//import javax.faces.component.UIComponent;
//import javax.faces.event.ActionEvent;
//import javax.faces.event.AjaxBehaviorEvent;
//import javax.faces.event.ValueChangeEvent;
//
//import org.bfh.phd.EntityManager;
//import org.bfh.phd.FilledQuestionnaire;
//import org.bfh.phd.LoginController;
//import org.bfh.phd.interfaces.IAnswer;
//import org.bfh.phd.interfaces.IFilledQuestionnaire;
//
//@ManagedBean(name = "elbow", eager = true)
//@SessionScoped
//public class Elbow implements Serializable{
//	
//	private String selectedRadioValue2;
//
//	public String getSelectedRadioValue2() {
//		return selectedRadioValue2;
//	}
//
//	public void setSelectedRadioValue2(String selectedRadioValue2) {
//		this.selectedRadioValue2 = selectedRadioValue2;
//	}
//
//	
//	
//	
//	
////	private static final long serialVersionUID = 1L;
////	private static List<String> quests;
////	private String questselected;
////	private List<String> answerCheckbox;
////	private String answerString;
////	private String answerRadioButton;
////	private IFilledQuestionnaire filledQuestionnaire = new FilledQuestionnaire();
////	
////	public Elbow(){
////	}
////	
////	static {
////		EntityManager em = new EntityManager();
////		quests = em.getTemplateNames();
////	}
////		
////	public void safe(LoginController lc){
////		System.out.println(lc);
////		System.out.println(questselected);
////		System.out.println(filledQuestionnaire.getAnswers().size());
////		filledQuestionnaire.setQuestionnaireName(questselected);
////		//lc.addAnswer(filledQuestionnaire);
////		filledQuestionnaire = new FilledQuestionnaire();
////	}
////	
////	public void addString(final AjaxBehaviorEvent event){
////		IAnswer a = new AnswerString();
////		a.setAnswer(answerString);
////		filledQuestionnaire.addAnswers(a);
////		System.out.println("->" + answerString);
////	}
////	
////	public void addRadioButton(final AjaxBehaviorEvent event){
////		String i1 = event.getComponent().getId();
////		System.out.println(i1);
////		int i2 = Integer.parseInt(i1.substring(5));
//////		Answer a = new AnswerRadioButton();
//////		a.setAnswer(answerRadioButton);
//////		while(answers.size() < i2){
//////			answers.add(null);
//////		}
//////		answers.set(i2-1, a);
////		System.out.println("->" + answerRadioButton);
////	}
////	
////	public void addCheckbox(final AjaxBehaviorEvent event){
////		IAnswer a = new AnswerCheckbox();
////		a.setAnswer(answerCheckbox);
////		filledQuestionnaire.addAnswers(a);
////		System.out.println("->" + answerCheckbox);
////	}
////	
////	public String getAnswerString() {
////		return answerString;
////	}
////	public void setAnswerString(String answerString) {
////		System.out.println("nichts");
////		this.answerString = answerString;
////	}
////	public String getAnswerRadioButton() {
////		return answerRadioButton;
////	}
////	public void setAnswerRadioButton(String answerRadioButton) {
////		System.out.println(answerRadioButton);
////		this.answerRadioButton = answerRadioButton;
////		System.out.println(answerRadioButton);
////	}
////	public List<String> getAnswerCheckbox() {
////		return answerCheckbox;
////	}
////	public void setAnswerCheckbox(List<String> answerCheckbox) {
////		this.answerCheckbox = answerCheckbox;
////	}
////		
////	public List<String> getQuests(){
////		return quests;
////	}
//}
