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
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;
import org.jasypt.hibernate4.encryptor.HibernatePBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;
import org.jasypt.salt.StringFixedSaltGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;

import com.dentist.googlecalendar.GoogleServerToServer;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.maxmind.geoip.LookupService;

@Configuration
@ComponentScan(basePackages = "com.dentist.*")
@EnableTransactionManagement
@EnableScheduling
@PropertySource(value = {"classpath:application.properties"})
public class ApplicationContextConfig {
	@Autowired
	private ServletContext sevletContext;
	@Autowired
	private Environment environment;
	@Autowired
	private ResourceLoader resourceLoader;
	@Autowired
	@Qualifier("encryptableProps")
	private Properties encryptableProps;

	private static final Logger LOGGER = Logger.getLogger(ApplicationContextConfig.class);

	/**
	 * Bean required to maintains the registry of sessions.In order to make this
	 * bean work register HttpSessionEventPublisher listener with Spring
	 * DispatcherServlet.
	 **/
	@Bean
	public SessionRegistry sessionRegistry() {
		SessionRegistry sessionRegistry = new SessionRegistryImpl();
		return sessionRegistry;
	}

	/**
	 * When a request is intercepted and requires authentication, the request
	 * data is stored to record the original destination before the
	 * authentication process commenced, and to allow the request to be
	 * reconstructed when a redirect to the same URL occurs. This bean is
	 * responsible for performing the redirect to the original URL if
	 * appropriate.
	 **/
	@Bean
	public AuthenticationSuccessHandler getAuthenticationSuccessHandler() {

		AuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
		return successHandler;
	}

	/**
	 * JASYPT configuration to integrate with Hibernate 4 and Spring FrameWork
	 **/

	@Bean
	public StringFixedSaltGenerator stringFixedSaltGenerator() {
		StringFixedSaltGenerator stringFixedSaltGenerator = new StringFixedSaltGenerator("justAnotherSaltforGX");
		return stringFixedSaltGenerator;
	}

	@Bean
	public EnvironmentStringPBEConfig environmentVariablesConfiguration() {
		EnvironmentStringPBEConfig encryptorConfig = new EnvironmentStringPBEConfig();
		encryptorConfig.setProvider(new BouncyCastleProvider());
		encryptorConfig.setAlgorithm("PBEWITHSHA256AND128BITAES-CBC-BC");
		encryptorConfig.setPassword("Lancer");
		// encryptorConfig.setStringOutputType("hexadecimal");
		// encryptorConfig.setSaltGenerator(new
		// StringFixedSaltGenerator("mySalt"));
		// config.setPasswordEnvName("APP_ENCRYPTION_PASSWORD");
		return encryptorConfig;
	}

	@Bean
	public PooledPBEStringEncryptor pooledPBEStringEncryptor() {
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		encryptor.setConfig(environmentVariablesConfiguration());
		encryptor.setPoolSize(5);
		return encryptor;
	}

	@Bean
	public HibernatePBEStringEncryptor hibernatePBEStringEncryptor() {
		HibernatePBEStringEncryptor hibernatePBEStringEncryptor = new HibernatePBEStringEncryptor();
		hibernatePBEStringEncryptor.setConfig(environmentVariablesConfiguration());
		hibernatePBEStringEncryptor.setEncryptor(pooledPBEStringEncryptor());
		hibernatePBEStringEncryptor.setRegisteredName("HibernateStringEncryptor");
		return hibernatePBEStringEncryptor;
	}

	/**
	 * Required to get the decrypted properties wrapped with ENC() in
	 * application.properties files in classpath
	 **/
	@Bean(name = "encryptableProps")
	public Properties encryptableProperties() {
		Properties props = new EncryptableProperties(pooledPBEStringEncryptor());
		Resource resource = new ClassPathResource("application.properties");
		try {
			props.load(resource.getInputStream());
		} catch (IOException e) {
			LOGGER.error("application.properties file not found in classpath", e);
		}
		return props;

	}

	/**
	 * Email bean configuration
	 **/

	@Bean(name = "emailSender")
	public JavaMailSenderImpl emailSender() {
		JavaMailSenderImpl emailSender = new JavaMailSenderImpl();
		Properties emailProperties = new Properties();
		emailProperties.setProperty("auth", "Container");
		emailProperties.setProperty("mail.smtp.auth", "true");
		emailProperties.setProperty("mail.smtp.starttls.enable", "true");
		emailProperties.setProperty("type", "javax.mail.Session");

		emailSender.setJavaMailProperties(emailProperties);
		emailSender.setHost(environment.getProperty("email.host"));
		emailSender.setPort(Integer.parseInt(environment.getProperty("email.port")));
		emailSender.setProtocol("smtp");
		emailSender.setUsername(encryptableProps.getProperty("email.username"));
		emailSender.setPassword(encryptableProps.getProperty("email.password"));
		return emailSender;
	}

	/**
	 * Velocity templating bean configuration for email template generation
	 **/

	@Bean
	public VelocityConfigurer velocityConfigurer() throws VelocityException, IOException {
		VelocityConfigurer velocityConfigurer = new VelocityConfigurer();
		velocityConfigurer.setResourceLoaderPath("/WEB-INF/velocity/emailtemplates/");
		velocityConfigurer.setServletContext(sevletContext);
		velocityConfigurer.afterPropertiesSet();
		return velocityConfigurer;
	}

	@Bean(name = "velocityEngine")
	public VelocityEngine velocityEngine() throws VelocityException, IOException {
		VelocityEngine velocityEngine = velocityConfigurer().createVelocityEngine();
		return velocityEngine;
	}

	/**
	 * Configuration to establish server to server connection with Goolge
	 **/
	@Bean(name = "googleCredential")
	public GoogleCredential getGoogleCredential() throws IOException {
		String serverAccountEmail = environment.getRequiredProperty("google.servertoserver.account.email");
		ArrayList<String> OuthScopes = new ArrayList<String>();
		OuthScopes.add(CalendarScopes.CALENDAR);
		File privateKeyFileP12 = resourceLoader.getResource("classpath:DentalCalKey.p12").getFile();
		GoogleCredential credential = GoogleServerToServer.getGoogleCredential(serverAccountEmail, privateKeyFileP12, OuthScopes);
		return credential;
	}

	/**
	 * Configuration to establish connection with Google Calendar
	 **/
	@Bean(name = "googleCalendar")
	public Calendar getCalendar() throws GeneralSecurityException, IOException {
		Calendar calendar = GoogleServerToServer.getCalendar(getGoogleCredential(), "dentalappointmentcalander");
		return calendar;
	}

	/**
	 * Required to get the Geo location of the user base on remote IP address
	 **/
	@Bean(name = "lookupService")
	public LookupService getGeoLocation() throws IOException {
		Resource geoLiteDatabaseFile = resourceLoader.getResource("classpath:GeoLiteCity.dat");
		LookupService lookup = new LookupService(geoLiteDatabaseFile.getFile(), LookupService.GEOIP_MEMORY_CACHE);
		return lookup;
	}

}
