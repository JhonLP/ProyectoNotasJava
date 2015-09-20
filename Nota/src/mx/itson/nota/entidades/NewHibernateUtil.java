package mx.itson.nota.entidades;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Luis Quiñónez
 */

public class NewHibernateUtil {

	private static ServiceRegistry serviceRegistry;
	private static SessionFactory factory;
    
    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
        	Configuration configuracion = new Configuration();
	           configuracion.configure();
	           
	           serviceRegistry = new ServiceRegistryBuilder().applySettings(configuracion.getProperties()).buildServiceRegistry();        
	           factory = configuracion.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return factory;
    }
}
