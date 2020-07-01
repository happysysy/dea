package dea.homepage.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Import({ JpaDatabaseConfig.class })
public class WebConfig {
    @Configuration
    @EnableWebMvc
    static public class WebMvcConfig implements WebMvcConfigurer {
        private static final String[] CLASSPATH_RESOURCE_LOCATIONS = { "classpath:/static/" };

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/static/**")
                    .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
        }
    }
}
