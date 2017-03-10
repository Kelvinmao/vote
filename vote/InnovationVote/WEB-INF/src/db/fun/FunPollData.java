package db.fun;

import db.pojo.PollData;
import db.pojo.UserData;
import db.pojo.ProjectData;

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

/***************用于对poll_data表的所有操作****************/


public class FunPollData
{ 
	
  
	/**********************************************************
                函数二：
                判断用户投票是否重复
                传入：projectID(项目ID) 和 userID（用户ID）
                传出：true(重复)        或 false(不重复)
  ***********************************************************/
	public boolean ifRepeat(String projectID, String userID)
	{
	  int i = 0;
	
	  Configuration cfg = new Configuration().configure(); 
	  //ServiceRegistry Sr = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
	  //SessionFactory sf = cfg.configure().buildSessionFactory(); 
	  SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
 	  Session session = sessionFactory.openSession();
 	  Transaction ts = session.beginTransaction();
			//开启事务
		
			Criteria c = session.createCriteria(PollData.class);
			c.add(Restrictions.and(Restrictions.eq("ProjectID",projectID),
														 Restrictions.eq("UserID",userID)
														 ));

			i = c.list().size();
		 
		
		//进行操作提交
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();

		
		//后续返回操作
		return i>0?true:false;
	}    
	
	/**********************************************************
                函数三：
                用户删票
	              传入：projectID(项目ID) 和 userID(用户ID)
	              返回：void 
  ***********************************************************/  
  public void PollDelete(String projectID, String userID)
	{
	
	  Configuration cfg = new Configuration().configure(); 
	  //ServiceRegistry Sr = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
	  //SessionFactory sf = cfg.configure().buildSessionFactory(); 
	  SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
 	  Session session = sessionFactory.openSession();
 	  Transaction ts = session.beginTransaction();
			//开启事务
		
			Criteria c = session.createCriteria(PollData.class);
			c.add(Restrictions.and(Restrictions.eq("ProjectID",projectID),
														 Restrictions.eq("UserID",userID)
														 ));
			List list = c.list();
			PollData v = (PollData)list.get(0);
			Long l = v.getPollID();
			PollData u = (PollData)session.get(PollData.class,l);
			session.delete(u);
		 
		
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();

	}        
	public void pollDelete(String projectID, String userID)
	{
	  PollDelete(projectID,userID);
	  new FunUserData().minusUserPollNumber(userID);
	  new FunProjectData().minusProjectVoteNumber(projectID);
	}                      
}