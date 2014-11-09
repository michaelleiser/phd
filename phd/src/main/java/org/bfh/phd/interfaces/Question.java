package org.bfh.phd.interfaces;

import java.util.List;

public interface Question <A, B, C> {
	
	public int getId();
	
	public void setId(int i);

	public String getType();
	
	public void setType(A o);

	public String getQuestion();
	
	public void setQuestion(B o);
	
	public void addAnswerPossibility(String o);
	
	public List<String> getAnswerPossibilities();
	
	public void setAnswerPossibilities(List<String> o);
	
	public void setAnswer(String s);
	
	public C getAnswer();
}