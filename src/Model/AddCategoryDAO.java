package Model;

import java.util.ArrayList;
import java.util.Iterator;
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
import VO.GameVO;

public class AddCategoryDAO {
	private static SessionFactory factory;
	private static ServiceRegistry serviceRegistry;

	public static void insert(String cat_name, String cat_description) {
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
		Session session;
		try{
			session = factory.getCurrentSession();
		}
		catch(Exception e){
			System.out.println("(SETUP) Cant get current session so opening new one.");
			session = factory.openSession();
		}
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
		Session session;
		try{
			session = factory.getCurrentSession();
		}
		catch(Exception e){
			System.out.println("(SETUP) Cant get current session so opening new one.");
			session = factory.openSession();
		}
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
	
	@SuppressWarnings("unchecked")
	public static void delete(long cat_id){
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
			
			System.out.println("Deleting category with cat_id "+cat_id+". But before that,");
			
			// Nw delete all entries in GameSubcategoryVo with cat_id
			Query query = session.createQuery("from GameSubCategoryVO where cat_id="+new GameCategoryVO(cat_id).getCat_id());
			Iterator<GameSubCategoryVO> itr_scat2delete = query.list().iterator();
			while(itr_scat2delete.hasNext()){
				GameSubCategoryVO gscvo = itr_scat2delete.next();
				System.out.println("Deleting entry with scat_id: "+gscvo.getScat_id());
				session.delete(gscvo);
			}
			
			Query query2 = session.createQuery("from GameVO where cat_id="+new GameCategoryVO(cat_id).getCat_id());
			Iterator<GameVO> itr_game2delete = query2.list().iterator();
			while(itr_game2delete.hasNext()){
				GameVO gvo = itr_game2delete.next();
				System.out.println("Deleting entry with game_id: "+gvo.getGame_id());
				session.delete(gvo);
			}
			
			GameCategoryVO gcvo = new GameCategoryVO();
			gcvo.setCat_id(cat_id);
			
			// After safely removing all entries from subcategories. remove category!
			gcvo = (GameCategoryVO)session.merge(gcvo);
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