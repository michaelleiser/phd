package org.bfh.phd.interfaces;

/**
 * A staff is a member of a department, which has some credentials to authenticate to a department.
 * 
 * @author leism3, koblt1
 */
public interface IStaff {

	/**
	 * @return
	 * 			id for this staff
	 */
	public int getId();

	/**
	 * @param id
	 * 			of the staff
	 */
	public void setId(int id);

	/**
	 * @return
	 * 			name of the staff
	 */
	public String getName();

	/**
	 * @param name
	 * 			of the staff
	 */
	public void setName(String name);
	
	/**
	 * @return
	 * 			salt of the staff's password
	 */
	public String getSalt();

	/**
	 * @param salt
	 * 			of the staff's password
	 */
	public void setSalt(String salt);	
	
	/**
	 * @return
	 * 			password of the staff
	 */
	public String getPassword();

	/**
	 * @param password
	 * 			of the staff
	 */
	public void setPassword(String password);

	/**
	 * @return
	 * 			role of the staff
	 */
	public int getRole();

	/**
	 * @param role
	 * 			of the staff
	 */
	public void setRole(int role);

	/**
	 * @return
	 * 			true if the staff is already activated
	 */
	public boolean getActivated();

	/**
	 * Set activation value for the staff.
	 * @param isActivated	
	 * 			
	 */
	public void setActivated(boolean isActivated);
	
	/**
	 * @return
	 * 			public key part of the staff
	 */
	public String getPublicKey();

	/**
	 * @param publicKey
	 * 			part of the staff
	 */
	public void setPublicKey(String publicKey);

	/**
	 * @return
	 * 			private key part of the staff
	 */
	public String getPrivateKey();

	/**
	 * @param privateKey
	 * 			part of the staff
	 */
	public void setPrivateKey(String privateKey);
	
}