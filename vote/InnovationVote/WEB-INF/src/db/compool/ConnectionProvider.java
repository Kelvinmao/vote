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
import java.sql.Connection;

import db.compool.PropertiesLoader;
import db.compool.SysLog;


/**
 * 封装数据库连接操作
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
	 * 从连接池中取一个数据库连接并返回
	 * @return 一个数据库连接
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
	 * 释放数据库连接
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
	 * 获取数据库连接参数
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
	 * 创建连接池实例
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
