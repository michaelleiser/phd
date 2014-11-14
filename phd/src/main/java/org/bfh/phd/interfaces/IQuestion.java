package org.bfh.phd.interfaces;

import java.util.List;

public interface IQuestion <A, B> {
	
	public int getId();
	
	public void setId(int i);

	public String getQuestion();
	
	public void setQuestion(B o);
	
	public void addAnswerPossibility(String o);
	
	public List<String> getAnswerPossibilities();
	
	public void setAnswerPossibilities(List<String> o);
	
	public void setAnswer(String s);
}