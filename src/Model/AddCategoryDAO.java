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

import VO.GameCategoryVO;

public class AddCategoryDAO {
	private static SessionFactory factory;
	private static ServiceRegistry serviceRegistry;

	public static void insert(String cat_name, String cat_description) {
		setUp();
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(new GameCategoryVO(cat_name, cat_description));
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
	public static List<GameCategoryVO> showAll(){
		setUp();
		Session session = factory.openSession();
		Transaction tx = null;
		List<GameCategoryVO> ls = new ArrayList<GameCategoryVO>();
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("from GameCategoryVO");
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
	
	@SuppressWarnings("unchecked")
	public static List<GameCategoryVO> edit(long cat_id){
		setUp();
		Session session = factory.openSession();
		Transaction tx = null;
		List<GameCategoryVO> ls = null;
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("from GameCategoryVO where cat_id="+cat_id);
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
	
	public static void update(long cat_id, String cat_name, String cat_description) {
		setUp();
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			GameCategoryVO gcvo = new GameCategoryVO();
			gcvo.setCat_id(cat_id);
			gcvo.setCat_name(cat_name);
			gcvo.setCat_description(cat_description);
			session.saveOrUpdate(gcvo);
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
	
	public static void delete(long cat_id){
		setUp();
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			GameCategoryVO gcvo = new GameCategoryVO();
			gcvo.setCat_id(cat_id);
			session.delete(gcvo);
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