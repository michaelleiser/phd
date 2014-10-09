package org.bfh.phd;

public class PaginatorGroup implements Paginator {
	
	private static int pagesize = 10;
	private static int pagenr = 1;
	private static int first = 0;
	private static int size = 0;
	
	public PaginatorGroup(){

	}
	
	public int getPagesize() {
		return this.pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getPagenr() {
		return this.pagenr;
	}
	
	public void setPagenr(int pagenr) {
		this.pagenr = pagenr;
	}

	public int getFirst() {
		return this.first;
	}
	
	public void setFirst(int first) {
		this.first = first;
	}

	public int getSize() {
		return this.size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public boolean hasPrevious(){
		return this.first > 0;
	}
	
	public boolean hasNext(){
		return ((this.size - this.first) - this.pagesize) > 0;
	}
	
	public String backward(){
		this.first = this.first - this.pagesize;
		if(this.first < 0){
			this.first = 0;
		}
		this.pagenr--;
		return null;
	}
	
	public String forward(){
		this.first = this.first + this.pagesize;
		this.pagenr++;
		return null;
	}
	
}
