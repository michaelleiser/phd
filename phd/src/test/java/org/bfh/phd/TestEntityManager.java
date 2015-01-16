package org.bfh.phd;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestEntityManager{
	CreateTestAnswers ta = new CreateTestAnswers();
	
//	@Test
//	public void generate(){
//		ta.createQuestionnaire();
//		ta.createAnswers();
//	}
	
}
//
//	EntityManager em;
//	
//	@Before
//	public void init(){
//		em = new EntityManager();
//	}
//	
//	@After
//	public void after(){
//	}
//
////	@Test
////	public void testInsertNewAnswer(){
////		int a = 0,b = 0,c = 0;
////		try {
////			a = em.insertAnswer("test");
////			b = em.insertAnswer("aha");
////			c = em.insertAnswer("test");
////		} catch (SQLException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}finally{
////			assertEquals(a, 1);
////			assertEquals(b, 2);
////			assertEquals(c, 1);
////		}
////	}
////
////	@Test
////	public void getTemplate(){
////		System.err.println("getTemplate");
////		List<Questionnair> list = em.getTemplate("test 1");
////		for(Questionnair q:list){
////			System.err.println(q.getId());
////			System.err.println(q.getQuestion());
////			System.err.println(q.getType());
////			System.err.println(q.getAnswer());
////		}
////	}
//////	@Test
//////	public void testGetPossibleAnswer(){
//////		List<String> s = em.getPossibilities("", 1);
//////		assertEquals(s.get(0), "viel");
//////		assertEquals(s.get(1), "mittel");
//////		assertEquals(s.get(2), "wenig");
//////	}
////	
////	@Test
////	public void testAddAnswer(){
////		List<Answer> a = new ArrayList<Answer>();
////		Answer an = new AnswerString();
////		an.addAnswer("tester");
////		a.add(an);
////		a.add(an);
////		a.add(an);
////		a.add(an);
////		em.addAnswer("knee", a , 3);
////	}
////	
//////	@Test
//////	public void getTyps(){
//////		List<String> list = em.getType();
//////		for(int i = 0; i < list.size(); i++){
//////			System.err.println(list.get(i));
//////		}
//////	}
////	
////	@Test
////	public void addNewQuestionTemplate() throws SQLException{
////		List<String> list = new ArrayList<String>();
////		list.add("1");
////		list.add("2");
////		list.add("3");
//////		em.addQuestionnaireTemplate("String", "geht es?", "testTemplate", list);
////	}
////	
////	
//////	@Test
//////	public void getQuestionarris(){
//////		List<ListOfQuestionnari> list = em.searchData(1);
//////		System.err.println(list.get(0).getQuestId());
//////		System.err.println(list.get(0).getDate());
//////		System.err.println(list.get(0).getTypOfQuest());
//////	}
////	
//////	@Test
//////	public void getFilledQuestion2(){
//////		System.err.println("Test 2 start");
//////		List<Question> list = em.getFilledQuestion2(10);
//////		System.err.println(list);
//////		for(int i = 0; i < list.size(); i++){
//////			System.err.println("Frage: " + list.get(i).getQuestion());
//////			System.err.println("Mögliche Antworten:" + list.get(i).getAnswerPossibilities());
//////			System.err.println("Gegebene Antwort:" + list.get(i).getAnswer());
//////		}
//////	}
////	
//////	@Test
//////	public void test(){
//////		System.err.println(em.getAnswers("elbow", 1));
//////		System.err.println(em.getAnswers("knee", 2));
//////	}
////	
//////	@Test
//////	public void test1(){
//////		System.err.println("Start test 1");
//////		LoginController l = new LoginController();
//////		List<Question> questions = l.getQuestions();
//////		List<Answer> answers = l.getAnswers( 1);
//////		Knee k = new Knee();
//////		k.setQuestions(questions);
//////		k.setAnswers(answers);
//////		for(Question q : k.getQuestions()){
//////			System.err.println("Frage " + q);
//////			List<String> pos = q.getAnswerPossibilities();
//////			if(pos != null){
//////				for(String s : pos){
//////					System.err.println("-> " + s);
//////				}			
//////			}
//////		}
//////		
//////		for(Answer a : k.getAnswers()){
//////			System.err.println("Antwort " + a);
//////		}
//////	}
////	
//////	@Test
//////	public void testGetQuestion(){
//////		System.err.println("GetAnswer start");
//////		List<Question> list = em.getQuestionnaris2(2);
//////		System.err.println("size of list: " + list.size());
//////		for(int i = 0; i < list.size(); i++){
//////			System.err.println(list.get(i).getQuestion());
//////		}
//////	}
////	
//////	@Test
//////	public void testGetAnswer(){
//////		List<Tools> list = em.getFilledQuestion(2);
//////		System.err.println(list.size());
//////		for(int i = 0; i < list.size(); i++){
//////			System.err.println(list.get(i).getString());
//////		}
//////		
//////	}
//	
//	
//	
//	
//	@Test
//	public void testAddNewTemplate(){
//		ArrayList<String> pos1 = new ArrayList<String>();
//		ArrayList<String> pos2 = new ArrayList<String>();
//		pos1.add("antwort 1");
//		pos1.add("antwort 2");
//		pos2.add("antwort 11");
//		pos2.add("antwort 12");
//		pos2.add("antwort 13");
//		
//		
//		em.addQuestionnaireTemplate("String", "frage 1", "test", null, 1);
//		em.addQuestionnaireTemplate("RadioButton", "frage 2", "test", pos1, 2);
//		em.addQuestionnaireTemplate("Checkbox", "frage 3", "test", pos2, 3);
//	}
//	
//	@Test
//	public void testGetFilledQuestionnaire(){
//		em.getFilledQuestion(1, "knee");
//	}
//	
//	@Test
//	public void testGetTemplate(){
//		em.getTemplate("test");
//	}
//	
////	@Test
////	public void testDeletTemplateQuestion(){
////		QuestionnairTools qt = new QuestionnairTools();
////		qt.setDbId(1);
////		try {
////			em.deletTemplateQuestion(qt);
////		} catch (SQLException e) {
////			e.printStackTrace();
////		}
////	}
//	
//	@Test
//	public void testInsertAnswer() throws SQLException{
//		int i = em.insertAnswer("antwort 99");
//		System.out.println(i);
//	}
//	
//	@Test
//	public void testGetEmptyQuestionnaires(){
//		FilledQuestionnaire f = em.getEmptyQuestionnaire("test 10");
//		assertEquals(f.getQuestions().get(0).toString(), "frage 10");
//		assertEquals(f.getQuestions().get(1).toString(), "frage 11");
//		assertEquals(f.getQuestions().get(2).toString(), "frage 12");
//	}
//	
//	@Test
//	public void testGetPossibilities(){
//		ArrayList<String> a = new ArrayList<String>();
//		ArrayList<String> b = new ArrayList<String>();
//		a = (ArrayList<String>) em.getPos(90);
//		b = (ArrayList<String>) em.getPos(91);
//		System.out.println(a);
//		System.out.println(b);
//	}
//	
//
//}
