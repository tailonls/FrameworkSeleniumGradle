package br.com.automacao.repositorio.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

//@SpringBootApplication
@Configuration
@ComponentScan("br.com.automacao")
@PropertySource("classpath:application.properties")
@EnableFeignClients("br.com.automacao")
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class TestContextConfiguration {

    private static final String BASE_NAME_ALTERNATIVO = "nomeDaBaseAlternativa";
    private static final String BASE_NAME = "tbcliente";
    private static final String USER = "admin";
    private static final String PASS = "";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:8080/";

    @Bean(name = "jdbcTemplate")
    JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean(name = "jdbcTemplateAlternativo")
    JdbcTemplate jdbcTemplateAlternativo() {
        return new JdbcTemplate(dataSourceAlternativo());
    }

    private DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(DRIVER);
        driverManagerDataSource.setUrl(URL + BASE_NAME);
        driverManagerDataSource.setUsername(USER);
        driverManagerDataSource.setPassword(PASS);

        return driverManagerDataSource;
    }

    private DataSource dataSourceAlternativo() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(DRIVER);
        driverManagerDataSource.setUrl(URL + BASE_NAME_ALTERNATIVO);
        driverManagerDataSource.setUsername(USER);
        driverManagerDataSource.setPassword(PASS);

        return driverManagerDataSource;
    }
}