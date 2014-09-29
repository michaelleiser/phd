package org.bfh.phd.questionary;

import java.util.ArrayList;
import java.util.List;

public class AnswerRadioButton implements Answer<List<String>, String> {
	
	private List<String> answer = new ArrayList<String>();
	
	public AnswerRadioButton(){
		
	}
	
	@Override
	public List<String> getAnswer() {
		return answer;
	}
	
	@Override
	public void setAnswer(List<String> l) {
		answer = l;
	}

	@Override
	public void addAnswer(String s){
		answer.add(s);
	}
	
	@Override
	public String toString(){
		String ret = "";
		for(String s : answer){
			ret = ret + " " + s;
		}
		return "RB: " + ret;
	}

}
