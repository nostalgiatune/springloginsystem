package com.tuoppi.springsecuresession.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:derby.properties")
public class PersistenceContext {
    
    @Autowired
    Environment env;
    
    @Bean
    public LocalContainerEntityManagerFactoryBean emfb(JpaVendorAdapter va,
            DataSource ds) {
        LocalContainerEntityManagerFactoryBean emfb =
                new LocalContainerEntityManagerFactoryBean();
        emfb.setJpaVendorAdapter(va);
        emfb.setDataSource(ds);
        emfb.setPackagesToScan("com.tuoppi.springsecuresession.user");
        emfb.getJpaPropertyMap().put("hbm2dll.auto", "create-drop");
        return emfb;
    }
    
    @Bean @Profile("dev")
    public DataSource embeddedDataSource() {
        
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.DERBY)
                .build();
    }
    
    @Bean @Profile("prod")
    public DataSource driverManagerDataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(env.getRequiredProperty("driver"));
        ds.setUrl(env.getRequiredProperty("url"));
        ds.setUsername(env.getRequiredProperty("username"));
        ds.setPassword(env.getRequiredProperty("password"));
        return ds;
    }
    
    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter va = new HibernateJpaVendorAdapter();
        va.setDatabase(Database.DERBY);
        va.setDatabasePlatform(null);
        va.setGenerateDdl(true);
        va.setShowSql(true);
        return va;
    }
    
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf,
            DataSource ds) {
        JpaTransactionManager txManager = new JpaTransactionManager(emf);
        txManager.setDataSource(ds);
        return txManager;
    }
    
    @Bean
    public PersistenceExceptionTranslationPostProcessor petpp() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
    
}