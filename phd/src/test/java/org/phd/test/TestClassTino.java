//package org.phd.test;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//import junit.framework.Assert;
//
//import org.bfh.phd.EntityManager;
//import org.bfh.phd.FilledQuestionnaire;
//import org.bfh.phd.Patient;
//import org.bfh.phd.questionary.AnswerCheckbox;
//import org.bfh.phd.questionary.QuestionCheckbox;
//import org.junit.Before;
//import org.junit.Test;
//
//public class TestClassTino {
//
//	EntityManager em;
//	String art = "`test.art`";
//	String quest = "`test.quest`";
//	String fragebogen = "`test.fragebogen`";
//	
//	@Test
//	public void newQuestionnari(){
//		
//	}
//	
//	@Before
//	public void init(){
//		em = new EntityManager();
//	}
//	
//	@Test
//	public void testInsert(){
//		AnswerCheckbox ac = new AnswerCheckbox();
//		ac.addAnswer("test 1");
//		ac.addAnswer("test 2");
//		QuestionCheckbox qc = new QuestionCheckbox();
//		qc.addAnswerPossibility("test 1");
//		qc.addAnswerPossibility("test 2");
//		ArrayList<String> list = new ArrayList<String>();
//		list.add("test 5");
//		list.add("test 6");
//		qc.setAnswer(list);
//		FilledQuestionnaire f = new FilledQuestionnaire();
//		f.addAnswers(ac);
//		f.addQuestions(qc);
//		Patient p = em.getPatient(em.getStaff(706), 928);
//		em.addAnswer(p, f);
//	}
//}
