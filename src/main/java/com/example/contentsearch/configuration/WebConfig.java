package com.example.contentsearch.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

@Order(Ordered.HIGHEST_PRECEDENCE)
public class WebConfig implements WebApplicationInitializer {

	final static Logger log = LoggerFactory.getLogger(WebApplicationInitializer.class);

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

		log.info("Web application initlalizer starting up!");

		log.debug("Creating the 'root' Spring application context");
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(SpringConfig.class);

		log.debug("Manage the lifecycle of the root application context");
		servletContext.addListener(new ContextLoaderListener(ctx));
		servletContext.setInitParameter("contextConfigLocation", "com.example.contentsearch");

		log.debug("Setting up the Jersey Rest Servlet");
		ServletRegistration.Dynamic jerseyServlet = servletContext.addServlet("jersey-servlet", new ServletContainer());
		jerseyServlet.setInitParameter("javax.ws.rs.Application", "com.example.contentsearch.configuration.RestConfig");
		jerseyServlet.addMapping("/api/*");
		jerseyServlet.setLoadOnStartup(1);

	}
}