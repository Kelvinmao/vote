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

import java.sql.Connection;
import java.sql.DriverManager;

import db.compool.PropertiesLoader;


/**
 * ��װ���ݿ����Ӳ���
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
	 * �����ӳ���ȡһ�����ݿ����Ӳ�����
	 * @return һ�����ݿ�����
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
	 * �ͷ����ݿ�����
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
	 * ��ȡ���ݿ����Ӳ���
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
	 * �������ӳ�ʵ��
	 */

}
