package org.bfh.phd;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.bfh.phd.interfaces.IStaff;

/**
 * The staff is a member of a department, which has some credentials to authenticate to a department.
 * 
 * @author leism3, koblt1
 */
@ManagedBean(name = "staff", eager = true)
@RequestScoped
public class Staff implements Serializable, IStaff{

	private static final long serialVersionUID = 1L;
	
	public static final int DOCTOR = 1;
	public static final int STATISTICIAN = 2;
	
	private int id;
	private String name;
	private String salt;
	private String password;
	private int role;
	private boolean isActivated;
	
	private String publicKey;
	private String privateKey;
	
	public Staff(){
		
	}

	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
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
	public String getSalt(){
		return this.salt;
	}
	
	@Override
	public void setSalt(String salt){
		this.salt = salt;
	}
	
	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int getRole() {
		return this.role;
	}
	
	@Override
	public void setRole(int role) {
		this.role = role;
	}
	
	@Override
	public boolean getActivated() {
		return this.isActivated;
	}
	
	@Override
	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}

	@Override
	public String getPublicKey() {
		return this.publicKey;
	}

	@Override
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	@Override
	public String getPrivateKey() {
		return this.privateKey;
	}

	@Override
	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
	
	@Override
	public String toString(){
		return this.name + " : " + this.role + " : " + this.isActivated;
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
		Staff other = (Staff) obj;
		if (this.name == null) {
			if (other.name != null)
				return false;
		} else if (!this.name.equals(other.name))
			return false;
		return true;
	}
	
}
