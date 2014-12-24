package org.bfh.phd.interfaces;

import org.bfh.phd.Department;
import org.bfh.phd.Staff;

/**
 * A login controller is used for logging in and registering users.
 * 
 * @author leism3, koblt1
 */
public interface ILoginController {

	/**
	 * Log in a user with the specified name and password.
	 * @param name
	 * 			of the user
	 * @param password
	 * 			of the user
	 * @return
	 * 			the return page
	 */
	public String login(String name, String password);

	/**
	 * Register a new staff in a specified department.
	 * @param staff
	 * 			to be registered
	 * @return
	 * 			the newly created staff
	 */
	public String registerNew(Staff staff);

	/**
	 * Register a new staff in a new department.
	 * @param staff
	 * 			to be registered
	 * @param department
	 * 			to be registered within
	 * @param groupKey
	 * 			of the department group
	 * @return
	 * 			the newly created staff
	 */
	public String registerNewWithDepartment(Staff staff, Department department, String groupKey);
	
}
