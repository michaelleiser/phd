package org.bfh.phd.questionary;

public interface Question <A, B> {

	public String getType();
	
	public void setType(A o);

	public String getQuestion();
	
	public void setQuestion(B o);

}
