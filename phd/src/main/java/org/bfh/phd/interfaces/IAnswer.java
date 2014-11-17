package org.bfh.phd.interfaces;

import java.util.List;

public interface IAnswer <A, B>  {
	
	public A getAnswer();
	
	public void setAnswer(A o);

	public void addAnswer(B o);

}
