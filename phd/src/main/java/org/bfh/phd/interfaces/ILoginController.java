package org.bfh.phd.interfaces;

/**
 * @author leism3, koblt1
 *
 */
public interface ILoginController {

	/**
	 * logs in a user with the specified name and password
	 * 
	 * @param name
	 * @param password
	 * @return
	 */
	public String login(String name, String password);

	/**
	 * logs out the currently logged in user
	 * 
	 * @return
	 */
	public String logout();

}
