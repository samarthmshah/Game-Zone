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

import VO.GameVO;
import VO.LinkSellerVO;
import VO.SellerVO;


public class SellerDAO {
	private static SessionFactory factory;
	private static ServiceRegistry serviceRegistry;

	public static void insert(SellerVO svo) {
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
	public static void update(long seller_id, String companyName, String firstName, String lastName, 
							  String email, String phNo, String address, String zip, String routingNumber, 
							  String accountNumber, String paypal) {
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
		String username = "", password = "", dob = "";
		int status = 0;
		try {
			tx = session.beginTransaction();
			// All this because of fucking behavior of session.saveOurUpdate! 
			Query query = session.createQuery("FROM SellerVO WHERE seller_id="+seller_id);
			Iterator<SellerVO> itr_seller = query.list().iterator();
			while(itr_seller.hasNext()){
				SellerVO getter = itr_seller.next();
				username = getter.getUsername();
				password = getter.getPassword();
				dob = getter.getDob();
				status = getter.getStatus();
			}
			SellerVO svo = new SellerVO(seller_id);
			svo.setAccountNumber(accountNumber);
			svo.setAddress(address);
			svo.setCompanyname(companyName);
			svo.setDob(dob);
			svo.setEmail(email);
			svo.setFirstname(firstName);
			svo.setLastname(lastName);
			svo.setPassword(password);
			svo.setPaypal(paypal);
			svo.setPhNo(phNo);
			svo.setRoutingNumber(routingNumber);
			svo.setStatus(status);
			svo.setUsername(username);
			svo.setZip(zip);
			svo = (SellerVO)session.merge(svo);
			session.update(svo);
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
		Session session;
		try{
			session = factory.getCurrentSession();
		}
		catch(Exception e){
			System.out.println("(SETUP) Cant get current session so opening new one.");
			session = factory.openSession();
		}
		Transaction tx = null;
		List<SellerVO> ls = new ArrayList<SellerVO>();
		String storedHashedPassword = null;
		try {
			tx = session.beginTransaction();
			Query getPasswordList = session.createQuery("SELECT password FROM SellerVO where username='"+username+"'");
			Iterator<String> pwditr = getPasswordList.list().iterator();
			while(pwditr.hasNext()){
				storedHashedPassword = pwditr.next();
				try {
					if(PasswordHash.validatePassword(password, storedHashedPassword)){
						Query q = session.createQuery("from SellerVO where username='"+username+"' and password='"+storedHashedPassword+"'");
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
			System.out.println("(SETUP) Cant get current session so opening new one.");
			session = factory.openSession();
		}
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
		Session session;
		try{
			session = factory.getCurrentSession();
		}
		catch(Exception e){
			System.out.println("(SETUP) Cant get current session so opening new one.");
			session = factory.openSession();
		}
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
	
	@SuppressWarnings("unchecked")
	public static void deleteSeller(long seller_id){
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
		System.out.println("deleting seller with seller_id "+seller_id+". But before that,");
		try {
			tx = session.beginTransaction();
			Query q1 = session.createQuery("from LinkSellerVO where seller_id="+new SellerVO(seller_id).getSeller_id());
			Iterator<LinkSellerVO> itr_link2bdeleted = q1.list().iterator();
			while(itr_link2bdeleted.hasNext()){
				LinkSellerVO lsvo = itr_link2bdeleted.next();
				session.delete(lsvo);
			}
			
			Query q2 = session.createQuery("from GameVO where seller_id="+new SellerVO(seller_id).getSeller_id());
			Iterator<GameVO> itr_game2bdeleted = q2.list().iterator();
			while(itr_game2bdeleted.hasNext()){
				GameVO gvo = itr_game2bdeleted.next();
				session.delete(gvo);
			}
			SellerVO svo = new SellerVO();
			svo.setSeller_id(seller_id);
			svo = (SellerVO) session.merge(svo);
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
	
	// Inserts into the Linksellervo table
		public static void insert(LinkSellerVO lsvo) {
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
				session.save(lsvo);
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
		public static List<LinkSellerVO> getTupleByLink(String link){
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
			List<LinkSellerVO> ls = null;
			try {
				tx = session.beginTransaction();
				q = session.createQuery("from LinkSellerVO where link='"+ link +"'");
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

		public static void updateLSVOOnActivation(LinkSellerVO lsvo){
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
				session.saveOrUpdate(lsvo);
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
		public static List<SellerVO> getSellerById(long seller_id){
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
			List<SellerVO> ls = null;
			try {
				tx = session.beginTransaction();
				q = session.createQuery("from SellerVO where seller_id="+ seller_id);
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
	
		public static void deactivate(long seller_id) {
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
				session.createQuery("UPDATE SellerVO SET status=0 WHERE seller_id="+seller_id).executeUpdate();
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