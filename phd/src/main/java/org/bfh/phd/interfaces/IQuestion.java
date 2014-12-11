package org.bfh.phd.interfaces;

public interface IQuestion <A, B, C, D> {
	
	public int getId();
	
	public void setId(int i);

	public String getQuestion();
	
	public void setQuestion(B o);
	
	public void addAnswerPossibility(String o);
	
	public C getAnswerPossibilities();
	
	public void setAnswerPossibilities(C o);

	public D getAnswer();
	
	public void setAnswer(D o);

	public int getDBid();
}