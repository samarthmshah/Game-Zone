package Model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import VO.BuyerVO;


public class BuyerDAO {
	private static SessionFactory factory;
	private static ServiceRegistry serviceRegistry;

	public static void insert(BuyerVO bvo) {
		setUp();
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(bvo);
			tx.commit();
		} 
		catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} 
		finally {
			session.close();
		}
	}
	@SuppressWarnings("unchecked")
	public static List<BuyerVO> checkUsernamePass(String username, String password){
		setUp();
		Session session = factory.openSession();
		Transaction tx = null;
		List<BuyerVO> ls = new ArrayList<BuyerVO>();
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("from BuyerVO where username='"+username+"' and password='"+password+"'");
			ls = q.list();
			tx.commit();
		} 
		catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} 
		finally {
			session.close();
		}
		return ls;
	}
	
	public static boolean checkUsernameAvailability(String username) {
		setUp();
		Session session = factory.openSession();
		Transaction tx = null;
		boolean isAvail = false;
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("SELECT buyer_id from BuyerVO where username='"+ username +"'");
			@SuppressWarnings("rawtypes")
			List ls = q.list();
			if(ls != null && ls.size() > 0)
				isAvail = false;
			else
				isAvail = true;
			tx.commit(); 
		} 
		catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} 
		finally {
			session.close();
		}
		return isAvail;
	}
	
	private static void setUp() {
		try {
			Configuration configuration = new Configuration();
			configuration.configure();
			serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();
			factory = configuration.buildSessionFactory(serviceRegistry);
		} catch (Throwable t) {
			System.err.println("Unable to create the sessionFactory Object!");
			t.printStackTrace();
			throw new ExceptionInInitializerError(t);
		}
	}
};