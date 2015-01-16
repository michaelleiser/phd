package org.bfh.phd.questionnaire;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import org.bfh.phd.EntityManager;
import org.bfh.phd.LoginController;
import org.bfh.phd.Questionnaire;

/**
 * @author leism3, koblt1
 *
 */
@ManagedBean(name = "template", eager = true)
@SessionScoped
public class Template {

	private static int i = 1, x = 0;
	private int eNumber = 0;
	private QuestionnaireTools eQuestion = new QuestionnaireTools();
	private String name;
	private String templatename = "";
	private String questionString;
	private String answerString;
	private static String typeselected;
	private static String templateNameSelected;
	private List<QuestionnaireTools> test = new ArrayList<QuestionnaireTools>();
	private List<QuestionnaireTools> edit = new ArrayList<QuestionnaireTools>();
	private List<String> answerRadioButton = new ArrayList<String>();
	private List<String> answerCheckbox = new ArrayList<String>();
	private static List<String> type;
	private static List<String> templateName;
	private static List<Integer> y;
	private QuestionnaireTools t = new QuestionnaireTools();
	private static EntityManager em;

	static {
		init();
	}

	private static void init() {
		em = new EntityManager();
		templateName = em.getTemplateNames();
		Map<String, Integer> map = em.getType();
		type = new ArrayList<String>(map.keySet());
		y = new ArrayList<Integer>();
		for (int j = 1; j <= 10; j++) {
			y.add(new Integer(j));
		}
	}

	private void clear() {
		name = templatename = questionString = answerString = "";
		answerRadioButton.clear();
		answerCheckbox.clear();
		test.clear();
		edit.clear();
	}
	
	private void clearAnswer(){
		answerCheckbox.clear();
		answerRadioButton.clear();
		answerString = "";
	}

	public boolean isEmpty() {
		return templatename.isEmpty();
	}

	public boolean isNotEmpty() {
		return !templatename.isEmpty();
	}

	public boolean isTemplateEmpty() {
		return !test.isEmpty();
	}

	public List<QuestionnaireTools> getQuestion() {
		return test;
	}

	public void add() {
		t = new QuestionnaireTools();
		eQuestion = new QuestionnaireTools();
		clearAnswer();
	}

	public void save(LoginController lc) {
		int i = 1;
		for (QuestionnaireTools t : test) {
			lc.addQuestionnaireTemplate(t.getType(), t.getQuestion(), templatename, t.getAnswer(), i);
			i++;
		}
		init();
		clear();
	}

	public void saveAddEdit(LoginController lc) {
		lc.changeQuestionNr(templateNameSelected, eNumber);
		lc.addQuestionnaireTemplate(eQuestion.getType(),
				eQuestion.getQuestion(), templateNameSelected,
				eQuestion.getAnswer(), eNumber + 1);
		eNumber = 0;
		setTemplates();
		add();
	}

	public void addString(final AjaxBehaviorEvent event) {
		t.setQuestion(questionString);
		t.setType("String");
		t.addPossibleAnswer("-");
		if (event.getComponent().getAttributes().get("edit").equals("1")) {
			t.setId(i);
			eQuestion = t;
		} else {
			t.setId(i);
			test.add(t);
		}
		i++;
	}

	public void addRadioButton(final AjaxBehaviorEvent event) {
		t.setQuestion(questionString);
		t.setType("RadioButton");
		for (String s : answerRadioButton) {
			if (s != "") {
				t.addPossibleAnswer(s);
			}
		}
		if (event.getComponent().getAttributes().get("edit").equals("1")) {
			t.setId(i);
			eQuestion = t;
		} else {
			t.setId(i);
			test.add(t);
		}
		i++;
		answerRadioButton.clear();
	}

	public void addCheckbox(final AjaxBehaviorEvent event) {
		t.setQuestion(questionString);
		t.setType("Checkbox");
		for (String s : answerCheckbox) {
			if (s != "") {
				t.addPossibleAnswer(s);
			}
		}
		if (event.getComponent().getAttributes().get("edit").equals("1")) {
			t.setId(i);
			eQuestion = t;

		} else {
			t.setId(i);
			test.add(t);
		}
		i++;
		answerCheckbox.clear();
	}

	public List<String> getTemplateNames() {
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

	public void addQuestion(final AjaxBehaviorEvent event) {
	}

	public void addAnswer(final AjaxBehaviorEvent event) {
	}

	public String getQuestionString() {
		return questionString;
	}

	public void setQuestionString(String questionString) {
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

	private void addCheckBox(String s) {
		this.answerCheckbox.add(s);
	}

	private void addRadioButton(String test) {
		this.answerRadioButton.add(test);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String nameToTemp() {
		templatename = this.name;
		return "";
	}

	private void setTemplates() {
		edit.clear();
		edit = em.getTemplate(templateNameSelected);
	}

	public List<QuestionnaireTools> getTemplates() {
		return edit;
	}

	public void editTemplate() {
		System.out.println("edit Question");
	}

	public void deleteQuestion(LoginController lc, QuestionnaireTools q) {
		lc.deleteTemplateQuestion(q);
		setTemplates();
	}

	public void deleteTemplate(LoginController lc) {
		System.out.println("not implemented");
	}

	public void saveEdit(LoginController lc) {
		for (QuestionnaireTools q : edit) {
			if (q.isEditable()) {
				q.setEditable(false);
				lc.editQuestion(q);
			}
		}
	}

	public String editAction(QuestionnaireTools q) {
		q.setEditable(true);
		return null;
	}

	public String addAction(QuestionnaireTools q) {
		eNumber = q.getId();
		return null;
	}

	public int getE() {
		return eNumber;
	}

	public void setE(int e) {
		this.eNumber = e;
	}

	public List<String> getTypes() {
		return type;
	}

	public String typeChanged(ActionEvent evt) {
		UIComponent comp = evt.getComponent();
		typeselected = (String) comp.getAttributes().get("value");
		return "";
	}

	public String getTypeselected() {
		return typeselected;
	}

	public void setTypeselected(String questselected) {
		clearAnswer();
		this.typeselected = questselected;
	}

	public List<Integer> getI() {
		return y;
	}

	public String numberChanged(ActionEvent evt) {
		UIComponent comp = evt.getComponent();
		x = Integer.getInteger((String) comp.getAttributes().get("value"));
		return "";
	}

	public int getNumberselected() {
		return x;
	}

	public void setNumberselected(int k) {
		this.x = k;
	}
}
