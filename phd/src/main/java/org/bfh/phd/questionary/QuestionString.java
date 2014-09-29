package org.bfh.phd.questionary;


public class QuestionString implements Question <String, String> {
	
	private String type;
	private String question;

	@Override
	public String getType() {
		return type;
	}
	
	@Override
	public void setType(String o) {
		type = o;
	}

	@Override
	public String getQuestion() {
		return question;
	}

	@Override
	public void setQuestion(String o) {
		question = o;
	}

	@Override
	public String toString(){
		return type + " " + question;
	}

}
