package Model;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
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

import VO.BuyerVO;
import VO.LinkBuyerVO;


public class BuyerDAO {
	private static SessionFactory factory;
	private static ServiceRegistry serviceRegistry;

	public static void insert(BuyerVO bvo) {
		setUp();
		Session session;
		try{
			session = factory.getCurrentSession();
		}
		catch(Exception e){
			session = factory.openSession();
		}
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
		Session session;
		try{
			session = factory.getCurrentSession();
		}
		catch(Exception e){
			session = factory.openSession();
		}
		Transaction tx = null;
		List<BuyerVO> ls = new ArrayList<BuyerVO>();
		String storedHashedPassword = null;
		try {
			tx = session.beginTransaction();
			Query getPasswordList = session.createQuery("SELECT password FROM BuyerVO where username='"+username+"'");
			Iterator<String> pwditr = getPasswordList.list().iterator();
			while(pwditr.hasNext()){
				storedHashedPassword = pwditr.next();
				try {
					if(PasswordHash.validatePassword(password, storedHashedPassword)){
						Query q = session.createQuery("from BuyerVO where username='"+username+"' and password='"+storedHashedPassword+"'");
						ls = q.list();
					}
				} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
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
		Session session;
		try{
			session = factory.getCurrentSession();
		}
		catch(Exception e){
			session = factory.openSession();
		}
		Transaction tx = null;
		boolean isAvail = false,
				conflictWithBuyer = true,
				conflictWithAdmin = true,
				conflictWithSeller = true;
		
		if(!(username.equals("sam") || username.equals("john")))	// If the UN is not sam or john
			conflictWithAdmin = false;	// Then there is no conflict
		
		try {
			tx = session.beginTransaction();
			
			Query q = session.createQuery("SELECT buyer_id from BuyerVO where username='"+ username +"'");
			@SuppressWarnings("rawtypes")
			List listOfBuyerWithSameUsername = q.list();
			if(!(listOfBuyerWithSameUsername != null && listOfBuyerWithSameUsername.size()>0))		// If the list is empty!
				conflictWithBuyer = false;		// Then there is no conflict
			
			Query q1 = session.createQuery("SELECT seller_id from SellerVO where username='"+ username +"'");
			@SuppressWarnings("rawtypes")
			List listOfSellerWithSameUsername = q1.list();
			if(!(listOfSellerWithSameUsername != null && listOfSellerWithSameUsername.size()>0)) // If the list is empty
				conflictWithSeller = false;
			
			if(conflictWithBuyer || conflictWithAdmin || conflictWithSeller)
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
	public static List<BuyerVO> showAll(){
		setUp();
		Session session;
		try{
			session = factory.getCurrentSession();
		}
		catch(Exception e){
			session = factory.openSession();
		}
		Transaction tx = null;
		List<BuyerVO> ls = new ArrayList<BuyerVO>();
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("from BuyerVO");
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
	
	public static void updateStatus(long buyer_id, String action){
		setUp();
		Session session;
		try{
			session = factory.getCurrentSession();
		}
		catch(Exception e){
			session = factory.openSession();
		}
		Transaction tx = null;
		Query q = null;
		try {
			tx = session.beginTransaction();
			if(action.equals("approve"))
				q=session.createQuery("UPDATE BuyerVO SET status ='1' WHERE buyer_id='"+buyer_id+"'");
			else
				q=session.createQuery("UPDATE BuyerVO SET status ='0' WHERE buyer_id='"+buyer_id+"'");
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
	
	public static void deleteBuyer(long buyer_id){
		setUp();
		Session session;
		try{
			session = factory.getCurrentSession();
		}
		catch(Exception e){
			session = factory.openSession();
		}
		Transaction tx = null;
		BuyerVO bvo = new BuyerVO();
		bvo.setBuyer_id(buyer_id);
		try {
			tx = session.beginTransaction();
			session.delete(bvo);
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
	
	// Inserts into the Linkbuyervo table
	public static void insert(LinkBuyerVO lbvo) {
		setUp();
		Session session;
		try{
			session = factory.getCurrentSession();
		}
		catch(Exception e){
			session = factory.openSession();
		}
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(lbvo);
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
	public static List<LinkBuyerVO> getTupleByLink(String link){
		setUp();
		Session session;
		try{
			session = factory.getCurrentSession();
		}
		catch(Exception e){
			session = factory.openSession();
		}
		Transaction tx = null;
		Query q = null;
		List<LinkBuyerVO> ls = null;
		try {
			tx = session.beginTransaction();
			q = session.createQuery("from LinkBuyerVO where link='"+ link +"'");
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
	
	public static void updateLBVOOnActivation(LinkBuyerVO lbvo){
		setUp();
		Session session;
		try{
			session = factory.getCurrentSession();
		}
		catch(Exception e){
			session = factory.openSession();
		}
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(lbvo);
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
	public static List<BuyerVO> getBuyerById(long buyer_id){
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
		Query q = null;
		List<BuyerVO> ls = null;
		try {
			tx = session.beginTransaction();
			q = session.createQuery("from BuyerVO where buyer_id="+ buyer_id);
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
			System.out.println("(SETUP) New Factory is created");
		} catch (Throwable t) {
			System.err.println("(SETUP) Unable to create the sessionFactory Object!");
			t.printStackTrace();
			throw new ExceptionInInitializerError(t);
		}
	}
};