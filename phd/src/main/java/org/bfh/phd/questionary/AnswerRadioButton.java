package org.bfh.phd.questionary;

import org.bfh.phd.interfaces.IAnswer;

public class AnswerRadioButton implements IAnswer<String, String> {
	
	private String answer = "";
	
	public AnswerRadioButton(){
		
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

	@Override
	public String getTyp() {
		return "RadioButton";
	}
}