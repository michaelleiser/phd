//package org.bfh.phd;
//
//import static org.junit.Assert.*;
//
//import java.sql.Date;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.bfh.phd.interfaces.IAnswer;
//import org.bfh.phd.interfaces.IFilledQuestionnaire;
//import org.bfh.phd.interfaces.IQuestion;
//import org.bfh.phd.questionary.AnswerCheckbox;
//import org.bfh.phd.questionary.AnswerRadioButton;
//import org.bfh.phd.questionary.AnswerString;
//import org.bfh.phd.questionary.QuestionCheckbox;
//import org.bfh.phd.questionary.QuestionRadioButton;
//import org.bfh.phd.questionary.QuestionString;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//public class FilledQuestionnaireTest {
//
//	private IFilledQuestionnaire f;
//	
//	@Before
//	public void init(){
//		f = new FilledQuestionnaire();
//	}
//	
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	private void initAnswer(){
//		ArrayList<IAnswer> list = new ArrayList<IAnswer>();
//		IAnswer a = new AnswerString();
//		a.setAnswer("answer 1");
//		IAnswer b = new AnswerCheckbox();
//		b.addAnswer("answer 2.1");
//		b.addAnswer("answer 2.2");
//		IAnswer c = new AnswerRadioButton();
//		c.setAnswer("answer 3");
//		IAnswer d = new AnswerCheckbox();
//		List<String> alist = new ArrayList<String>();
//		alist.add("answer 4.1");
//		alist.add("answer 4.2");
//		d.setAnswer(alist);
//		list.add(a);
//		list.add(b);
//		list.add(c);
//		list.add(d);
//		f.setAnswers(list);
//	}
//	
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	private void initQuestion() {
//		ArrayList<IQuestion> list = new ArrayList<IQuestion>();
//		
//		IQuestion a = new QuestionString();
//		a.setQuestion("frage 1");
//		IQuestion b = new QuestionCheckbox();
//		b.setQuestion("frage 2");
//		IQuestion c = new QuestionRadioButton();
//		c.setQuestion("frage 3");
//		IQuestion d = new QuestionCheckbox();
//		d.setQuestion("frage 4");
//		list.add(a);
//		list.add(b);
//		list.add(c);
//		list.add(d);
//		f.setQuestions(list);
//	}
//	
//	@After
//	public void end(){
//		f = null;
//	}
//	
//	@SuppressWarnings("deprecation")
//	@Test
//	public void testDateAndName(){
//		f.setDate(new Date(2014, 3, 7));
//		f.setQuestionnaireName("testQuestionnaire");
//		assertEquals(new Date(2014, 3, 7), f.getDate());
//		assertEquals("testQuestionnaire", f.getQuestionnaireName());
//	}
//	
//	@SuppressWarnings("rawtypes")
//	@Test
//	public void testAnswers(){
//		initAnswer();
//		List<IAnswer> list = f.getAnswers();
//		assertEquals(list.get(0).getAnswer(), "answer 1");
//		assertEquals(list.get(1).toString(), "answer 2.1, answer 2.2");
//		assertEquals(list.get(2).getAnswer(), "answer 3");
//		assertEquals(list.get(3).toString(), "answer 4.1, answer 4.2");
//	}
//	
//	@SuppressWarnings("rawtypes")
//	@Test
//	public void testQuestions(){
//		initQuestion();
//		List<IQuestion> list = f.getQuestions();
//		assertEquals(list.get(0).getQuestion(), "frage 1");
//		assertEquals(list.get(1).getQuestion(), "frage 2");
//		assertEquals(list.get(2).getQuestion(), "frage 3");
//		assertEquals(list.get(3).getQuestion(), "frage 4");
//	}
//	
//	@Test
//	public void testGetList(){
//		initQuestion();
//		initAnswer();
//		ArrayList<ArrayList<String>> list = f.getList();
//		assertEquals(list.get(2).get(0), "frage 3");
//		assertEquals(list.get(0).get(1), "answer 1");
//	}
//}
