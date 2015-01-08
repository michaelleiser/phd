package org.bfh.phd;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.bfh.phd.interfaces.IDepartment;

/**
 * This is a simple department with a unique name.
 * 
 * @author leism3, koblt1
 */
@ManagedBean(name = "department", eager = true)
@RequestScoped
public class Department implements Serializable, IDepartment, Comparable<Department> {

	private static final long serialVersionUID = 1L;
	
	private int department_id;
	private String name;
	
	public Department(){
		
	}
	
	@Override
	public int getDepartment_id() {
		return this.department_id;
	}

	@Override
	public void setDepartment_id(int id) {
		this.department_id = id;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString(){
		return this.name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		if (this.name == null) {
			if (other.name != null)
				return false;
		} else if (!this.name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public int compareTo(Department o) {
		String myname = this.name;
		String oname = o.name;
		return myname.compareTo(oname);
	}
	
}