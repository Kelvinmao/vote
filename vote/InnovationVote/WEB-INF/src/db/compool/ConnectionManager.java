/*
 * Created on 2015-3-25
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
public class ConnectionManager {
	private static ConnectionProvider connProvider = null;
//	private static DefaultConnectionProvider connProvider = null;
//	private static StaticConnectionProvider connProvider = null;
	private static Object initLock = new Object();
	
	public static Connection getConnection(){
		init();
		if (connProvider == null ){
			return null;
		}else{
			return connProvider.getConnection() ;
		}
	}
	
	public static void closeConnection(Connection conn ){
		if (conn == null){
			return;
		}
		if (connProvider == null){
			try {
				conn.close();
			}catch(Exception e){
			}
		}else{
			connProvider.closeConnection( conn);
		}
	}
	
	private static void init(){
		if (connProvider == null){
			synchronized(initLock){
				connProvider = new ConnectionProvider();
//				connProvider = new DefaultConnectionProvider();
//				connProvider = new StaticConnectionProvider();
		}
		}
	}

}
