package org.phd.test;

public class AnswerString implements Answer {
	
	private String answer;

	@Override
	public void setAnswer(Object o) {
		answer = (String) o;
	}

	@Override
	public String toString(){
		return answer;
	}

}
