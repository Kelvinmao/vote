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
用于对ProjectInfo表的所有操作
*/

public class FunProjectInfo
{
	/*
	用于投票删除
	项目票数-1
	传入：项目编号
	传出：项目票数
	*/
	public int minusProjectVoteNum(Long projectID)
	{
	  int num = 0;
	
	  Configuration cfg = new Configuration().configure(); 
	  //ServiceRegistry Sr = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
	  //SessionFactory sf = cfg.configure().buildSessionFactory(); 
	  SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
 	  Session session = sessionFactory.openSession();
 	  Transaction ts = session.beginTransaction();
			//开启事务
		
		
		ProjectInfo p = (ProjectInfo)session.get(ProjectInfo.class,projectID);
		num = p.getVoteNum();
		num--;
		p.setVoteNum(num);
		session.save(p);
		 
		
		//进行操作提交
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();

		
		//后续返回操作
		return num;
	}
	
	/*
	用于投票增加
	项目票数+1
	传入：项目编号
	传出：项目票数
	*/
	public int addProjectVoteNum(Long projectID)
	{
	  int num = 0;
	
	  Configuration cfg = new Configuration().configure(); 
	  //ServiceRegistry Sr = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
	  //SessionFactory sf = cfg.configure().buildSessionFactory(); 
	  SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
 	  Session session = sessionFactory.openSession();
 	  Transaction ts = session.beginTransaction();
		//开启事务
		
		
	  ProjectInfo p = (ProjectInfo)session.get(ProjectInfo.class,projectID);
	  num = p.getVoteNum();
	  num++;
	  p.setVoteNum(num);
	  session.save(p);
		 
		
		//进行操作提交
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();

		
		//后续返回操作
		return num;
	}
	
	/*
  显示项目得票情况、排序、分页显示
	传入参数：用于分页的首行数、每页的行数
	返回参数：List
	*/
	public List showProjectVote(int firstPage,int maxPage)
	{
	  List list = null;
	
	  Configuration cfg = new Configuration().configure(); 
	  //ServiceRegistry Sr = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
	  //SessionFactory sf = cfg.configure().buildSessionFactory(); 
	  SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
 	  Session session = sessionFactory.openSession();
 	  Transaction ts = null;
 		try
 		{
			ts = session.beginTransaction();
			//开启事务
		
			String hql = "from ProjectInfo as p order by p.VoteNum desc";
			Query q = session.createQuery(hql);
			q.setFirstResult(firstPage);
			q.setMaxResults(maxPage);
			list = q.list();
		
			//进行操作提交
			session.getTransaction().commit();
		}
		catch(Exception ex)
		{
			if(ts != null)ts.rollback();
			System.out.println("showProjectVote方法执行错误");
			ex.printStackTrace();
		}
		finally
		{
			session.close();
			sessionFactory.close();
		}
			
		
		//后续返回操作
		return list;
	}
	
	/*
  项目单页显示
	传入参数：项目编号
	返回参数：ProjectInfo对象
	*/
	public ProjectInfo showProjectOnly(Long projectID)
	{
	  ProjectInfo p = null;
	
	  Configuration cfg = new Configuration().configure(); 
	  //ServiceRegistry Sr = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
	  //SessionFactory sf = cfg.configure().buildSessionFactory(); 
	  SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
 	  Session session = sessionFactory.openSession();
 	  Transaction ts = null;
 		try
 		{
			ts = session.beginTransaction();
			//开启事务
		
			p = (ProjectInfo)session.get(ProjectInfo.class,projectID);
		
			//进行操作提交
			session.getTransaction().commit();
		}
		catch(Exception ex)
		{
			if(ts != null)ts.rollback();
			System.out.println("showProjectOnly方法执行错误");
			ex.printStackTrace();
		}
		finally
		{
			session.close();
			sessionFactory.close();
		}

		//后续返回操作
		return p;
	}
	
	/*
	over
  模糊查询并进行分页显示
	传入参数：用于分页的首行数、每页的行数、查询的ID、查询的名字
	返回参数：List
	*/
	public List ProjectInfoSearch(int firstPage,int maxPage,String search)
	{
	  List list = null;
	
	  Configuration cfg = new Configuration().configure(); 
	  //ServiceRegistry Sr = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
	  //SessionFactory sf = cfg.configure().buildSessionFactory(); 
	  SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
 	  Session session = sessionFactory.openSession();
 	  Transaction ts = null;
 		try
 		{
			ts = session.beginTransaction();
			//开启事务
			Criteria c = session.createCriteria(ProjectInfo.class);
			boolean result = search.matches("[0-9]+");
			long l;
			Long ll;
			if(result)
			{
				l =  Long.parseLong(search);
				ll = new Long(l);
				c.add(Restrictions.eq("ProjectID", ll));
			}
			else
			{
				c.add(Restrictions.like("ProjectName", "%"+search+"%"));
			}
			c.setFirstResult(firstPage);
			c.setMaxResults(maxPage);
			list = c.list();
		
			//进行操作提交
			session.getTransaction().commit();
		}
		catch(Exception ex)
		{
			if(ts != null)ts.rollback();
			System.out.println("showProjectVote方法执行错误");
			ex.printStackTrace();
		}
		finally
		{
			session.close();
			sessionFactory.close();
		}			
		//后续返回操作
		return list;
	}
	
	/*
	over
  模糊查询
	传入参数：查询的ID、查询的名字
	返回参数：List
	*/
	public List ProjectInfoSearch(String search)
	{
	  List list = null;
	
	  Configuration cfg = new Configuration().configure(); 
	  //ServiceRegistry Sr = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
	  //SessionFactory sf = cfg.configure().buildSessionFactory(); 
	  SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
 	  Session session = sessionFactory.openSession();
 	  Transaction ts = null;
 		try
 		{
			ts = session.beginTransaction();
			//开启事务
			
			Criteria c = session.createCriteria(ProjectInfo.class);
			boolean result = search.matches("[0-9]+");
			long l;
			Long ll;
			if(result)
			{
				l =  Long.parseLong(search);
				ll = new Long(l);
				c.add(Restrictions.eq("ProjectID", ll));
			}
			else
			{
				c.add(Restrictions.like("ProjectName", "%"+search+"%"));
			}
			list = c.list();
		
			//进行操作提交
			session.getTransaction().commit();
		}
		catch(Exception ex)
		{
			if(ts != null)ts.rollback();
			System.out.println("showProjectVote方法执行错误");
			ex.printStackTrace();
		}
		finally
		{
			session.close();
			sessionFactory.close();
		}			
		//后续返回操作
		return list;
	}
	
	/*
  显示项目得票情况
	传入参数：
	返回参数：List
	*/
	public List showProjectVote()
	{
	  List list = null;
	
	  Configuration cfg = new Configuration().configure(); 
	  //ServiceRegistry Sr = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
	  //SessionFactory sf = cfg.configure().buildSessionFactory(); 
	  SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
 	  Session session = sessionFactory.openSession();
 	  Transaction ts = null;
 		try
 		{
			ts = session.beginTransaction();
			//开启事务
		
			String hql = "from ProjectInfo as p order by p.VoteNum desc";
			Query q = session.createQuery(hql);
			list = q.list();
		 if (list == null) System.out.println("123123");
			//进行操作提交
			session.getTransaction().commit();
		}
		catch(Exception ex)
		{
			if(ts != null)ts.rollback();
			System.out.println("showProjectVote方法执行错误");
			ex.printStackTrace();
		}
		finally
		{
			session.close();
			sessionFactory.close();
		}
			
		
		//后续返回操作
		return list;
	}
	

	public ProjectInfo projectInfo(Long projectID)
	{
	
	  Configuration cfg = new Configuration().configure(); 
	  //ServiceRegistry Sr = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
	  //SessionFactory sf = cfg.configure().buildSessionFactory(); 
	  SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
 	  Session session = sessionFactory.openSession();
 	  Transaction ts = session.beginTransaction();
			//开启事务
		
		
		ProjectInfo p = (ProjectInfo)session.get(ProjectInfo.class,projectID);
		 
		
		//进行操作提交
		session.close();
		sessionFactory.close();

		
		//后续返回操作
		return p;
	}
}