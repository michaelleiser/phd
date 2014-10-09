package org.bfh.phd.questionary;

import java.util.ArrayList;
import java.util.List;

public class AnswerCheckbox implements Answer <List<String>, String> {
	
	private List<String> answer = new ArrayList<String>();
	
	public AnswerCheckbox(){
		
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
	public String toString(){;
		return answer.toString();
	}
	
}