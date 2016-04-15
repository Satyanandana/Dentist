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
import javax.servlet.Filter;
import javax.servlet.ServletContext;

import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MvcWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { ApplicationContextConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { WebMvcConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected Filter[] getServletFilters() {
		return new Filter[] { new DelegatingFilterProxy("springSecurityFilterChain") };
	}

	@Override
	protected void registerDispatcherServlet(ServletContext servletContext) {
		super.registerDispatcherServlet(servletContext);

		servletContext.addListener(new HttpSessionEventPublisher());

	}
}
