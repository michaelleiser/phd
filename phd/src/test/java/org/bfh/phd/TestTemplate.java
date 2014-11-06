//package org.bfh.phd;
//
//import static org.junit.Assert.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.faces.event.AjaxBehaviorEvent;
//
//import junit.framework.AssertionFailedError;
//
//import org.bfh.phd.questionary.Questionnair;
//import org.bfh.phd.questionary.Template;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//public class TestTemplate {
//
//	private Template t;
//	private ArrayList<String> x;
//	
//	@Before
//	public void init(){
//		t = new Template();
//		x = new ArrayList<String>();
//	}
//	
//	@After
//	public void after(){
//		x.clear();
//	}
//	
////	private void initialize(){
////		t.setAnswerCheckbox("test1");
////		t.setAnswerCheckbox("test2");
////		t.setAnswerCheckbox("test3");
////		t.setAnswerCheckbox("test4");
////		x.add("test1");
////		x.add("test2");
////		x.add("test3");
////		x.add("test4");
////		x.add("test5");
////		x.add("test6");
////		x.add("test7");
////	}
////	
////	@Test
////	public void testTemplateButtons(){
////		initialize();
////		t.addCheckbox(null);
////		t.setAnswerRadioButton("test5");
////		t.setAnswerRadioButton("test6");
////		t.setAnswerRadioButton("test7");
////		t.addRadioButton(null);
////		List<Questionnair> l = t.getQuestion();
////		List<String> s = l.get(0).getAnswer();
////		int i = 0;
////		for(String st:s){
////			assertEquals(st, x.get(i));
////			i++;
////		}
////	}
//	
//
//	@Test
//	public void test(){
//		t.setTemplateNameselected("test 1");
//		List<Questionnair> list = t.getTemplates();
//		System.out.println(list.size());
//		System.out.println(list.get(0).getId());
//		System.out.println(list.get(0).getQuestion());
//		System.out.println(list.get(0).getType());
//		System.out.println(list.get(0).getAnswer());
//	}
//	
//	
//
//}
