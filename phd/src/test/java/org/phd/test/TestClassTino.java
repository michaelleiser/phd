//package org.phd.test;
//
//import java.util.List;
//import java.util.Random;
//
//import junit.framework.Assert;
//
//import org.bfh.phd.EntityManager;
//import org.bfh.phd.questionary.Question;
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
////	@Test
////	public void dbTest(){
////		Questionnari q = new Questionnari();
////		q.setId(7);
////		List<Questionnari> qq = q.getQuestionnaris();
////		for(int i = 0; i < qq.size();i++){
////		System.err.println(qq.get(i).getQ5());
////		}
////	}
////	
////	@Test
////	public void searchQuestionnari(){
////		Questionnari q = new Questionnari();
////		q.setPatient(1);
////		List<Questionnari> qq = q.getListOfQuestionnaris();
////		for(int i = 0; i < qq.size();i++){
////		System.err.println(qq.get(i).getId());
////		System.err.println(qq.get(i).getOp());
////		System.err.println(qq.get(i).getDate());
////		}
////	}
//	
//	@Test
//	public void addArt(){
//		em.addNewArt(art, "test");
//	}
//	
//	@Test
//	public void addNewQuestionari(){
//		em.addNewQuestionnari(1, 1, quest);
//	}
//	
//	//@Test
//	public void addNewAnswers(){
//		Random randomgenerator = new Random();
//		int i = randomgenerator.nextInt(100);
//
//		Assert.assertEquals(2, em.addNewAnswers("`test.fragebogenanworten`", Integer.toString(i), 1,0));
//		Assert.assertEquals(1, em.addNewAnswers("`test.fragebogenanworten`", Integer.toString(i), 2,0));
//		
//		
//	}
//	
//	@Test
//	public void getQuestions(){
//		
//		List<Question> list = em.getQuestion("arm");
//		for(int i = 0; i < list.size(); i++){
//			System.err.println(list.get(i).toString());
//			if(i == 1){
//			List<String> test = list.get(i).getAnswerPossibilities();
//			for (int j = 0; j < test.size(); j++){
//				System.err.println(test.get(j));
//			}}
//		}
//			
////		em.getQuestion(fragebogen, "knie");
//		
//		
//	}
//}
