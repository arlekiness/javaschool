package ru.javasch.metro.configuration;

//import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.hibernate5.HibernateTransactionManager;
//import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//import java.util.Properties;
//
//
//    @Configuration
//    @EnableTransactionManagement
//    @PropertySource({ "classpath:application.properties" })
//    @ComponentScan({ "ru.javasch.metro" })
//    public class HibernateConfiguration {
//
//        @Autowired
//        private Environment env;
//
//        @Bean
//        public LocalSessionFactoryBean sessionFactory() {
//            LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//            sessionFactory.setDataSource(restDataSource());
//            sessionFactory.setPackagesToScan(
//                    "ru.javasch.metro.model");
//            sessionFactory.setHibernateProperties(hibernateProperties());
//
//            return sessionFactory;
//        }
//
//        @Bean
//        public DataSource restDataSource() {
//            BasicDataSource dataSource = new BasicDataSource();
//            dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
//            dataSource.setUrl(env.getProperty("jdbc.url"));
//            dataSource.setUsername(env.getProperty("jdbc.username"));
//            dataSource.setPassword(env.getProperty("jdbc.password"));
//
//            return dataSource;
//        }
//
//        @Bean
//        @Autowired
//        public HibernateTransactionManager transactionManager(
//                SessionFactory sessionFactory) {
//
//            HibernateTransactionManager txManager
//                    = new HibernateTransactionManager();
//            txManager.setSessionFactory(sessionFactory);
//
//            return txManager;
//        }
//
//        @Bean
//        public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
//            return new PersistenceExceptionTranslationPostProcessor();
//        }
//
//        Properties hibernateProperties() {
//            return new Properties() {
//                {
//                    setProperty("hibernate.hbm2ddl.auto",
//                            env.getProperty("hibernate.hbm2ddl.auto"));
//                    setProperty("hibernate.dialect",
//                            env.getProperty("hibernate.dialect"));
//                    setProperty("hibernate.globally_quoted_identifiers",
//                            "true");
//                }
//            };
//        }
//    }

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@EnableTransactionManagement
@ComponentScan({"ru.javasch.metro"})
@PropertySource(value = {"classpath:application.properties"})
public class HibernateConfiguration {

    private static final String MODEL_PACKAGE = "ru.javasch.metro.model";
    private static final String JDBC_DRIVER_CLASS_NAME_PROPERTY = "jdbc.driverClassName";
    private static final String JDBC_URL_PROPERTY = "jdbc.url";
    private static final String JDBC_USERNAME = "jdbc.username";
    private static final String JDBC_PASSWORD = "jdbc.password";
    private static final String HIBERNATE_DIALECT_PROPERTY = "hibernate.dialect";
    private static final String HIBERNATE_SHOW_SQL_PROPERTY = "hibernate.show_sql";
    private static final String HIBERNATE_FORMAT_SQL_PROPERTY = "hibernate.format_sql";

    @Autowired
    private Environment environment;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(MODEL_PACKAGE);
        sessionFactory.setHibernateProperties(hibernateProperties());
        Properties property = new Properties();
        property.put("hibernate.id.new_generator_mappings", "false");
        sessionFactory.setHibernateProperties(property);
        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty(JDBC_DRIVER_CLASS_NAME_PROPERTY));
        dataSource.setUrl(environment.getRequiredProperty(JDBC_URL_PROPERTY));
        dataSource.setUsername(environment.getProperty(JDBC_USERNAME));
        dataSource.setPassword(environment.getProperty(JDBC_PASSWORD));
        return dataSource;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put(HIBERNATE_DIALECT_PROPERTY, environment.getRequiredProperty(HIBERNATE_DIALECT_PROPERTY));
        properties.put(HIBERNATE_SHOW_SQL_PROPERTY, environment.getRequiredProperty(HIBERNATE_SHOW_SQL_PROPERTY));
        properties.put(HIBERNATE_FORMAT_SQL_PROPERTY, environment.getRequiredProperty(HIBERNATE_FORMAT_SQL_PROPERTY));
        return properties;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(s);
        return txManager;
    }
}