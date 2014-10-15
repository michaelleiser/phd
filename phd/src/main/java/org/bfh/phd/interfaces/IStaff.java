package org.bfh.phd.interfaces;

/**
 * @author leism3, koblt1
 *
 */
public interface IStaff {

	/**
	 * @return
	 */
	public int getId();

	/**
	 * @param id
	 */
	public void setId(int id);

	/**
	 * @return
	 */
	public String getName();

	/**
	 * @param name
	 */
	public void setName(String name);

	/**
	 * @return
	 */
	public String getPassword();

	/**
	 * @param password
	 */
	public void setPassword(String password);

	/**
	 * @return
	 */
	public int getRole();

	/**
	 * @param role
	 */
	public void setRole(int role);


	
	/**
	 * @return
	 */
	public String getPublicKey();

	/**
	 * @param publicKey
	 */
	public void setPublicKey(String publicKey);

	/**
	 * @return
	 */
	public String getPrivateKey();

	/**
	 * @param privateKey
	 */
	public void setPrivateKey(String privateKey);	
	
	/**
	 * @return
	 */
	public String login();

	/**
	 * @return
	 */
	public String registernew();

}
