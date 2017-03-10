package db.fun;

import db.pojo.VoteRecord;
import db.pojo.UserData;
import db.pojo.ProjectInfo;

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

/*
用于对VoteRecord表的所有操作
*/

public class FunVoteRecord
{
	/*
	判断投票是否重复
	传入：项目编号、学号
	返回：true(重复) false(不重复)
	*/
	public boolean ifRepeat(Long projectID,String outID)
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
		
			Criteria c = session.createCriteria(VoteRecord.class);
			c.add(Restrictions.and(Restrictions.eq("ProjectID",projectID),
														 Restrictions.eq("OutID",outID)
														 ));

			i = c.list().size();
		 
		
		//进行操作提交
		session.close();
		sessionFactory.close();

		
		//后续返回操作
		return i>0?true:false;
	}
	
	/*
	删票
	传入：项目编号、学号
	返回：void
	*/
	public void VoteDelete(Long projectID,String outID)
	{
	
	  Configuration cfg = new Configuration().configure(); 
	  //ServiceRegistry Sr = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
	  //SessionFactory sf = cfg.configure().buildSessionFactory(); 
	  SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
 	  Session session = sessionFactory.openSession();
 	  Transaction ts = session.beginTransaction();
			//开启事务
		
			Criteria c = session.createCriteria(VoteRecord.class);
			c.add(Restrictions.and(Restrictions.eq("ProjectID",projectID),
														 Restrictions.eq("OutID",outID)
														 ));
			List list = c.list();
			VoteRecord v = (VoteRecord)list.get(0);
			Long l = v.getVoteID();
			VoteRecord u = (VoteRecord)session.get(VoteRecord.class,l);
			session.delete(u);
		 
		
		//进行操作提交
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();

		
		//后续返回操作
	}
	
	/*
	删票
	传入：项目编号、学号
	返回：void
	*/
	public void voteDelete(Long projectID,String outID)
	{
	  VoteDelete(projectID,outID);
	  new FunUserData().minusUserVoteNum(outID);
	  new FunProjectInfo().minusProjectVoteNum(projectID);
	}
}