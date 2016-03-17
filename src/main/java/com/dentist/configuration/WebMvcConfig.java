package com.dentist.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.dentist.webapp" })

public class WebMvcConfig extends WebMvcConfigurerAdapter {
	/* Add static pages to the view controller */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/aboutus").setViewName("aboutus");
		registry.addViewController("/accessDenied").setViewName("accessDenied");

	}

	/*
	 * ViewResolver bean for jsp, will resove the forward URL by adding prefix
	 * and suffix
	 */
	@Bean(name = "viewResolver")
	public InternalResourceViewResolver getViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	/*
	 * ViewResolver bean for velocity templates, will resove the forward URL by
	 * adding prefix and suffix
	 */
	/*
	 * @Bean public VelocityViewResolver getVelocityViewResolver() {
	 * VelocityViewResolver viewResolver = new VelocityViewResolver();
	 * viewResolver.setCache(true); viewResolver.setPrefix("");
	 * viewResolver.setSuffix(""); return viewResolver; }
	 */

	/*
	 * ResourceHandlerRegistry will handle all the requests to resource files
	 * like js,CSS,images etc
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**")
		        .addResourceLocations("/resources/")
		        .setCachePeriod(60*60*24);
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

}
