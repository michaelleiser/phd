package org.bfh.phd.interfaces;

/**
 * 
 * 
 * @author leism3, koblt1
 *
 * @param <A> is the type of an answer (string or collection)
 * @param <B> is the type of an answer (string or collection)
 */
public interface IAnswer<A, B> {

	/**
	 * @return the answer
	 */
	public A getAnswer();

	/**
	 * @param o
	 *            is the answer that is to set
	 */
	public void setAnswer(A o);

	/**
	 * @param o
	 *            add a answer to the other
	 */
	public void addAnswer(B o);

	/**
	 * @return the database primkey
	 */
	public int getDb();

	/**
	 * @param db
	 *            set the database primkey
	 */
	public void setDb(int db);
}
