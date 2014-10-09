package org.bfh.phd.questionary;

public class AnswerString implements Answer<String, String> {
	
	private String answer = "";
	
	public AnswerString(){
		
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