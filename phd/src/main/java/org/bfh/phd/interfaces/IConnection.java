package org.bfh.phd.interfaces;

import java.sql.Connection;

/**
 * A connection to a database.
 * 
 * @author leism3, koblt1
 */
public interface IConnection {
	
	/**
	 * @return
	 * 			a connection to a specific database
	 */
	public Connection getConnection();
	
}
