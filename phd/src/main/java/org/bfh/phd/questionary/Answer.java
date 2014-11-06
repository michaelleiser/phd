package org.bfh.phd.questionary;

public interface Answer <A, B>  {
	
	A getAnswer();
	
	void setAnswer(A o);

	void addAnswer(B o);
	
	String getTyp();

}
