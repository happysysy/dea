package dea.homepage;

import dea.homepage.config.WebConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.h2.H2ConsoleAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Arrays;

@Slf4j
@ComponentScan( basePackages = {"dea.homepage"})
@EnableJpaAuditing
@EnableAutoConfiguration( exclude = {DataSourceAutoConfiguration.class, H2ConsoleAutoConfiguration.class})
@SpringBootConfiguration
@Import(WebConfig.class)
public class WebApplication extends SpringBootServletInitializer {

    private static ApplicationContext context;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(WebApplication.class);
    }

    public static void main( final String[] args ) {

        ApplicationContext ctx = SpringApplication.run( WebApplication.class, args );

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort( beanNames );
        for (String beanName : beanNames) {

        }
    }

    public static Object getBean(final String beanName) {
        return context.getBean(beanName);
    }

    @Bean
    public SpringApplicationContext springApplicationContext() {
        return new SpringApplicationContext();
    }


}
