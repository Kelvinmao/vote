/**
 * StatementManager.java
 * 
 * Copyright (c) 2015,�����ʵ��ѧ�Ƽ����´�Ӫ
 * All rights reserved.
 * 
 * @author LiuHaiYang
 * @version 1.0
 * Date:2015-3-25
 */
package db.compool;

import java.io.File;
import java.sql.Connection;

import db.compool.PropertiesLoader;
import db.compool.SysLog;


/**
 * ��װ���ݿ����Ӳ���
 */
public class ConnectionProvider 
{
	private Class thisClass =
		db.compool.ConnectionProvider.class;

	private ConnectionPool connectionPool = null;

	private String driver;
	private String serverURL;
	private String username;
	private String password;
	private int minConnections = 3;
	private int maxConnections = 10;
	private double connectionTime = 0.5;
	private String logPath = null;

	private Object initLock = new Object();

	/**
	 * �����ӳ���ȡһ�����ݿ����Ӳ�����
	 * @return һ�����ݿ�����
	 * @throws SQLException
	 */
	public Connection getConnection() 
	{
		if (connectionPool == null)
		{
			createConnectionPool();
		}
		return connectionPool.getConnection();
	}
	
	/**
	 * �ͷ����ݿ�����
	 * @param conn
	 */
	public void closeConnection(Connection conn)
	{
		if (conn == null || connectionPool == null)
		{
			try
			{
				conn.close();
			}
			catch(Exception e){}
		}
		else
		{
			connectionPool.freeConnection( conn );
		}
		
	}

	/*
	 * ��ȡ���ݿ����Ӳ���
	 */
	private void loadProperties()
	{
		PropertiesLoader prop = new PropertiesLoader();
		driver = prop.getProperty("driver");
		serverURL = prop.getProperty("serverURL");
		username = prop.getProperty("username");
		password = prop.getProperty("password");
		logPath = prop.getLlHome() + File.separator + "logs" + File.separator + "ConnectionPool.log";
		String minCons = prop.getProperty("minConnections");
		String maxCons = prop.getProperty("maxConnections");
		String conTime = prop.getProperty("maxConnectionLifeTimes");
		try
		{
			if (minCons != null)
			{
				minConnections = Integer.parseInt(minCons);
			}
			if (maxCons != null)
			{
				maxConnections = Integer.parseInt(maxCons);
			}
			if (conTime != null)
			{
				connectionTime = Double.parseDouble(conTime);
			}
		}
		catch (Exception e)
		{
			SysLog.getLogger(thisClass).error(
				"could not parse default pool properties. "
					+ "Make sure the values exist and are correct.");
		}
	}
	/*
	 * �������ӳ�ʵ��
	 */
	private void createConnectionPool()
	{
		synchronized (initLock)
		{
			if (connectionPool == null)
			{
				try 
				{
					loadProperties();
					connectionPool = new ConnectionPool(
							driver, serverURL, username, password, minConnections, 
							maxConnections, logPath, connectionTime);
				}
				catch(Exception e)
				{
				}
			}
		}
	}

}
