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

import VO.SellerVO;


public class SellerDAO {
	private static SessionFactory factory;
	private static ServiceRegistry serviceRegistry;

	public static void insert(SellerVO svo) {
		setUp();
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(svo);
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
	public static List<SellerVO> checkUsernamePass(String username, String password){
		setUp();
		Session session = factory.openSession();
		Transaction tx = null;
		List<SellerVO> ls = new ArrayList<SellerVO>();
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("from SellerVO where username='"+username+"' and password='"+password+"'");
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
		boolean isAvail = false,
				conflictWithSeller = true,
				conflictWithAdmin = true,
				conflictWithBuyer = true;
		
		if(!(username.equals("sam") || username.equals("john")))	// If the UN is not sam or john
			conflictWithAdmin = false;	// Then there is no conflict
		
		try {
			tx = session.beginTransaction();
			
			Query q = session.createQuery("SELECT seller_id from SellerVO where username='"+ username +"'");
			@SuppressWarnings("rawtypes")	// Testing for conflict with Seller
			List listOfSellerWithSameUsername = q.list();
			if(!(listOfSellerWithSameUsername != null && listOfSellerWithSameUsername.size() > 0))	// If the list is empty
				conflictWithSeller = false;		// Then there is not conflict
			
			Query q1 = session.createQuery("SELECT buyer_id from BuyerVO where username='"+ username +"'");
			@SuppressWarnings("rawtypes")	// Testing for conflict with Buyer
			List listOfBuyerWithSameUsername = q1.list();
			if(!(listOfBuyerWithSameUsername != null && listOfBuyerWithSameUsername.size()>0))		// If the list is empty!
				conflictWithBuyer = false;		// Then there is no conflict
			
			if(conflictWithSeller || conflictWithAdmin || conflictWithBuyer)
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
	
	@SuppressWarnings("unchecked")
	public static List<SellerVO> showAll(){
		setUp();
		Session session = factory.openSession();
		Transaction tx = null;
		List<SellerVO> ls = new ArrayList<SellerVO>();
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("from SellerVO");
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
	
	public static void updateStatus(long seller_id, String action){
		setUp();
		Session session = factory.openSession();
		Transaction tx = null;
		Query q = null;
		try {
			tx = session.beginTransaction();
			if(action.equals("approve"))
				q=session.createQuery("UPDATE SellerVO SET status ='1' WHERE seller_id='"+seller_id+"'");
			else
				q=session.createQuery("UPDATE SellerVO SET status ='0' WHERE seller_id='"+seller_id+"'");
			q.executeUpdate();
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
	
	public static void deleteSeller(long seller_id){
		setUp();
		Session session = factory.openSession();
		Transaction tx = null;
		SellerVO svo = new SellerVO();
		svo.setSeller_id(seller_id);
		try {
			tx = session.beginTransaction();
			session.delete(svo);
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