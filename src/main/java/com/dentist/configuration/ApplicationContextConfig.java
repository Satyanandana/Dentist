package com.dentist.configuration;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;
import org.jasypt.hibernate4.encryptor.HibernatePBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;
import org.jasypt.salt.StringFixedSaltGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;

import com.dentist.util.GoogleServerToServer;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;

@Configuration
@ComponentScan(basePackages = "com.dentist.*")
@EnableTransactionManagement
@PropertySource(value = {"classpath:application.properties"})
public class ApplicationContextConfig {
	@Autowired
	private ServletContext sevletContext;
	@Autowired
	private Environment environment;

	@Bean
	public SessionRegistry sessionRegistry() {
		SessionRegistry sessionRegistry = new SessionRegistryImpl();
		return sessionRegistry;
	}

	@Bean
	public AuthenticationSuccessHandler getAuthenticationSuccessHandler() {

		AuthenticationSuccessHandler SuccessHandler = new SavedRequestAwareAuthenticationSuccessHandler();
		return SuccessHandler;
	}

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
		encryptorConfig.setPassword("Boston");
		// encryptorConfig.setSaltGenerator(stringFixedSaltGenerator());

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

	/*
	 * @Bean public StandardPBEStringEncryptor standardPBEStringEncryptor() {
	 * StandardPBEStringEncryptor standardPBEStringEncryptor = new
	 * StandardPBEStringEncryptor();
	 * standardPBEStringEncryptor.setConfig(environmentVariablesConfiguration())
	 * ; return standardPBEStringEncryptor; }
	 */

	/*
	 * Can be used with hibernate xml configuration.
	 * 
	 * @Bean public EncryptablePropertyPlaceholderConfigurer
	 * encryptablePropertyPlaceholderConfiguration(){
	 * EncryptablePropertyPlaceholderConfigurer ePPConfiguration = new
	 * EncryptablePropertyPlaceholderConfigurer(stringEncryptor()); Resource
	 * resource = new ClassPathResource("application.properties");
	 * ePPConfiguration.setLocation(resource);; return ePPConfiguration;
	 * 
	 * }
	 */

	@Bean(name = "encryptableProps")
	public Properties encryptableProperties() {
		Properties props = new EncryptableProperties(pooledPBEStringEncryptor());
		Resource resource = new ClassPathResource("application.properties");
		try {
			props.load(resource.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props;

	}

	// Email bean configuration

	@Bean(name = "emailSender")
	public JavaMailSenderImpl emailSender() {
		JavaMailSenderImpl emailSender = new JavaMailSenderImpl();
		Properties emailProperties = new Properties();
		emailProperties.setProperty("auth", "Container");
		emailProperties.setProperty("mail.smtp.auth", "true");
		emailProperties.setProperty("mail.smtp.starttls.enable", "true");
		emailProperties.setProperty("type", "javax.mail.Session");

		emailSender.setJavaMailProperties(emailProperties);
		emailSender.setHost("smtp.gmail.com");
		emailSender.setPort(587);
		emailSender.setProtocol("smtp");
		emailSender.setUsername("gtsatyansv");
		emailSender.setPassword("lancer4950");
		return emailSender;
	}

	// Velocity templating bean configuration for email template generation

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

	@Bean(name = "googleCredential")
	public GoogleCredential getGoogleCredential() {
		String serverAccountEmail = environment.getRequiredProperty("google.servertoserver.account.email");
		ArrayList<String> OuthScopes = new ArrayList<String>();
		OuthScopes.add(CalendarScopes.CALENDAR);
		File privateKeyFileP12 = new File(environment.getRequiredProperty("google.servertoserver.p12file"));
		GoogleCredential credential = GoogleServerToServer.getGoogleCredential(serverAccountEmail, privateKeyFileP12,
				OuthScopes);

		return credential;
	}

	@Bean(name = "googleCalendar")
	public Calendar getCalendar() throws GeneralSecurityException, IOException {

		Calendar calendar = GoogleServerToServer.getCalendar(getGoogleCredential(), "dentalappointmentcalander");
		return calendar;
	}

}
