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
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

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

            System.out.println( " bean이름 ::: " +beanName );
        }


    }

    public static Object getBean(final String beanName) {
        return context.getBean(beanName);
    }

    @Bean
    public SpringApplicationContext springApplicationContext() {
        return new SpringApplicationContext();
    }



    @Bean
    public SpringResourceTemplateResolver templateResolver(){
        // SpringResourceTemplateResolver automatically integrates with Spring's own
        // resource resolution infrastructure, which is highly recommended.
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext( context );
        templateResolver.setPrefix("classpath:templates/");
        templateResolver.setSuffix(".html");
        // HTML is the default value, added here for the sake of clarity.
        templateResolver.setTemplateMode(TemplateMode.HTML);
        // Template cache is true by default. Set to false if you want
        // templates to be automatically updated when modified.
        templateResolver.setCacheable(true);
        return templateResolver;
    }
}
