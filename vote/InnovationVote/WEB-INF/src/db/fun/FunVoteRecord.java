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
���ڶ�VoteRecord������в���
*/

public class FunVoteRecord
{
	/*
	�ж�ͶƱ�Ƿ��ظ�
	���룺��Ŀ��š�ѧ��
	���أ�true(�ظ�) false(���ظ�)
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
			//��������
		
			Criteria c = session.createCriteria(VoteRecord.class);
			c.add(Restrictions.and(Restrictions.eq("ProjectID",projectID),
														 Restrictions.eq("OutID",outID)
														 ));

			i = c.list().size();
		 
		
		//���в����ύ
		session.close();
		sessionFactory.close();

		
		//�������ز���
		return i>0?true:false;
	}
	
	/*
	ɾƱ
	���룺��Ŀ��š�ѧ��
	���أ�void
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
			//��������
		
			Criteria c = session.createCriteria(VoteRecord.class);
			c.add(Restrictions.and(Restrictions.eq("ProjectID",projectID),
														 Restrictions.eq("OutID",outID)
														 ));
			List list = c.list();
			VoteRecord v = (VoteRecord)list.get(0);
			Long l = v.getVoteID();
			VoteRecord u = (VoteRecord)session.get(VoteRecord.class,l);
			session.delete(u);
		 
		
		//���в����ύ
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();

		
		//�������ز���
	}
	
	/*
	ɾƱ
	���룺��Ŀ��š�ѧ��
	���أ�void
	*/
	public void voteDelete(Long projectID,String outID)
	{
	  VoteDelete(projectID,outID);
	  new FunUserData().minusUserVoteNum(outID);
	  new FunProjectInfo().minusProjectVoteNum(projectID);
	}
}