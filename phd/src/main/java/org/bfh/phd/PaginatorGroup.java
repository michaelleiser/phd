package org.bfh.phd;

import org.bfh.phd.interfaces.IPaginator;

/**
 * This paginator is used for a group list.
 * 
 * @author leism3, koblt1
 */
public class PaginatorGroup implements IPaginator{
	
	private static int pagesize = 10;
	private static int pagenr = 1;
	private static int first = 0;
	private static int size = 0;
	
	public PaginatorGroup(){
	}
	
	@Override
	public int getPagesize() {
		return this.pagesize;
	}

	@Override
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	@Override
	public int getPagenr() {
		return this.pagenr;
	}
	
	@Override
	public void setPagenr(int pagenr) {
		this.pagenr = pagenr;
	}

	@Override
	public int getFirst() {
		return this.first;
	}
	
	@Override
	public void setFirst(int first) {
		this.first = first;
	}

	@Override
	public int getSize() {
		return this.size;
	}

	@Override
	public void setSize(int size) {
		this.size = size;
	}
	
	@Override
	public boolean hasPrevious(){
		return this.first > 0;
	}
	
	@Override
	public boolean hasNext(){
		return ((this.size - this.first) - this.pagesize) > 0;
	}
	
	@Override
	public void backward(){
		this.first = this.first - this.pagesize;
		if(this.first < 0){
			this.first = 0;
		}
		this.pagenr--;
	}
	
	@Override
	public void forward(){
		this.first = this.first + this.pagesize;
		this.pagenr++;
	}
	
}
