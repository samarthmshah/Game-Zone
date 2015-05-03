package Model;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import VO.GameVO;

public class GameDAO {
	private static SessionFactory factory;
	private static ServiceRegistry serviceRegistry;
	
	public static void insert(GameVO gvo) {
		setUp();
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(gvo);
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
	public static List<GameVO> getGamesBySeller_id(long seller_id) {
		setUp();
		Session session = factory.openSession();
		Transaction tx = null;
		Query query;
		List<GameVO> ls = null;
		try {
			tx = session.beginTransaction();
			query = session.createQuery("from GameVO WHERE seller_id="+seller_id);
			ls = query.list();
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
	public static List<GameVO> getGameByGame_id(long game_id) {
		setUp();
		Session session = factory.openSession();
		Transaction tx = null;
		Query query;
		List<GameVO> ls = null;
		try {
			tx = session.beginTransaction();
			query = session.createQuery("from GameVO WHERE game_id="+game_id);
			ls = query.list();
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
	
	public static void update(GameVO gvo) {
		setUp();
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(gvo);
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
	
	public static void delete(long game_id) {
		setUp();
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			GameVO gvo = new GameVO(game_id);
			session.delete(gvo);
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
