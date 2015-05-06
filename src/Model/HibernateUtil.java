package Model;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	private static final SessionFactory factory = setUp();
	private static ServiceRegistry serviceRegistry;
	
	private static SessionFactory setUp() {
		try {
			Configuration configuration = new Configuration();
			configuration.configure();
			serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();
			return configuration.buildSessionFactory(serviceRegistry);
		} catch (Throwable t) {
			System.err.println("Unable to create the sessionFactory Object!");
			t.printStackTrace();
			throw new ExceptionInInitializerError(t);
		}
	}
	
	public static SessionFactory getSessionFactory(){
		return factory;
	}
};
