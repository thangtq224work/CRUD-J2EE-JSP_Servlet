package util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import model.TuLanh;
import model.Account;

public class HibernateUtil {
	private static final SessionFactory FACTORY;
	static {
		Configuration configuration = new Configuration();
		Properties properties = new Properties();
		properties.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");
		properties.put(Environment.URL, "jdbc:sqlserver://localhost:1433;databaseName=assignment;encrypt=true;trustServerCertificate=true;");
		properties.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
		properties.put(Environment.USER,"sa");
		properties.put(Environment.PASS, "thang1212");
		properties.put(Environment.SHOW_SQL,"true");
//		properties.put(Environment.HBM2DDL_AUTO,"create");
		configuration.addProperties(properties);
		configuration.addAnnotatedClass(TuLanh.class);
		configuration.addAnnotatedClass(Account.class);
		ServiceRegistry registry= new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		FACTORY = configuration.buildSessionFactory(registry);
	}
	public static SessionFactory getFactory() {
		return FACTORY;
	}
}
