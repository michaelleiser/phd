package org.bfh.phd.interfaces;

/**
 * A simple department with a unique name.
 * 
 * @author leism3, koblt1
 */
public interface IDepartment {

	/**
	 * @return
	 * 			id of the department
	 */
	public int getDepartment_id();

	/**
	 * @param id
	 * 			of the department
	 */
	public void setDepartment_id(int id);

	/**
	 * @return
	 * 			name of the department
	 */
	public String getName();

	/**
	 * @param name
	 * 			of the department
	 */
	public void setName(String name);

}