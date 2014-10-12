package org.bfh.phd.interfaces;

/**
 * @author leism3, koblt1
 *
 */
public interface IPaginator {
	
	static int pagesize = 10;
	static int pagenr = 1;
	static int first = 0;
	static int size = 0;
	
	/**
	 * @return
	 */
	public int getPagesize();

	/**
	 * @param pagesize
	 */
	public void setPagesize(int pagesize);

	/**
	 * @return
	 */
	public int getPagenr();
	
	/**
	 * @param pagenr
	 */
	public void setPagenr(int pagenr);

	/**
	 * @return
	 */
	public int getFirst();
	
	/**
	 * @param first
	 */
	public void setFirst(int first);

	/**
	 * @return
	 */
	public int getSize();

	/**
	 * @param size
	 */
	public void setSize(int size);
	
	/**
	 * @return
	 */
	public boolean hasPrevious();
	
	/**
	 * @return
	 */
	public boolean hasNext();
	
	/**
	 * @return
	 */
	public String backward();
	
	/**
	 * @return
	 */
	public String forward();
	
}
