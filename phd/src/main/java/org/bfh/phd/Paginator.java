package org.bfh.phd;

public interface Paginator {
	
	static int pagesize = 10;
	static int pagenr = 1;
	static int first = 0;
	static int size = 0;
	
	public int getPagesize();

	public void setPagesize(int pagesize);

	public int getPagenr();
	
	public void setPagenr(int pagenr);

	public int getFirst();
	
	public void setFirst(int first);

	public int getSize();

	public void setSize(int size);
	
	public boolean hasPrevious();
	
	public boolean hasNext();
	
	public String backward();
	
	public String forward();
	
}
