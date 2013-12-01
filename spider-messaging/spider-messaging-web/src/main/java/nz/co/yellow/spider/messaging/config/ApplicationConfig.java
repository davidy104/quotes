package nz.co.yellow.spider.messaging.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = { "nz.co.yellow.spider.messaging" })
@Import({ InfrastructureContextConfiguration.class,
		TestDataContextConfiguration.class })
public class ApplicationConfig {

}
