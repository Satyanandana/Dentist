package com.dentist.configuration;

import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
/**
 * 
 *
 * @author  Satyanandana Srikanthvarma Vadapalli
 * @email srikanthvarma.vadapalli@gmail.com
 * @version 1.0
 * @since   Mar 17, 20161:10:28 AM
 *       
 */
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mobile.device.DeviceHandlerMethodArgumentResolver;
import org.springframework.mobile.device.DeviceResolverHandlerInterceptor;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.dentist.googlecalendar.CustomJodaDateTimeSerializer;
import com.dentist.googlecalendar.CustomJodaLocalDateSerializer;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
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
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/").setCachePeriod(60 * 60 * 24);
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
		builder.indentOutput(true).serializerByType(LocalDate.class, new CustomJodaLocalDateSerializer())
				.serializerByType(DateTime.class, new CustomJodaDateTimeSerializer());
		;
		converters.add(new MappingJackson2HttpMessageConverter(builder.build()));

	}

	@Bean
	public DeviceResolverHandlerInterceptor deviceResolverHandlerInterceptor() {
		return new DeviceResolverHandlerInterceptor();
	}

	@Bean
	public DeviceHandlerMethodArgumentResolver deviceHandlerMethodArgumentResolver() {
		return new DeviceHandlerMethodArgumentResolver();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(deviceResolverHandlerInterceptor());
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(deviceHandlerMethodArgumentResolver());
	}

}
