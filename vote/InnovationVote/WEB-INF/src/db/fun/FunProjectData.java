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

/*******************用于对project_data表的所有操作************************/
// 11
public class FunProjectData
{
	
	//验证项目密码
	public boolean checkProject(String projectID,String projectPassword)
	{
		
		boolean b;
		Configuration cfg = new Configuration().configure(); 
	  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
	  SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
 	  Session session = sessionFactory.openSession();
 	  Transaction ts = session.beginTransaction();
 	  
 	  Criteria a = session.createCriteria(ProjectData.class);
		a.add(Restrictions.eq("ProjectID",projectID));
		if(a.list().size()==0)
		{
			session.getTransaction().commit();
			session.close();
			sessionFactory.close();
			System.out.print("sfjahfkjlaks");
			return false;
		}
		else
		{
			ProjectData p=(ProjectData)session.get(ProjectData.class,projectID);
		if(projectPassword.equals(p.getProjectPassword()))
			b=true;
		else
			b=false;
		//System.out.print(b);
			session.getTransaction().commit();
			session.close();
			sessionFactory.close();
			return b;
		}
	}
	//修改密码
	public void modifyProjectPassword(String projectID,String newProjectPassword)
	{
		
		Configuration cfg = new Configuration().configure(); 
	  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
	  SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
 	  Session session = sessionFactory.openSession();
 	  Transaction ts = session.beginTransaction();
 	  
 	  
		ProjectData p=(ProjectData)session.get(ProjectData.class,projectID);
		p.setProjectPassword(newProjectPassword);
		
		session.save(p);
 	  session.getTransaction().commit();
		session.close();
		sessionFactory.close();
	}

	
	/**********************************************************
                函数一：
                验证项目ID、用户ID是否存在，用户密码
                传入：projectID(项目ID) 和 userID(用户ID) 和 userPassword(用户密码)
  ***********************************************************
  
  public boolean checkPoll(String projectID, String userID, String userPassword)
  { 
  	int m = 0;
  	int n = 0;
		//String str="0";
	
	  Configuration cfg = new Configuration().configure(); 
	  //ServiceRegistry Sr = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
	  //SessionFactory sf = cfg.configure().buildSessionFactory(); 
	  SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
 	  Session session = sessionFactory.openSession();
 	  Transaction ts = session.beginTransaction();
			//开启事务
			
  	  Criteria a = session.createCriteria(ProjectData.class);
			a.add(Restrictions.eq("ProjectID",projectID));
			Criteria b = session.createCriteria(UserData.class);
			b.add(Restrictions.and(Restrictions.eq("UserPassword",userPassword),
														 Restrictions.eq("UserID",userID)
														 ));

			m = a.list().size();
			n = b.list().size();
	  //取出验证码？
		
		//进行操作提交
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();

		
		//后续返回操作
		return (m>0&&n>0);
  	}*/
/*************************************************************************
										函数一：
										用于投票删除，项目票数-1
										传入：projectID(项目编号) 
										传出：num(项目票数)
*************************************************************************/
	public Long  minusProjectVoteNumber(String projectID)
	{
		Long num = 0L;
	  Configuration cfg = new Configuration().configure(); 
	  //configure()方法默认会在classpath下面寻找hibernate.cfg.xml文件，如果没有找到该文件，系统会打印如下信息并抛出HibernateException异常。
	  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
	  SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
 	  Session session = sessionFactory.openSession();
 	  Transaction ts = session.beginTransaction();
 	  
 	  	ProjectData p=(ProjectData)session.get(ProjectData.class,projectID);
 	  	num=p.getVoteNumber();
 	  	num--;
 	  	p.setVoteNumber(num);
 	  	
 	  	session.save(p);
 	  	session.getTransaction().commit();
			session.close();
			sessionFactory.close();

		return num;
	}
	
/*************************************************************************
										函数二：
										用于投票增加，项目票数+1
  									传入：projectID（项目编号）
  									传出：num（项目票数)
*************************************************************************/
	public Long addProjectPollNumber(String projectID)
 	{
 		Long num = 0L;

	  Configuration cfg = new Configuration().configure(); 
	  //configure()方法默认会在classpath下面寻找hibernate.cfg.xml文件，如果没有找到该文件，系统会打印如下信息并抛出HibernateException异常。
	  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
	  SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
 	  Session session = sessionFactory.openSession();
 	  Transaction ts = session.beginTransaction();
 	  
 	  ProjectData p=(ProjectData)session.get(ProjectData.class,projectID);
 	  num=p.getVoteNumber();
 	  num++;
 	  p.setVoteNumber(num);
 	  
 	  session.save(p);
 	  session.getTransaction().commit();
		session.close();
		sessionFactory.close();
		return num;
	}
	
/*************************************************************************
										函数三：
  									显示项目得票情况、按票数从高到低排序、分页显示
  									传入参数：firstPage（用于分页的首行数）、
  														maxPage（每页的行数）
  									返回参数：List
*************************************************************************/
	

	public List showProjectVote(int firstPage,int maxPage)
	{
		List list = null;
	  Configuration cfg = new Configuration().configure(); 
	  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
	  SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
 	  Session session = sessionFactory.openSession();
 	  Transaction ts = null;
 	  try
 	  {
 	  	ts = session.beginTransaction();
 	  	String hql = "from ProjectData as p order by p.VoteNumber desc";
 	  	Query q = session.createQuery(hql);
			q.setFirstResult(firstPage);//设置要查询的结果集的开始索引位置
			q.setMaxResults(maxPage);//设置要查询的结果集的数量
			list = q.list();
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
		return list;
	}
	
/*************************************************************************
										函数四：
  									按得票从高到低显示所有项目的得票情况（不进行分页显示）
  									传入参数：
  									返回参数：list
*************************************************************************/
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
			String hql = "from ProjectData as p order by p.VoteNumber desc";
			Query q = session.createQuery(hql);
			list = q.list();
		 if (list == null) System.out.println("数据库里项目数为0");
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
		return list;
	}
	
/*************************************************************************
										函数五：
  									项目单页显示
  									传入参数：projectID(项目编号)
  									返回参数：ProjectData对象
*************************************************************************/
	public ProjectData showProjectOnly(String projectID)
	{
		ProjectData p = null;
	
	  Configuration cfg = new Configuration().configure(); 
	  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
	  SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
 	  Session session = sessionFactory.openSession();
 	  Transaction ts=session.beginTransaction();
 	  
			p = (ProjectData)session.get(ProjectData.class,projectID);
			
			session.getTransaction().commit();
			session.close();
			sessionFactory.close();
			
		return p;
	}
	//按分类选取projectdata no fen ye
	
	public List ProjectDataClassifySearch(String projectClassify)
	{
		List list=null;
		
		Configuration cfg = new Configuration().configure(); 
	  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
	  SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
 	  Session session = sessionFactory.openSession();
 	  Transaction ts = null;
 	  
 	  try
 	  {
 	  	ts = session.beginTransaction();
			Criteria c = session.createCriteria(ProjectData.class);
			c.add(Restrictions.eq("ProjectClassify",projectClassify));
			list = c.list();
			session.getTransaction().commit();
		}
		catch(Exception ex)
		{
			if(ts != null)ts.rollback();
			System.out.println("ProjectDataSearch方法执行错误");
			ex.printStackTrace();
		}
		finally
		{
			session.close();
			sessionFactory.close();
		}			
		return list;
	}
	//按分类选取projectdata  fen ye
	public List ProjectDataClassifySearch(int firstPage,int maxPage,String projectClassify)
	{
		List list=null;
		
		Configuration cfg = new Configuration().configure(); 
	  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
	  SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
 	  Session session = sessionFactory.openSession();
 	  Transaction ts = session.beginTransaction();
 	  
		Criteria c = session.createCriteria(ProjectData.class);
		c.add(Restrictions.eq("ProjectClassify",projectClassify));
		c.setFirstResult(firstPage);
		c.setMaxResults(maxPage);
		list = c.list();
		
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
			
		return list;
	}

/*************************************************************************
										函数六：模糊查询并进行分页显示
  									传入参数：firstPage用于分页的首行数、
  														maxPage每页的行数、
  														search查询的ID、查询的名字
  									返回参数：List 若查找不到则返回一个空的list
*************************************************************************/
	public List ProjectDataSearch(int firstPage,int maxPage,String search)
	{
		List list=null;
		
		Configuration cfg = new Configuration().configure(); 
	  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
	  SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
 	  Session session = sessionFactory.openSession();
 	  Transaction ts = null;
 	  
 	  try
 	  {
 	  	ts = session.beginTransaction();
			Criteria c = session.createCriteria(ProjectData.class);
			c.add(Restrictions.like("ProjectID", "%"+search+"%"));
			c.add(Restrictions.like("ProjectName", "%"+search+"%"));
			c.setFirstResult(firstPage);
			c.setMaxResults(maxPage);
			list = c.list();
			session.getTransaction().commit();
		}
		catch(Exception ex)
		{
			if(ts != null)ts.rollback();
			System.out.println("ProjectDataSearch方法执行错误");
			ex.printStackTrace();
		}
		finally
		{
			session.close();
			sessionFactory.close();
		}			
		return list;
	}
	
/*************************************************************************
										函数七：模糊查询（不进行分页显示）
  									传入参数：search查询的ID、查询的名字
  									返回参数：List 若查找不到则返回一个空的list
*************************************************************************/	
	public List ProjectDataSearch(String search)
	{
		List list=null;
		
		Configuration cfg = new Configuration().configure(); 
	  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
	  SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
 	  Session session = sessionFactory.openSession();
 	  Transaction ts = null;
 	  
 	  try
 	  {
 	  	ts = session.beginTransaction();
			Criteria c = session.createCriteria(ProjectData.class);
			c.add(Restrictions.like("ProjectID", "%"+search+"%"));
			c.add(Restrictions.like("ProjectName", "%"+search+"%"));
			list = c.list();
			session.getTransaction().commit();
		}
		catch(Exception ex)
		{
			if(ts != null)ts.rollback();
			System.out.println("ProjectDataSearch方法执行错误");
			ex.printStackTrace();
		}
		finally
		{
			session.close();
			sessionFactory.close();
		}			
		return list;
	}
/*************************************************************************
										函数八：上传简介
  									传入：projectID（项目编号），projectIntroduction(简介)
*************************************************************************/
	public void uploadIntroduction(String projectID,String projectIntroduction)
	{
		List list=null;
		Configuration cfg = new Configuration().configure(); 
	  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
	  SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
 	  Session session = sessionFactory.openSession();
 	  Transaction ts = null;
 	  
 	  
 	  
 	  	ts = session.beginTransaction();
 	  	ProjectData p=(ProjectData)session.get(ProjectData.class,projectID);
 	  	p.setProjectIntroduction(projectIntroduction);
			session.save(p);
 	  	session.getTransaction().commit();
		
			session.close();
			sessionFactory.close();
					
	}
	/*************************************************************************
										函数八：上传分类
  									传入：projectID（项目编号），projectClassify(项目类型)
*************************************************************************/
	public void uploadProjectClassify(String projectID,String projectClassify)
	{
		Configuration cfg = new Configuration().configure(); 
	  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
	  SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
 	  Session session = sessionFactory.openSession();
 	  Transaction ts = session.beginTransaction();
 	  
 	  ProjectData p=(ProjectData)session.get(ProjectData.class,projectID);
 	  p.setProjectClassify(projectClassify);
 	  
 	  session.save(p);
 	  session.getTransaction().commit();
 	  
 	  session.close();
		sessionFactory.close();
	}
	//上传图片
	public void uploadProjectImage(String projectID,String projectImage)
	{
		Configuration cfg = new Configuration().configure(); 
	  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
	  SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
 	  Session session = sessionFactory.openSession();
 	  Transaction ts = session.beginTransaction();
 	  
 	  ProjectData p=(ProjectData)session.get(ProjectData.class,projectID);
 	  p.setProjectImage(projectImage);
 	  
 	  session.save(p);
 	  session.getTransaction().commit();
 	  
 	  session.close();
		sessionFactory.close();
	}
	/*获取图片
	public String getPictureAddress(String projectID)
	{
		String str;
		Configuration cfg = new Configuration().configure(); 
	  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
	  SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
 	  Session session = sessionFactory.openSession();
 	  Transaction ts = session.beginTransaction();
 	  
 	  ProjectData p=(ProjectData)session.get(ProjectData.class,projectID);
 	  str=p.getPictureAddress();

		session.getTransaction().commit();
 	  session.close();
		sessionFactory.close();
		return str;
	}*/
	//获取一个项目对象
	public ProjectData getProjectData(String projectID)
	{
		Configuration cfg = new Configuration().configure(); 
	  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
	  SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
 	  Session session = sessionFactory.openSession();
 	  Transaction ts = session.beginTransaction();
 	  
 	  ProjectData p = (ProjectData)session.get(ProjectData.class,projectID);
 	  
 	  
 	  session.getTransaction().commit();
 	  session.close();
		sessionFactory.close();
		
		return p;
	}
	//member
	public void uploadProjectMembers (String projectID,String projectMembers)//,Long  memberNumber
	{
		Configuration cfg = new Configuration().configure(); 
	  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
	  SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
 	  Session session = sessionFactory.openSession();
 	  Transaction ts = session.beginTransaction();
 	  
 	  ProjectData p = (ProjectData)session.get(ProjectData.class,projectID);
 	  p.setProjectMembers(projectMembers);
 	  //p.setMemberNumber(memberNumber);
 	  
 	 	session.save(p);
 	  session.getTransaction().commit();
 	  session.close();
		sessionFactory.close();

	}
	//上传负责人
	public void uploadLeader (String projectID,String leaderName)//,String leaderTel,String leaderEmail
	{
		Configuration cfg = new Configuration().configure(); 
	  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
	  SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
 	  Session session = sessionFactory.openSession();
 	  Transaction ts = session.beginTransaction();
 	  
 	  ProjectData p = (ProjectData)session.get(ProjectData.class,projectID);
 	  p.setLeaderName(leaderName);
 	 // p.setLeaderTel(leaderTel);
 	  //p.setLeaderEmail(leaderEmail);
 	  
 	  session.save(p);
 	  session.getTransaction().commit();
 	  session.close();
		sessionFactory.close();

	}
	
	//上传标题
	public void uploadProjectName(String projectID,String projectName)
	{
		Configuration cfg = new Configuration().configure(); 
	  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
	  SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
 	  Session session = sessionFactory.openSession();
 	  Transaction ts = session.beginTransaction();
 	  
 	  ProjectData p = (ProjectData)session.get(ProjectData.class,projectID);
 	  p.setProjectName(projectName);
 	  
 	  session.save(p);
 	  session.getTransaction().commit();
 	  session.close();
		sessionFactory.close();
	}
		public boolean checkProjectID(String projectID)
 	{
 		Configuration cfg = new Configuration().configure(); 
	  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
	  SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
 	  Session session = sessionFactory.openSession();
 	  Transaction ts =session.beginTransaction();

		
		Criteria b = session.createCriteria(ProjectData.class);
		b.add(Restrictions.eq("ProjectID",projectID));
		
		if(b.list().size()==0)
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
	
}