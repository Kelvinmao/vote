/**
 * StatementManager.java
 * 
 * Copyright (c) 2015,北京邮电大学科技创新大本营
 * All rights reserved.
 * 
 * @author LiuHaiYang
 * @version 1.0
 * Date:2015-3-25
 */
package db.compool;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import db.compool.PropertiesLoader;
import db.compool.SysLog;

/**
 * 封装数据库连接操作
 */
public class StaticConnectionPool 
{
	private static Class thisClass =
		db.compool.StaticConnectionProvider.class;

//	private ConnectionPool connectionPool = null;

	private static Connection[] connPool;
	private static int[] connStatus;

	private static long[] connLockTime, connCreateDate;
	private static String[] connID;
	private static String logFileString;
	private static int currConnections, connLast, minConns, maxConns, maxConnMSec;

	//available: set to false on destroy, checked by getConnection()
	private static boolean available = true;

	private static String driver;
	private static String serverURL;
	private static String username;
	private static String password;
	private static double maxConnTime;
	private static double maxLockTime; 

	private Object initLock = new Object();
	
	static {
		loadProperties();
		connPool = new Connection[maxConns];
		connStatus = new int[maxConns];
		connLockTime = new long[maxConns];
		connCreateDate = new long[maxConns];
		connID = new String[maxConns];
		currConnections = minConns;
		maxConnMSec = (int) (maxConnTime * 86400000.0); //86400 sec/day
		if (maxConnMSec < 30000)
		{ // Recycle no less than 30 seconds.
			maxConnMSec = 30000;
		}


		// Write the pid file (used to clean up dead/broken connection)
		SimpleDateFormat formatter =
			new SimpleDateFormat("yyyy.MM.dd G 'at' hh:mm:ss a zzz");
		java.util.Date nowc = new java.util.Date();
		


		// Initialize the pool of connections with the mininum connections:
		// Problems creating connections may be caused during reboot when the
		//    servlet is started before the database is ready.  Handle this
		//    by waiting and trying again.  The loop allows 50 seconds for
		//    db reboot.
		boolean connectionsSucceeded = false;
		int dbLoop = 5;

		try
		{
			for (int i = 1; i < dbLoop; i++)
			{
				try
				{
					for (int j = 0; j < currConnections; j++)
					{
						createConn(j);
					}
					connectionsSucceeded = true;
					break;
				}
				catch (SQLException e)
				{
				}
			}
			if (!connectionsSucceeded)
			{ // All attempts at connecting to db exhausted
				throw new IOException();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		// Fire up the background housekeeping thread

	} //End ConnectionPool()

	/**
	 * Housekeeping thread.  Runs in the background with low CPU overhead.
	 * Connections are checked for warnings and closure and are periodically
	 * restarted.
	 * This thread is a catchall for corrupted
	 * connections and prevents the buildup of open cursors. (Open cursors
	 * result when the application fails to close a Statement).
	 * This method acts as fault tolerance for bad connection/statement programming.
	 */
	public static void check()
	{
		Statement stmt = null;
		String currCatalog = null;

	
		for (int i = 0; i < maxConns; i++)
		{ // Do for each connection
			long age = System.currentTimeMillis() - connCreateDate[i];
	
			synchronized (connStatus)
			{
				if (connStatus[i] > 0)
				{ // In use, catch it next time!
					try
					{
						if (connPool[i].isClosed()){
							connStatus[i] = 0;
						}
						else
						{
							if (getAge(connPool[i]) > maxLockTime){
								closeConnection(i);
							}
						}
					}
					catch(Exception e1){}
					continue;
				}
				// Take offline (2 indicates housekeeping lock)
			}
	
			try
			{ // Test the connection with createStatement call
				if (age > maxConnMSec)
				{ // Force a reset at the max conn time
					closeConnection(i);
				}
	
				// Some DBs return an object even if DB is shut down
				if (connPool[i] != null && connPool[i].isClosed())
				{
					connPool[i] = null;
				}
				// Connection has a problem, restart it
			}
			catch (SQLException e)
			{
			}
		}
	} // End run

	/**
	 * This method hands out the connections in round-robin order.
	 * This prevents a faulty connection from locking
	 * up an application entirely.  A browser 'refresh' will
	 * get the next connection while the faulty
	 * connection is cleaned up by the housekeeping thread.
	 *
	 * If the min number of threads are ever exhausted, new
	 * threads are added up the the max thread count.
	 * Finally, if all threads are in use, this method waits
	 * 2 seconds and tries again, up to ten times.  After that, it
	 * returns a null.
	 */
	public static Connection getConnection()
	{
		Connection conn = null;
		if (available)
		{
			boolean gotOne = false;
			check();

			for (int outerloop = 1; outerloop <= 10; outerloop++)
			{

				try
				{
					int loop = 0;
					int roundRobin = connLast + 1;
					if (roundRobin >= maxConns)
						roundRobin = 0;

					do
					{
						synchronized (connStatus)
						{
							if ((connStatus[roundRobin] < 1)
								&& (connPool[roundRobin] != null)
								&& (!connPool[roundRobin].isClosed()))
							{
								conn = connPool[roundRobin];
								connStatus[roundRobin] = 1;
								connLockTime[roundRobin] =
									System.currentTimeMillis();
								connLast = roundRobin;
								gotOne = true;
								break;
							}
							else
							{
								loop++;
								roundRobin++;
								if (roundRobin >= maxConns)
									roundRobin = 0;
							}
						}
					}
					while ((gotOne == false) && (loop < maxConns));
				}
				catch (SQLException e1)
				{
				}

				if (gotOne)
				{
					break;
				}
				else
				{
					synchronized (connStatus)
					{ // Add new connections to the pool
						for ( int i = 0; i<maxConns; i++)
						{
							if (connStatus[i] == 0 && connPool[i] == null)
							{
								try
								{
									createConn(i);
								}catch(Exception e)
								{
									continue;
								}
								break;
							}
						}
					}

					try
					{
						Thread.sleep(2000);
					}
					catch (InterruptedException e)
					{
					}
				}
			} // End of try 10 times loop

		}

		return conn;
	}

	/**
	 * Returns the local JDBC ID for a connection.
	 */
	public static int idOfConnection(Connection conn)
	{
		int match;
		String tag;

		try
		{
			tag = String.valueOf(getConnectionHash(conn));
			//tag = conn.toString();
		}
		catch (NullPointerException e1)
		{
			tag = "none";
		}

		match = -1;

		for (int i = 0; i < maxConns; i++)
		{
			if (connID[i].equals(tag))
			{
				match = i;
				break;
			}
		}
		return match;
	}

	/**
	 * Frees a connection.  Replaces connection back into the main pool for
	 * reuse.
	 */
	public static void freeConnection(Connection conn)
	{
		int thisconn = idOfConnection(conn);
		if (thisconn >= 0)
		{
			connStatus[thisconn] = 0;
		}
	}

	/**
	 * Returns the age of a connection -- the time since it was handed out to
	 * an application.
	 */
	public static long getAge(Connection conn)
	{ // Returns the age of the connection in millisec.
		int thisconn = idOfConnection(conn);
		return System.currentTimeMillis() - connLockTime[thisconn];
	}

	/**
	 * Returns the same hashcode for the given object as would be returned
	 * by the default method hashCode(), whether or not the given object's
	 * class overrides hashCode().
	 * The hashcode for the null reference is zero.
	 */
	private static int getConnectionHash(Connection con)
	{
		// Use of System.identifyHashCode() gets the base (java.lang.Object)
		// version of the hashCode. If the Connection implementations has
		// provided it's own hashCode method, it will not be used
		return System.identityHashCode(con);
	}

	private static void createConn(int i) throws SQLException
	{
		Date now = new Date();
		try
		{
			Class.forName(driver);
			connPool[i] =
				DriverManager.getConnection(serverURL, username, password);
			try{
				connPool[i].setAutoCommit(false);
			}catch(Exception se){
			}
			connStatus[i] = 0;
			connID[i] = String.valueOf(getConnectionHash(connPool[i]));
			connLockTime[i] = 0;
			connCreateDate[i] = now.getTime();

		}
		catch (ClassNotFoundException e2)
		{
			e2.printStackTrace();
			throw new SQLException(e2.getMessage());
		}
	}

	/*
	 * 获取数据库连接参数
	 */
	private static void loadProperties()
	{
		PropertiesLoader prop = new PropertiesLoader();
		driver = prop.getProperty("driver");
		serverURL = prop.getProperty("serverURL");
		username = prop.getProperty("username");
		password = prop.getProperty("password");
		logFileString = prop.getProperty("llHome") + File.separator + "logs" + File.separator + "ConnectionPool.log";
		String minCons = prop.getProperty("minConnections");
		String maxCons = prop.getProperty("maxConnections");
		String conTime = prop.getProperty("maxConnectionLifeTimes");
		String conLockTime = prop.getProperty("maxConnectionLockTimes");
		try
		{
			if (minCons != null)
			{
				minConns = Integer.parseInt(minCons);
			}
			if (maxCons != null)
			{
				maxConns = Integer.parseInt(maxCons);
			}
			if (conTime != null)
			{
				maxConnTime = Double.parseDouble(conTime);
			}
			if (conLockTime != null)
			{
				maxLockTime = Double.parseDouble(conLockTime);
			}
		}
		catch (Exception e)
		{
			SysLog.getLogger(thisClass).error(
				"could not parse default pool properties. "
					+ "Make sure the values exist and are correct.");
		}
		

	}


	private static void closeConnection(int i){
		synchronized (connStatus){
			try
			{
				connPool[i].close();
				connStatus[i] = 0;
				connPool[i] = null;
				currConnections--;
			}
			catch(Exception e)
			{
			}
		}
	}

}
