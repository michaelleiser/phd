package org.bfh.phd.interfaces;

/**
 * A paginator is actually a view on a list, which consists of several pages and is able to navigate through the list.
 * 
 * @author leism3, koblt1
 */
public interface IPaginator {
	
	/**
	 * @return
	 * 			the number of entries displayed on one page
	 */
	public int getPagesize();

	/**
	 * @param pagesize
	 * 			the number of entries displayed on one page
	 */
	public void setPagesize(int pagesize);

	/**
	 * @return
	 * 			the currently displayed page number
	 */
	public int getPagenr();
	
	/**
	 * @param pagenr
	 * 			the currently displayed page number
	 */
	public void setPagenr(int pagenr);

	/**
	 * @return
	 * 			the first displayed entry
	 */
	public int getFirst();
	
	/**
	 * @param first
	 * 			entry to display
	 */
	public void setFirst(int first);

	/**
	 * @return
	 * 			the absolute number of entries
	 */
	public int getSize();

	/**
	 * @param size
	 * 			the absolute number of entries
	 */
	public void setSize(int size);
	
	/**
	 * @return
	 * 			true if the current page has a previous page
	 */
	public boolean hasPrevious();
	
	/**
	 * @return
	 * 			true if the current page has a next page
	 */
	public boolean hasNext();
	
	/**
	 * Go one page backward.
	 */
	public void backward();
	
	/**
	 * Go one page forward.
	 */
	public void forward();
	
}
