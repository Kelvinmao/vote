/**
 * StatementManager.java
 * 
 * Copyright (c) 2007,北京邮电大学科技创新大本营
 * All rights reserved.
 * 
 * @author LiuHaiYang
 * @version 1.0
 * Date:2015-3-25
 */
package db.compool;

import java.io.InputStream;
import java.util.Hashtable;
import java.util.Properties;

/**
 * 从classpath的根路径中提取配置文件(ll_init.properties)
 * 此配置文件主要保存了配置文件的存放目录及数据库的配置
 */
public class PropertiesLoader
{
	public static String cmsHome = null;
	private Hashtable db = new Hashtable(8);
	private Properties initProps = null;
	
	
	public void  init() throws Exception
	{
		if (initProps == null)
		{
			InputStream in = null;
			initProps = new Properties();
			try
			{
				in = getClass().getResourceAsStream("ll_init.properties");
				initProps.load(in);
			}
			catch(Exception e)
			{
				initProps = null;
				throw e;
			}
			finally
			{
				try{in.close();} catch(Exception e){}
			}
		}
	}
    
	/**
	 * 提取配置文件保存的目录
	 */
	public String getLlHome()
	{
		if(cmsHome != null)
		{
			return cmsHome;
		}
		else
		{
			try
			{
				init();
				cmsHome = initProps.getProperty("llHome");
				if (cmsHome != null)
				{
					if(cmsHome.endsWith("/"))
					{
						cmsHome = cmsHome.substring(0, cmsHome.length() - 1);
					}
					else if(cmsHome.endsWith("\\"))
					{
						cmsHome = cmsHome.substring(0, cmsHome.length() - 2);
					}
				}
			}
			catch (Exception e)
			{
				System.err.println("提取初始化配置文件失败！");
				e.printStackTrace();
			}
			return cmsHome;
		}
	}
	
	/**
	 * @param key 数据库配置键名
	 * @return 数据库配置值
	 */
	public String getProperty(String key)
	{
		if (key == null || key.equals(""))
		{
			return null;
		}
		String value = null;
		try 
		{
			value = (String)db.get(key);
		}
		catch(Exception e)
		{
			return null;
		}
		if(value != null)
		{
			return value;
		}
		else
		{
			try
			{
				init();
				value = initProps.getProperty(key);
				try
				{
					db.put(key,value);
				}
				catch (Exception ne)
				{
					
				}
			}
			catch (Exception e)
			{
				System.err.println("提取配置文件失败！");
				e.printStackTrace();
			}
			return value;
		}
	}
	
	public static void main(String [] args)
	{
		System.out.println(new PropertiesLoader().getProperty("driver"));
	}

}
