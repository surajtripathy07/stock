package stockindexing.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class StockIndexingInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

		AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
		appContext.register(StockIndexingConfig.class);
		appContext.setServletContext(servletContext);
		DispatcherServlet dispatcherServlet = new DispatcherServlet(appContext);
		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);

		Dynamic dynamic = servletContext.addServlet("stockindexing", dispatcherServlet);
		dynamic.addMapping("/");
		dynamic.setLoadOnStartup(1);
	}

}
