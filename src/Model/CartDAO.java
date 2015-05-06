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

import VO.CartVO;

public class CartDAO {
	private static SessionFactory factory;
	private static ServiceRegistry serviceRegistry;
	
	public static void insert(CartVO cvo) {
		setUp();
		Session session;
		try{
			session = factory.getCurrentSession();
		}
		catch(Exception e){
			System.out.println("(SETUP) Cant get current session so opening new one.");
			session = factory.openSession();
		}
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(cvo);
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
	
	public static void update(long cart_id, int game_quantity) {
		setUp();
		Session session;
		Query query;
		try{
			session = factory.getCurrentSession();
		}
		catch(Exception e){
			System.out.println("(SETUP) Cant get current session so opening new one.");
			session = factory.openSession();
		}
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			query = session.createQuery("UPDATE CartVO SET game_quantity="+game_quantity+" WHERE cart_id="+cart_id);
			query.executeUpdate();
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
	
	public static void delete(CartVO cvo) {
		setUp();
		Session session;
		try{
			session = factory.getCurrentSession();
		}
		catch(Exception e){
			System.out.println("(SETUP) Cant get current session so opening new one.");
			session = factory.openSession();
		}
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(cvo);
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
	
	public static void deleteByBuyerID(long buyer_id) {
		setUp();
		Session session;
		try{
			session = factory.getCurrentSession();
		}
		catch(Exception e){
			System.out.println("(SETUP) Cant get current session so opening new one.");
			session = factory.openSession();
		}
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.createQuery("DELETE FROM CartVO WHERE buyer_id="+buyer_id).executeUpdate();
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
	@SuppressWarnings({ "rawtypes" })
	public static List loadCart(long buyer_id) {
		setUp();
		Session session;
		try{
			session = factory.getCurrentSession();
		}
		catch(Exception e){
			System.out.println("(SETUP) Cant get current session so opening new one.");
			session = factory.openSession();
		}
		Transaction tx = null;
		List ls = null;
		try {
			tx = session.beginTransaction();
			ls = session.createQuery("from CartVO as cvo INNER JOIN cvo.game_id WHERE cvo.buyer_id="+buyer_id).list();
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
	public static List<CartVO> getCartByID(long cart_id) {
		setUp();
		Session session;
		try{
			session = factory.getCurrentSession();
		}
		catch(Exception e){
			System.out.println("(SETUP) Cant get current session so opening new one.");
			session = factory.openSession();
		}
		Transaction tx = null;
		List<CartVO> ls = null;
		try {
			tx = session.beginTransaction();
			ls = session.createQuery("from CartVO WHERE cart_id="+cart_id).list();
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
	public static List<CartVO> getCartByBuyerID(long buyer_id) {
		setUp();
		Session session;
		try{
			session = factory.getCurrentSession();
		}
		catch(Exception e){
			System.out.println("(SETUP) Cant get current session so opening new one.");
			session = factory.openSession();
		}
		Transaction tx = null;
		List<CartVO> ls = null;
		try {
			tx = session.beginTransaction();
			ls = session.createQuery("from CartVO WHERE buyer_id="+buyer_id).list();
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
	public static List<CartVO> checkIfGameAlreadyInCart(long buyer_id, long game_id) {
		setUp();
		Session session;
		try{
			session = factory.getCurrentSession();
		}
		catch(Exception e){
			System.out.println("(SETUP) Cant get current session so opening new one.");
			session = factory.openSession();
		}
		Transaction tx = null;
		List<CartVO> ls = null;
		try {
			tx = session.beginTransaction();
			ls = session.createQuery("from CartVO WHERE game_id="+game_id+" AND buyer_id="+buyer_id).list();
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
