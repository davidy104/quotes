package nz.co.yellow.spider.messaging.config;

import javax.servlet.annotation.WebServlet;

import com.sun.jersey.spi.spring.container.servlet.SpringServlet;
import javax.servlet.annotation.WebInitParam;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/rest/*" }, initParams = {
		@WebInitParam(name = "com.sun.jersey.config.property.packages", value = "nz.co.yellow.spider.messaging.resource"),
		@WebInitParam(name = "com.sun.jersey.api.json.POJOMappingFeature", value = "true") })
public class JerseySpringServlet extends SpringServlet {

}
