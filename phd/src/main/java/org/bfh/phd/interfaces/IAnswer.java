package org.bfh.phd.interfaces;

import java.util.List;

public interface IAnswer <A, B>  {
	
	A getAnswer();
	
	void setAnswer(A o);

	void addAnswer(B o);
	
	String getTyp();

}
