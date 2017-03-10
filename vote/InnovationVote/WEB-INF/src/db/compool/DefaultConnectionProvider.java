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

import java.sql.Connection;
import java.sql.DriverManager;

import db.compool.PropertiesLoader;


/**
 * 封装数据库连接操作
 */
public class DefaultConnectionProvider 
{
	private Class thisClass =
		db.compool.DefaultConnectionProvider.class;

//	private ConnectionPool connectionPool = null;

	private String driver;
	private String serverURL;
	private String username;
	private String password;

	private Object initLock = new Object();
	
	public DefaultConnectionProvider(){
		loadProperties();
	}
	
	/**
	 * 从连接池中取一个数据库连接并返回
	 * @return 一个数据库连接
	 * @throws SQLException
	 */
	public Connection getConnection() 
	{
		try
		{
			Class.forName(driver);
			Connection conn = null;
			conn = DriverManager.getConnection(serverURL,username, password);
			try
			{
				conn.setAutoCommit(false);
			}
			catch(Exception e1)
			{
			}
			return conn;
		}
		catch(Exception e)
		{
		return null;
		}
	}
	
	/**
	 * 释放数据库连接
	 * @param conn
	 */
	public void closeConnection(Connection conn)
	{
		if (conn != null )
		{
			try
			{
				conn.close();
			}
			catch(Exception e){}
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
	}
	/*
	 * 创建连接池实例
	 */

}
