package org.bfh.phd.questionary;

import java.util.List;

public interface Question <A, B> {
	
	public int getId();
	
	public void setId(int i);

	public String getType();
	
	public void setType(A o);

	public String getQuestion();
	
	public void setQuestion(B o);
	
	public void addAnswerPossibility(String o);
	
	public List<String> getAnswerPossibilities();
	
	public void setAnswerPossibilities(List<String> o);

}
