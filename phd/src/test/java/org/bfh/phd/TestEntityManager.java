package org.bfh.phd;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sound.midi.SysexMessage;

import org.bfh.phd.questionary.Answer;
import org.bfh.phd.questionary.AnswerCheckbox;
import org.bfh.phd.questionary.AnswerRadioButton;
import org.bfh.phd.questionary.AnswerString;
import org.bfh.phd.questionary.Knee;
import org.bfh.phd.questionary.Question;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestEntityManager{

	EntityManager em;
	
	@Before
	public void init(){
		em = new EntityManager();
		em.init();
	}
	
	@After
	public void after(){
		em.closed();
	}

//	@Test
//	public void testGetPossibleAnswer(){
//		List<String> s = em.getPossibilities("", 1);
//		assertEquals(s.get(0), "viel");
//		assertEquals(s.get(1), "mittel");
//		assertEquals(s.get(2), "wenig");
//	}
	
	@Test
	public void testAddAnswer(){
		List<Answer> a = new ArrayList<Answer>();
		Answer an = new AnswerString();
		an.addAnswer("tester");
		a.add(an);
		a.add(an);
		a.add(an);
		a.add(an);
		em.addAnswer("knee", a , 3);
	}
	
//	@Test
//	public void getTyps(){
//		List<String> list = em.getType();
//		for(int i = 0; i < list.size(); i++){
//			System.err.println(list.get(i));
//		}
//	}
	
	@Test
	public void addNewQuestionTemplate() throws SQLException{
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
//		em.addQuestionnaireTemplate("String", "geht es?", "testTemplate", list);
	}
	
	
//	@Test
//	public void getQuestionarris(){
//		List<ListOfQuestionnari> list = em.searchData(1);
//		System.err.println(list.get(0).getQuestId());
//		System.err.println(list.get(0).getDate());
//		System.err.println(list.get(0).getTypOfQuest());
//	}
	
//	@Test
//	public void getFilledQuestion2(){
//		System.err.println("Test 2 start");
//		List<Question> list = em.getFilledQuestion2(10);
//		System.err.println(list);
//		for(int i = 0; i < list.size(); i++){
//			System.err.println("Frage: " + list.get(i).getQuestion());
//			System.err.println("Mögliche Antworten:" + list.get(i).getAnswerPossibilities());
//			System.err.println("Gegebene Antwort:" + list.get(i).getAnswer());
//		}
//	}
	
//	@Test
//	public void test(){
//		System.err.println(em.getAnswers("elbow", 1));
//		System.err.println(em.getAnswers("knee", 2));
//	}
	
//	@Test
//	public void test1(){
//		System.err.println("Start test 1");
//		LoginController l = new LoginController();
//		List<Question> questions = l.getQuestions();
//		List<Answer> answers = l.getAnswers( 1);
//		Knee k = new Knee();
//		k.setQuestions(questions);
//		k.setAnswers(answers);
//		for(Question q : k.getQuestions()){
//			System.err.println("Frage " + q);
//			List<String> pos = q.getAnswerPossibilities();
//			if(pos != null){
//				for(String s : pos){
//					System.err.println("-> " + s);
//				}			
//			}
//		}
//		
//		for(Answer a : k.getAnswers()){
//			System.err.println("Antwort " + a);
//		}
//	}
	
//	@Test
//	public void testGetQuestion(){
//		System.err.println("GetAnswer start");
//		List<Question> list = em.getQuestionnaris2(2);
//		System.err.println("size of list: " + list.size());
//		for(int i = 0; i < list.size(); i++){
//			System.err.println(list.get(i).getQuestion());
//		}
//	}
	
//	@Test
//	public void testGetAnswer(){
//		List<Tools> list = em.getFilledQuestion(2);
//		System.err.println(list.size());
//		for(int i = 0; i < list.size(); i++){
//			System.err.println(list.get(i).getString());
//		}
//		
//	}
}
