/**
 * StatementManager.java
 * 
 * Copyright (c) 2007,�����ʵ��ѧ�Ƽ����´�Ӫ
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
 * ��classpath�ĸ�·������ȡ�����ļ�(ll_init.properties)
 * �������ļ���Ҫ�����������ļ��Ĵ��Ŀ¼�����ݿ������
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
	 * ��ȡ�����ļ������Ŀ¼
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
				System.err.println("��ȡ��ʼ�������ļ�ʧ�ܣ�");
				e.printStackTrace();
			}
			return cmsHome;
		}
	}
	
	/**
	 * @param key ���ݿ����ü���
	 * @return ���ݿ�����ֵ
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
				System.err.println("��ȡ�����ļ�ʧ�ܣ�");
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
