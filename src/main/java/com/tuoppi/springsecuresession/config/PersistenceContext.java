package com.tuoppi.springsecuresession.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
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
    
    @Bean
    public DataSource dataSource() {
        
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.DERBY)
                .build();
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