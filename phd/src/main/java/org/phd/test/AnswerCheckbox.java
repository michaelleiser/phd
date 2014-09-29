package org.phd.test;

import java.util.List;

public class AnswerCheckbox implements Answer {
	
	private List<String> answer;

	@Override
	public void setAnswer(Object o) {
		answer = (List<String>) o;
	}
	
	
	@Override
	public String toString(){
		String ret = "";
		for(String s : answer){
			ret = ret + " " + s;
		}
		return ret;
	}

}
