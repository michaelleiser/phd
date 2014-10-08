package org.bfh.phd;

public class Paginator {
	
	private int pagesize = 10;
	private int pagenr = 1;
	private int first = 0;
	private int size = 0;
	
	Paginator(int pagesize){
		this.pagesize = pagesize;
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
