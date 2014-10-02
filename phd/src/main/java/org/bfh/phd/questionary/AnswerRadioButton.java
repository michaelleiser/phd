package org.bfh.phd.questionary;

public class AnswerRadioButton implements Answer<String, String> {
	
	private String answer = "";
	
	public AnswerRadioButton(){
		
	}
	
	@Override
	public String getAnswer() {
		return answer;
	}
	
	@Override
	public void setAnswer(String s) {
		answer = s;
	}

	@Override
	public void addAnswer(String s){
		answer = s;
	}
	
	@Override
	public String toString(){
		return answer;
	}

}
