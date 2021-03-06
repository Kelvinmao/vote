package db.fun;

import db.pojo.PollData;
import db.pojo.UserData;
import db.pojo.ProjectData;
import db.pojo.LotteryData;

import java.util.*;
import org.hibernate.Query;
import org.hibernate.Session; 
import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory; 
import org.hibernate.cfg.Configuration; 
import org.hibernate.service.ServiceRegistry; 
import org.hibernate.criterion.Restrictions;
//import org.hibernate.service.ServiceRegistryBuilder; 
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/***************用于对user_data表的所有操作****************/

public class FunUserData
{
	/**********************************************************
                函数一：
                用户选票删除
	              用户票数-1
	              传入：用户名
	              传出：用户已投票数
  ***********************************************************/
  public Long minusUserPollNumber(String userID)
	{
	  Long num = 0L;
		Long lotteryTimes=0L;
	
	  Configuration cfg = new Configuration().configure(); 
	  //ServiceRegistry Sr = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
	  //SessionFactory sf = cfg.configure().buildSessionFactory(); 
	  SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
 	  Session session = sessionFactory.openSession();
 	  Transaction ts = session.beginTransaction();
		//开启事务
		
		
	  UserData u = (UserData)session.get(UserData.class,userID);
	  num = u.getPollNumber();
	  num--;
	  u.setPollNumber(num);
	  lotteryTimes = u.getLotteryNum();
	  lotteryTimes--;
	  u.setLotteryNum(lotteryTimes);
	  session.save(u);
		 
		
		//进行操作提交
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();

		
		//后续返回操作
		return num;
	}

  /**********************************************************
	            函数二：
	            用户选票增加
	            用户票数+1
	            传入：用户名
	            传出：用户已投票数
	**********************************************************/
	public Long addUserPollNumber(String userID)
	{
	  Long num = 0L;
		Long lotteryTimes=0L;

	  Configuration cfg = new Configuration().configure(); 
	  //ServiceRegistry Sr = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
	  //SessionFactory sf = cfg.configure().buildSessionFactory(); 
	  SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
 	  Session session = sessionFactory.openSession();
 	  Transaction ts = session.beginTransaction();
		//开启事务
		
		
	  UserData u = (UserData)session.get(UserData.class,userID);
	  num = u.getPollNumber();
	  num++;
	  u.setPollNumber(num);

	  lotteryTimes=u.getLotteryNum();
	  lotteryTimes++;
	  u.setLotteryNum(lotteryTimes);
	  
	  session.save(u);
		 
		
		//进行操作提交
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();

		
		//后续返回操作
		return num;
	}
	
	/**********************************************************
                  函数三：
                  判断是否为校内
	                获得IsBUPT
	                传入：用户名
	                传出：用户IsBUPT
  **********************************************************/
  public Long getUserIsBUPT(String userID)
	{
	  Long i = 0L;
	
	  Configuration cfg = new Configuration().configure(); 
	  //ServiceRegistry Sr = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
	  //SessionFactory sf = cfg.configure().buildSessionFactory(); 
	  SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
 	  Session session = sessionFactory.openSession();
 	  Transaction ts = session.beginTransaction();
		//开启事务
		
		
	  UserData u = (UserData)session.get(UserData.class,userID);
	  i = u.getIsBUPT();
		 
		
		//进行操作提交
		session.close();
		sessionFactory.close();

		
		//后续返回操作
		return i;
	}
	
	/**********************************************************
	             函数五：
	             获取用户投票数
	             传入：用户名
	             传出：票数
  **********************************************************/
  public Long getPollNumber(String userID)
	{
	  Long i = 0L;
	
	  Configuration cfg = new Configuration().configure(); 
	  //ServiceRegistry Sr = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
	  //SessionFactory sf = cfg.configure().buildSessionFactory(); 
	  SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
 	  Session session = sessionFactory.openSession();
 	  Transaction ts = session.beginTransaction();
		//开启事务
		
		
	  UserData u = (UserData)session.get(UserData.class,userID);
	  i = u.getPollNumber();
		 
		
		//进行操作提交
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();

		
		//后续返回操作
		return i;
	}

  /**********************************************************
                     函数七：
                     存入登录时间
	                   传入：用户名，登录时间
	                   传出：void
	  **********************************************************/
	public void setUserDatetime(String userID,Date datetime)
	{
	
	  Configuration cfg = new Configuration().configure(); 
	  //ServiceRegistry Sr = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
	  //SessionFactory sf = cfg.configure().buildSessionFactory(); 
	  SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
 	  Session session = sessionFactory.openSession();
 	  Transaction ts = session.beginTransaction();
		//开启事务
		
		
	  UserData u = (UserData)session.get(UserData.class,userID);
	  u.setUserDatetime(datetime);
		 
		
		//进行操作提交
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();

		
		//后续返回操作
	}
	
	/**********************************************************
                     函数八：
                     读取登录时间
	                   传入：用户名
	                   传出：登录时间
	 **********************************************************/
	public Date getUserDatetime(String userID)
	{
	
	  Configuration cfg = new Configuration().configure(); 
	  //ServiceRegistry Sr = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
	  //SessionFactory sf = cfg.configure().buildSessionFactory(); 
	  SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
 	  Session session = sessionFactory.openSession();
 	  Transaction ts = session.beginTransaction();
		//开启事务
		
		
	  UserData u = (UserData)session.get(UserData.class,userID);
	  Date datetime = u.getUserDatetime();
		 
		
		//进行操作提交
		session.close();
		sessionFactory.close();

		
		//后续返回操作
		return datetime;
	}
	/**********************************************************
                     函数九：
                     进行投票操作 写入PollData
	                   传入：用户名，项目编号，Ip,是否BUPT
	                   传出：void
	 **********************************************************/
	 public void setPoll(String userID,String projectID,String IP)//,Long isBUPT
	{
	
	  Configuration cfg = new Configuration().configure(); 
	  //ServiceRegistry Sr = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
	  //SessionFactory sf = cfg.configure().buildSessionFactory(); 
	  SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
 	  Session session = sessionFactory.openSession();
 	  Transaction ts = session.beginTransaction();
		//开启事务
		
		PollData v = new PollData();
		v.setUserID(userID);
		v.setProjectID(projectID);
		v.setPollIP(IP);
	//	v.setIsBUPT(isBUPT);
	  session.save(v);

		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
	}
	/**********************************************************
                     函数十：
                     进行投票操作 写入ProjectData UserData
	                   传入：项目编号，用户名,ip
	                   传出：void
	 **********************************************************/
	public void vote(String projectID,String userID,String IP)
	{
		addUserPollNumber(userID);
		new FunProjectData().addProjectPollNumber(projectID);
		//Long i = getUserIsBUPT(userID);
		new FunUserData().setPoll(userID,projectID,IP);//,i
	}
	
	//用户取消对某项目的投票
	public void cancelVote(String projectID,String userID)
	{ 
 	  minusUserPollNumber(userID);
 	  new FunProjectData().minusProjectVoteNumber(projectID);
 	  new FunPollData().PollDelete(projectID,userID);
 	}
		
	
	/**********************************************************
                     函数十一：
                     获取用户投票列表
	                   传入：用户名
	                   传出：List
	 **********************************************************/
	 public List userPoll(String userID)
	{
	
	  Configuration cfg = new Configuration().configure(); 
	  //ServiceRegistry Sr = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
	  //SessionFactory sf = cfg.configure().buildSessionFactory(); 
	  SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
 	  Session session = sessionFactory.openSession();
 	  Transaction ts = session.beginTransaction();
		//开启事务
		
		 String hql = "from VoteRecord u where u.UserID = ? ";
		 List i = session.createQuery(hql).setParameter(0,userID).list();
	  
		 
		
		//进行操作提交
		session.close();
		sessionFactory.close();

		
		//后续返回操作
		return i;
	}
	
	/**********************不懂不懂不懂不懂不懂不懂不懂不懂不懂不懂不懂不懂不懂不懂不懂不懂不懂不懂************/
	/*
	对SNR进行转换
	*/
	public String getSNR(String cardSource)
	{
			if(cardSource.length() != 10)
			{
				return "Err";
			}
			Long i = Long.parseLong(cardSource);
			String s = Long.toHexString(i);
			if(s.length()==7)
			{
				s="0"+s;
				}
			char c[] = new char[8];
			char cc[] = new char[8];
			System.out.println(s+"---------------------------");
			for(int j = 0; j < 8 ; j++ )
  		{
  			
  			c[j] = s.charAt(j);
  		}
  		cc[0] = c[6];
  		cc[1] = c[7];
  		cc[2] = c[4];
  		cc[3] = c[5];
  		cc[4] = c[2];
  		cc[5] = c[3];
  		cc[6] = c[0];
  		cc[7] = c[1];
  		String srt = new String(cc);
  		System.out.println(srt+"---------------------------");
			return srt;
	}
	/**********************不懂不懂不懂不懂不懂不懂不懂不懂不懂不懂不懂不懂不懂不懂不懂不懂不懂不懂************/
	
	//
	
	//抽奖
	  
	public boolean doLottery(String userID)
	{
		int num=0;
		int lotteryTimes;
		boolean b;
		Configuration cfg = new Configuration().configure(); 
	  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
	  SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
 	  Session session = sessionFactory.openSession();
 	  Transaction ts = session.beginTransaction();
 	  
	  String hql = "from LotteryData";
		Query q = session.createQuery(hql);
		num = q.list().size();
		num++;
		LotteryData p = new LotteryData();
		
		int N=0;
		if(num==(1002+N))
		{
			p.setUserPrize(1L);
			b=true;
		}
		else if(num==(5+N)||num==(605+N))
		{
			p.setUserPrize(2L);
			b=true;
		}
		else if(num==(1204+N)|num==(1971+N))
		{
			p.setUserPrize(2L);
			b=true;
		}
		else if((num==102+N)||num==(356+N))
		{
			p.setUserPrize(3L);
			b=true;
		}
		else if(num==(795+N)||num==(1406+N))
		{
			p.setUserPrize(3L);
			b=true;
		}
		else if(num==(1750+N)||num==(2130+N))
		{
			p.setUserPrize(3L);
			b=true;
		}
		else if(num==(2321+N)||num==(2500+N))
		{
			p.setUserPrize(3L);
			b=true;
		}
		else
		{
			p.setUserPrize(0L);	
			b=false;
		}
		
		/*String str=String.valueOf(num);
		Long l=Long.parseLong(String.valueOf(str));
		p.setLotteryID(l);*/
		// p.setLotteryIP(lotteryIP);
		p.setUserID(userID);
	  // p.setLotteryTime(lotteryTime);
	  
	  session.save(p);
	  session.getTransaction().commit();
		session.close();
		sessionFactory.close();
		return b;
	}
	  
	  
	  /**********************************************************
                     函数十四：
                     验证密码
	                   传入：用户名 密码
	                   传出：密码不对 传出false 正确传出true 
	  **********************************************************/
	public boolean checkUser(String userID,String userPassword)
	{
		String password;
		boolean b;
	  Configuration cfg = new Configuration().configure(); 
	  //configure()方法默认会在classpath下面寻找hibernate.cfg.xml文件，如果没有找到该文件，系统会打印如下信息并抛出HibernateException异常。
	  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
	  SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
 	  Session session = sessionFactory.openSession();
 	  Transaction ts =session.beginTransaction();
	 	
	 	Criteria a = session.createCriteria(UserData.class);
		a.add(Restrictions.eq("UserID",userID));
		if(a.list().size()==0)
		{
			session.getTransaction().commit();
 	  	session.close();
			sessionFactory.close();
			return false;
		}
		else
		{
			UserData u=(UserData)session.get(UserData.class,userID);
			if(userPassword.equals(u.getUserPassword()))
			b=true;
			else
			b=false;
			session.getTransaction().commit();
 	  	session.close();
			sessionFactory.close();
			return b;
		}
 	}
 	  	//修改密码
 	public void modifyPassword(String userID,String userPassword)
	{
	  Configuration cfg = new Configuration().configure(); 
	  //configure()方法默认会在classpath下面寻找hibernate.cfg.xml文件，如果没有找到该文件，系统会打印如下信息并抛出HibernateException异常。
	  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
	  SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
 	  Session session = sessionFactory.openSession();
 	  Transaction ts =session.beginTransaction();
 	  
	 	UserData u=(UserData)session.get(UserData.class,userID);
 	 	u.setUserPassword(userPassword);
 	 	
 	 	session.save(u);
 	  session.getTransaction().commit();
 	  session.close();
		sessionFactory.close();
 	}  	
 	
 	//验证projectID UerID
 	public boolean checkDoubleID(String projectID,String userID)
 	{
 		Configuration cfg = new Configuration().configure(); 
	  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
	  SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
 	  Session session = sessionFactory.openSession();
 	  Transaction ts =session.beginTransaction();
 	  
 	  Criteria a = session.createCriteria(UserData.class);
		a.add(Restrictions.eq("UserID",userID));
		
		Criteria b = session.createCriteria(ProjectData.class);
		b.add(Restrictions.eq("ProjectID",projectID));
		
		if(a.list().size()==0||b.list().size()==0)
		{
			session.getTransaction().commit();
 	  	session.close();
			sessionFactory.close();
			return false;
		}
		else
		{
			session.getTransaction().commit();
 		  session.close();
			sessionFactory.close();
			return true;
		}
	}
	
	//返回用户
	public UserData showUserData(String userID)
	{
		UserData u=null;
		Configuration cfg = new Configuration().configure(); 
	  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
	  SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
 	  Session session = sessionFactory.openSession();
 	  Transaction ts =session.beginTransaction();
 	  
 	  u = (UserData)session.get(UserData.class,userID);
 	  
 	  session.getTransaction().commit();
 		session.close();
		sessionFactory.close();
		return u;
	}
	/*
	获取outID
	*/
	public String getOutID(String cardSource)
	{
		String outID;
	  Configuration cfg = new Configuration().configure(); 
	  //ServiceRegistry Sr = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
	  //SessionFactory sf = cfg.configure().buildSessionFactory(); 
	  SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
 	  Session session = sessionFactory.openSession();
 	  Transaction ts = session.beginTransaction();
		//开启事务
		
		String SNR = getSNR(cardSource);
		if(SNR == "Err")
		{
		outID = "0000000000";
		session.close();
		sessionFactory.close();
		return outID;
		}
		String hql = "from UserData u where lower(u.CardID) = ? ";
		List	i = session.createQuery(hql).setParameter(0,SNR).list();
		if(i.size() == 0 )
		{
		outID = "0000000000";
		session.close();
		sessionFactory.close();
		return outID;
		}
		UserData d = (UserData)i.get(0);
		outID = d.getUserID();
		
		//进行操作提交
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();

		
		//后续返回操作
		return outID;
	}
}