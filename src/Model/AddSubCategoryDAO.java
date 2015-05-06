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
import VO.GameSubCategoryVO;

public class AddSubCategoryDAO {
	private static SessionFactory factory;
	private static ServiceRegistry serviceRegistry;

	public static void insert(GameCategoryVO gcvo, String scat_name, String scat_description) {
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
			session.save(new GameSubCategoryVO(gcvo, scat_name, scat_description));
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static List<GameSubCategoryVO> showAll(){
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
		List<GameSubCategoryVO> ls = new ArrayList<GameSubCategoryVO>();
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("from GameSubCategoryVO");
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
	public static List<GameSubCategoryVO> edit(long scat_id){
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
		List<GameSubCategoryVO> ls = null;
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("from GameSubCategoryVO where scat_id="+scat_id);
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
	
	public static void update(GameCategoryVO cat_id, long scat_id, String scat_name, String scat_description) {
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
			GameSubCategoryVO gscvo = new GameSubCategoryVO();
			gscvo.setCat_id(cat_id);
			gscvo.setScat_id(scat_id);
			gscvo.setScat_name(scat_name);
			gscvo.setScat_description(scat_description);
			session.saveOrUpdate(gscvo);
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
	
	public static void delete(long scat_id){
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
			GameSubCategoryVO gscvo = new GameSubCategoryVO();
			gscvo.setScat_id(scat_id);
			session.delete(gscvo);
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
	
	public static void delete(GameSubCategoryVO gscvo){
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
			session.delete(gscvo);
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
	public static List<GameSubCategoryVO> getTuplesByCatID(long cat_id){
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
		List<GameSubCategoryVO> ls = null;
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("from GameSubCategoryVO where cat_id="+cat_id);
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