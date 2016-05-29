package com.dentist.configuration;

/**
 * 
 *
 * @author  Satyanandana Srikanthvarma Vadapalli
 * @email srikanthvarma.vadapalli@gmail.com
 * @version 1.0
 * @since   Mar 17, 20161:10:28 AM
 *       
 */
import java.util.Properties;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({"com.dentist.dao", "com.dentist.domain"})
@PropertySource(value = {"classpath:application.properties"})
public class HibernateConfig {

	@Autowired
	private Environment environment;
	@Autowired
	@Qualifier("encryptableProps")
	private Properties encryptableProps;

	/**
	 * Configuration to create an instance of SessionFactory.This variant of
	 * LocalSessionFactoryBean requires Hibernate 4.0 or higher.it is closer to
	 * AnnotationSessionFactoryBean since its core purpose is to bootstrap a
	 * SessionFactory from package scanning.
	 **/
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[]{"com.dentist.*"});
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	/**
	 * Configuration to create an instance of DataSource to establish a
	 * connection with the Database.A DataSource that can be instantiated
	 * through IoC and implements the DataSource interface since the
	 * DataSourceProxy is used as a generic proxy. The DataSource simply wraps a
	 * ConnectionPool in order to provide a standard interface to the user
	 **/
	@Bean
	public DataSource dataSource() {

		PoolProperties p = new PoolProperties();
		p.setUrl(environment.getRequiredProperty("jdbc.url"));
		p.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
		p.setUsername(environment.getRequiredProperty("jdbc.username"));
		/** If the password is not encrypted in application.properties **/
		// p.setPassword(environment.getRequiredProperty("jdbc.password"));
		/** If the password encrypted in application.properties **/
		p.setPassword(encryptableProps.getProperty("jdbc.password"));
		p.setJmxEnabled(true);
		p.setTestWhileIdle(false);
		p.setTestOnBorrow(true);
		p.setValidationQuery("SELECT 1");
		p.setTestOnReturn(false);
		p.setValidationInterval(30000);
		p.setTimeBetweenEvictionRunsMillis(30000);
		p.setMaxActive(100);
		p.setInitialSize(10);
		p.setMaxWait(10000);
		p.setRemoveAbandonedTimeout(60);
		p.setMinEvictableIdleTimeMillis(30000);
		p.setMinIdle(10);
		p.setLogAbandoned(true);
		p.setRemoveAbandoned(true);
		p.setDefaultAutoCommit(false);
		p.setJdbcInterceptors(
				"org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;" + "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
		DataSource dataSource = new DataSource();
		dataSource.setPoolProperties(p);

		return dataSource;
	}

	/**
	 * Adding the hibernate properties extracted from application.properties
	 * file in classpath to java.util.Properties.
	 **/
	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
		properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
		properties.put("hibernate.current_session_context_class", environment.getRequiredProperty("hibernate.current_session_context_class"));
		// properties.put("hibernate.hbm2ddl.auto",
		// environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
		// properties.put("hibernate.hbm2ddl.import_files",
		// environment.getRequiredProperty("hibernate.hbm2ddl.import_files"));
		return properties;
	}

	/**
	 * Configuration of Hibernate Transaction Manager.This is important to make
	 * Spring Transaction work i.e @Transactional.Set LocalSessionFactory to
	 * transaction manager.
	 * 
	 * Binds a Hibernate Session from the specified factory to the thread,
	 * potentially allowing for one thread-bound Session per factory.
	 * SessionFactory.getCurrentSession() is required for Hibernate access code
	 * that needs to support this transaction handling mechanism, with the
	 * SessionFactory being configured with SpringSessionContext.
	 * 
	 * Supports custom isolation levels, and timeouts that get applied as
	 * Hibernate transaction timeouts.
	 * 
	 * This transaction manager is appropriate for applications that use a
	 * single Hibernate SessionFactory for transactional data access, but it
	 * also supports direct DataSource access within a transaction (i.e. plain
	 * JDBC code working with the same DataSource). This allows for mixing
	 * services which access Hibernate and services which use plain JDBC
	 * (without being aware of Hibernate)! Application code needs to stick to
	 * the same simple Connection lookup pattern as with
	 * 
	 **/
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory s) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(s);
		return txManager;
	}
}