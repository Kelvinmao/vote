/*
 * Created on 2007-4-25
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package db.compool;

import java.sql.Connection;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class StaticConnectionProvider {
	public Connection getConnection()
	{
		return StaticConnectionPool.getConnection();
	}
	public void closeConnection(Connection conn)
	{
		StaticConnectionPool.freeConnection(conn);
	}
	

}
