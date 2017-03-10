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
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class FunLotteryData
{
	/************************************************************************
										函数一：
										抽奖时判断学号/账号是否存
										传入：用户名 密码 验证码
										传出：布尔值
************************************************************************/
	public boolean ifUser(String userID)//String identifyingCode
	{
		int m = 0;
		Configuration cfg = new Configuration().configure(); 
	  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build(); 
	  SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
 	  Session session = sessionFactory.openSession();
 	  Transaction ts = session.beginTransaction();
			//开启事务
		Criteria a = session.createCriteria(UserData.class);
		a.add(Restrictions.eq("UserID",userID));
		m = a.list().size();
		//str=session.getAttribute("rand");		
		session.getTransaction().commit();
		session.close();
		sessionFactory.close(); 
		return (m>0);
	}
		/*************************************************************************
										函数二：
										返回用户中几等奖(也可用于判断用户是否已经中奖而失去中=抽奖资格)
										传入：用户ID
										传出：几等奖  0表示不中
*************************************************************************/
	public Long ifDrawn(String userID)
	{
		int m=0,n=0,p=0;
		List list=null;
		LotteryData l=new LotteryData();
		
		Configuration cfg = new Configuration().configure(); 
	  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build(); 
	  SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
 	  Session session = sessionFactory.openSession();
 	  Transaction ts = session.beginTransaction();
 	  
 	  Criteria a = session.createCriteria(LotteryData.class);
 	  a.add(Restrictions.and(Restrictions.eq("UserID",userID),
 	  												Restrictions.eq("UserPrize",1L)
 	  											));
 	 	Criteria b = session.createCriteria(LotteryData.class);
 	  b.add(Restrictions.and(Restrictions.eq("UserID",userID),
 	  												Restrictions.eq("UserPrize",2L)
 	  											));
 	  Criteria c = session.createCriteria(LotteryData.class);
 	  c.add(Restrictions.and(Restrictions.eq("UserID",userID),
 	  												Restrictions.eq("UserPrize",3L)
 	  											));
 	
 		m=a.list().size();
 		n=b.list().size();
 		p=c.list().size();
 	
 		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
		
	 	if(m>0)
	 		return 1L;
	 	else if(n>0)
	 		return 2L;
	 	else if(p>0)
 			return 3L;
	 	else
	 		return 0L;
	}
	
	//让中奖者传入姓名 电话 地址
	public void getWinner(String userID,Long userPrize,String userName,String userAddress,String userTel)
	{
		Configuration cfg = new Configuration().configure(); 
	  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build(); 
	  SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
 	  Session session = sessionFactory.openSession();
 	  Transaction ts = session.beginTransaction();
 	  
 	  Criteria a = session.createCriteria(LotteryData.class);
		a.add(Restrictions.and(Restrictions.eq("UserID",userID),
													 Restrictions.eq("UserPrize",userPrize)));
													 
		List list = a.list();
		LotteryData l=(LotteryData)list.get(0);
		Long  lotteryID=l.getLotteryID();
		LotteryData p=(LotteryData)session.get(LotteryData.class,lotteryID);
		p.setUserName(userName);
		p.setUserAddress(userAddress);
		p.setUserTel(userTel);
		
		session.save(p);
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
	}
		
	

	/*************************************************************************
									函数五：
										判断用户抽奖次数是否已经达到lotterytimes
										传入：用户ID
										传出：还有true 没有false
*************************************************************************/
	public boolean ifCanDrawn(String userID)
	{
		int n=0;
	  Long lotteryTimes=0L;

		Configuration cfg = new Configuration().configure(); 
	  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build(); 
	  SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
 	  Session session = sessionFactory.openSession();
 	  Transaction ts = session.beginTransaction();
		
		Criteria a = session.createCriteria(LotteryData.class);
		a.add(Restrictions.eq("UserID",userID));
		n=a.list().size();
		
		UserData u = (UserData)session.get(UserData.class,userID);
		lotteryTimes=u.getLotteryNum();		
				
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
		
		
		if(n<lotteryTimes&&n<10)
		return false;
		else
		return true;
	}
}	