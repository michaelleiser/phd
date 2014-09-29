package org.phd.test;

public class QuestionString implements Question {
	
	private String type;
	private String question;

	@Override
	public void setQuestion(Object o) {
		question = (String) o;
	}

	@Override
	public String toString(){
		return type + " " + question;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public String getQuestion() {
		return question;
	}

	@Override
	public void setType(Object o) {
		type = (String) o;
	}


}
