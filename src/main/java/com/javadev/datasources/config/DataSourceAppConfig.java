package com.javadev.datasources.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Objects;
import java.util.Properties;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = "com.javadev.datasources")
@PropertySource({"classpath:student-datasource.properties","classpath:course-datasource.properties"})
public class DataSourceAppConfig {

    private Environment environment;

    @Autowired
    public DataSourceAppConfig(Environment environment){
        this.environment = environment;
    }

    @Bean
    public DataSource studentDataSource(){
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();

        try {
            comboPooledDataSource.setDriverClass(environment.getProperty("student.jdbc.driver"));
        }catch (PropertyVetoException e){
            throw new RuntimeException(e);
        }

        comboPooledDataSource.setJdbcUrl(environment.getProperty("student.jdbc.url"));
        comboPooledDataSource.setUser(environment.getProperty("student.jdbc.user"));
        comboPooledDataSource.setPassword(environment.getProperty("student.jdbc.password"));

        comboPooledDataSource.setInitialPoolSize(Integer.parseInt(Objects.requireNonNull(environment.getProperty("student.connection.pool.initialPoolSize"))));
        comboPooledDataSource.setMinPoolSize(Integer.parseInt(Objects.requireNonNull(environment.getProperty("student.connection.pool.minPoolSize"))));
        comboPooledDataSource.setMaxPoolSize(Integer.parseInt(Objects.requireNonNull(environment.getProperty("student.connection.pool.maxPoolSize"))));
        comboPooledDataSource.setMaxIdleTime(Integer.parseInt(Objects.requireNonNull(environment.getProperty("student.connection.pool.maxIdleTime"))));

        return comboPooledDataSource;
    }

    @Bean
    public LocalSessionFactoryBean studentSessionFactory(){
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();

        sessionFactoryBean.setDataSource(studentDataSource());
        sessionFactoryBean.setPackagesToScan(environment.getProperty("student.hibernate.packagesToScan"));
        Properties properties = new Properties();
        properties.setProperty("student.hibernate.dialect",environment.getProperty("student.hibernate.dialect"));
        properties.setProperty("student.hibernate.show_sql",environment.getProperty("student.hibernate.show_sql"));
        sessionFactoryBean.setHibernateProperties(properties);

        return sessionFactoryBean;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager studentTransactionManager(
            @Qualifier("studentSessionFactory")SessionFactory sessionFactory
            ){
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
        hibernateTransactionManager.setSessionFactory(sessionFactory);

        return hibernateTransactionManager;
    }

    @Bean
    public DataSource courseDataSource(){
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();

        try {
            comboPooledDataSource.setDriverClass(environment.getProperty("course.jdbc.driver"));
        }catch (PropertyVetoException e){
            throw new RuntimeException(e);
        }
        comboPooledDataSource.setJdbcUrl(environment.getProperty("course.jdbc.url"));
        comboPooledDataSource.setUser(environment.getProperty("course.jdbc.user"));
        comboPooledDataSource.setPassword(environment.getProperty("course.jdbc.password"));

        comboPooledDataSource.setInitialPoolSize(Integer.parseInt(Objects.requireNonNull(environment.getProperty("course.connection.pool.initialPoolSize"))));
        comboPooledDataSource.setMaxPoolSize(Integer.parseInt(Objects.requireNonNull(environment.getProperty("course.connection.pool.maxPoolSize"))));
        comboPooledDataSource.setMinPoolSize(Integer.parseInt(Objects.requireNonNull(environment.getProperty("course.connection.pool.minPoolSize"))));
        comboPooledDataSource.setMaxIdleTime(Integer.parseInt(Objects.requireNonNull(environment.getProperty("course.connection.pool.maxIdleTime"))));

        return comboPooledDataSource;
    }

    @Bean
    public LocalSessionFactoryBean courseSessionFactory(){
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();

        sessionFactoryBean.setDataSource(courseDataSource());
        sessionFactoryBean.setPackagesToScan(environment.getProperty("course.hibernate.packagesToScan"));
        Properties properties = new Properties();
        properties.setProperty("course.hibernate.dialect",environment.getProperty("course.hibernate.dialect"));
        properties.setProperty("course.hibernate.show_sql",environment.getProperty("course.hibernate.show_sql"));

        sessionFactoryBean.setHibernateProperties(properties);

        return sessionFactoryBean;

    }

    @Bean
    @Autowired
    public HibernateTransactionManager courseTransactionManager(
            @Qualifier("courseSessionFactory") SessionFactory sessionFactory
    ){
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();

        hibernateTransactionManager.setSessionFactory(sessionFactory);

        return hibernateTransactionManager;
    }



}
