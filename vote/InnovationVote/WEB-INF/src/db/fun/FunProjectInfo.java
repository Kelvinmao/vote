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
���ڶ�ProjectInfo������в���
*/

public class FunProjectInfo
{
	/*
	����ͶƱɾ��
	��ĿƱ��-1
	���룺��Ŀ���
	��������ĿƱ��
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
			//��������
		
		
		ProjectInfo p = (ProjectInfo)session.get(ProjectInfo.class,projectID);
		num = p.getVoteNum();
		num--;
		p.setVoteNum(num);
		session.save(p);
		 
		
		//���в����ύ
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();

		
		//�������ز���
		return num;
	}
	
	/*
	����ͶƱ����
	��ĿƱ��+1
	���룺��Ŀ���
	��������ĿƱ��
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
		//��������
		
		
	  ProjectInfo p = (ProjectInfo)session.get(ProjectInfo.class,projectID);
	  num = p.getVoteNum();
	  num++;
	  p.setVoteNum(num);
	  session.save(p);
		 
		
		//���в����ύ
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();

		
		//�������ز���
		return num;
	}
	
	/*
  ��ʾ��Ŀ��Ʊ��������򡢷�ҳ��ʾ
	������������ڷ�ҳ����������ÿҳ������
	���ز�����List
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
			//��������
		
			String hql = "from ProjectInfo as p order by p.VoteNum desc";
			Query q = session.createQuery(hql);
			q.setFirstResult(firstPage);
			q.setMaxResults(maxPage);
			list = q.list();
		
			//���в����ύ
			session.getTransaction().commit();
		}
		catch(Exception ex)
		{
			if(ts != null)ts.rollback();
			System.out.println("showProjectVote����ִ�д���");
			ex.printStackTrace();
		}
		finally
		{
			session.close();
			sessionFactory.close();
		}
			
		
		//�������ز���
		return list;
	}
	
	/*
  ��Ŀ��ҳ��ʾ
	�����������Ŀ���
	���ز�����ProjectInfo����
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
			//��������
		
			p = (ProjectInfo)session.get(ProjectInfo.class,projectID);
		
			//���в����ύ
			session.getTransaction().commit();
		}
		catch(Exception ex)
		{
			if(ts != null)ts.rollback();
			System.out.println("showProjectOnly����ִ�д���");
			ex.printStackTrace();
		}
		finally
		{
			session.close();
			sessionFactory.close();
		}

		//�������ز���
		return p;
	}
	
	/*
	over
  ģ����ѯ�����з�ҳ��ʾ
	������������ڷ�ҳ����������ÿҳ����������ѯ��ID����ѯ������
	���ز�����List
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
			//��������
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
		
			//���в����ύ
			session.getTransaction().commit();
		}
		catch(Exception ex)
		{
			if(ts != null)ts.rollback();
			System.out.println("showProjectVote����ִ�д���");
			ex.printStackTrace();
		}
		finally
		{
			session.close();
			sessionFactory.close();
		}			
		//�������ز���
		return list;
	}
	
	/*
	over
  ģ����ѯ
	�����������ѯ��ID����ѯ������
	���ز�����List
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
			//��������
			
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
		
			//���в����ύ
			session.getTransaction().commit();
		}
		catch(Exception ex)
		{
			if(ts != null)ts.rollback();
			System.out.println("showProjectVote����ִ�д���");
			ex.printStackTrace();
		}
		finally
		{
			session.close();
			sessionFactory.close();
		}			
		//�������ز���
		return list;
	}
	
	/*
  ��ʾ��Ŀ��Ʊ���
	���������
	���ز�����List
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
			//��������
		
			String hql = "from ProjectInfo as p order by p.VoteNum desc";
			Query q = session.createQuery(hql);
			list = q.list();
		 if (list == null) System.out.println("123123");
			//���в����ύ
			session.getTransaction().commit();
		}
		catch(Exception ex)
		{
			if(ts != null)ts.rollback();
			System.out.println("showProjectVote����ִ�д���");
			ex.printStackTrace();
		}
		finally
		{
			session.close();
			sessionFactory.close();
		}
			
		
		//�������ز���
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
			//��������
		
		
		ProjectInfo p = (ProjectInfo)session.get(ProjectInfo.class,projectID);
		 
		
		//���в����ύ
		session.close();
		sessionFactory.close();

		
		//�������ز���
		return p;
	}
}