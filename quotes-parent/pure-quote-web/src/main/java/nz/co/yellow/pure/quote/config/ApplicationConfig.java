package nz.co.yellow.pure.quote.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
/**
 * Application for Webapp
 * @author david
 *
 */
@Configuration
@ComponentScan(basePackages = { "nz.co.yellow.pure.quote" })
@Import({ InfrastructureContextConfiguration.class,
		TestDataContextConfiguration.class })
public class ApplicationConfig {

}
