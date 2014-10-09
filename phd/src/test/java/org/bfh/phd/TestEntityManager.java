package org.bfh.phd;

import java.util.List;

import javax.sound.midi.SysexMessage;

import org.bfh.phd.questionary.Answer;
import org.bfh.phd.questionary.Knee;
import org.bfh.phd.questionary.Question;
import org.junit.Before;
import org.junit.Test;

public class TestEntityManager{

	EntityManager em;
	
	@Before
	public void init(){
		em = new EntityManager();
	}
	
	@Test
	public void getQuestionarris(){
		List<ListOfQuestionnari> list = em.searchData(1);
		System.err.println(list.get(0).getQuestId());
		System.err.println(list.get(0).getDate());
		System.err.println(list.get(0).getTypOfQuest());
	}
	
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
	
	@Test
	public void testGetAnswer(){
		List<Tools> list = em.getFilledQuestion(2);
		System.err.println(list.size());
		for(int i = 0; i < list.size(); i++){
			System.err.println(list.get(i).getString());
		}
		
	}
}