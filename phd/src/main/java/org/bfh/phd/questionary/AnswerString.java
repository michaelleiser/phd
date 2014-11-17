package org.bfh.phd.questionary;

import java.util.ArrayList;
import java.util.List;

import org.bfh.phd.interfaces.IAnswer;

public class AnswerString implements IAnswer<String, String> {
	
	private String answer = "";
	
	public AnswerString(){
		
	}
	public List<String> getTest(){
		ArrayList<String> a = new ArrayList<String>();
		a.add(answer);
		return a;
	}
	
	@Override
	public String getAnswer() {
		return answer;
	}

	@Override
	public void setAnswer(String l) {
		answer = l;
	}

	@Override
	public void addAnswer(String s){
		answer = s;
	}
	
	@Override
	public String toString(){;
		return answer.toString();
	}
}