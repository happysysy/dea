package dea.homepage.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "webJpaDatabaseEntityManager",
        transactionManagerRef = "webJpaDatabaseTransactionManager",
        basePackages = "dea.homepage"
)
public class JpaDatabaseConfig {


    @Primary
    @Bean(name = "webJpaDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    @Profile({ "local" })
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }


    @Primary
    @Bean(name = "webJpaDatabaseEntityManager")
    public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory( EntityManagerFactoryBuilder builder ,@Qualifier("webJpaDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("dea.homepage")
                .persistenceUnit("SystemFailure")
                .build();
    }

    @Primary
    @Bean(name = "webJpaDatabaseTransactionManager")
    public PlatformTransactionManager mysqlTransactionManager(@Qualifier("webJpaDatabaseEntityManager") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
