package org.bfh.phd.interfaces;

import java.sql.Connection;

/**
 * @author leism3, koblt1
 *
 */
public interface IConnection {
	
	/**
	 * @return
	 */
	public Connection getConnection();
	
}
