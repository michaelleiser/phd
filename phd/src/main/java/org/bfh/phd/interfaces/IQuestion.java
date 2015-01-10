package org.bfh.phd.interfaces;

/**
 * 
 * 
 * @author leism3, koblt1
 *
 * @param <C> is a collection of answer possibilities
 * @param <D> is a string or collection of answers
 */
public interface IQuestion <C, D> {
	
	/**
	 * @return the order identifications number
	 */
	public int getId();
	
	/**
	 * @param i set the place on the order
	 */
	public void setId(int i);

	/**
	 * @return the questionstring
	 */
	public String getQuestion();
	
	/**
	 * @param o set the questionstring
	 */
	public void setQuestion(String o);
	
	/**
	 * @param o add a possible answer to the other answers
	 */
	public void addAnswerPossibility(String o);
	
	/**
	 * @return the possible answers
	 */
	public C getAnswerPossibilities();
	
	/**
	 * @param o set all possible answers at once
	 */
	public void setAnswerPossibilities(C o);

	/**
	 * @return the answer
	 */
	public D getAnswer();
	
	/**
	 * @return the answer as String
	 */
	public String getAnswerToString();
	
	/**
	 * @param o
	 *            is the answer that is to set
	 */
	public void setAnswer(D o);

	/**
	 * @return the database primkey
	 */
	public int getDBid();
	public void setDBid(int i);
	
}