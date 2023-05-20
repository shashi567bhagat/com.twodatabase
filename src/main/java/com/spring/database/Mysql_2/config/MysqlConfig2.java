package com.spring.database.Mysql_2.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories
(  entityManagerFactoryRef = "SecondentityManagerFactoryBean",
   basePackages = {"com.spring.database.Mysql_2.Repo"},
   transactionManagerRef = "SecondtransactionManagers"
		
		
		)

public class MysqlConfig2 {
	
	@Autowired
	private Environment environment;

	// datasource

	@Bean(name = "SecondDataSource")
	@Primary
	public DataSource datasource() {
		DriverManagerDataSource datasource = new DriverManagerDataSource();

		datasource.setUrl(environment.getProperty("second.datasource.url"));
        datasource.setUsername(environment.getProperty("second.datasource.username"));
        datasource.setPassword(environment.getProperty("second.datasource.password"));
		datasource.setDriverClassName(environment.getProperty("second.datasource.driver-class-name"));
		return datasource;
		
	}

	// entityManagerFactory
	@Bean(name = "SecondentityManagerFactoryBean")
	@Primary
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean()
	{
		LocalContainerEntityManagerFactoryBean bean=new LocalContainerEntityManagerFactoryBean();
		
		bean.setDataSource(datasource());
		
		JpaVendorAdapter adapter=new HibernateJpaVendorAdapter();
		bean.setJpaVendorAdapter(adapter);
		 
		Map<String, String> map=new HashMap<>();
		map.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		map.put("hibernate.show_sql", "true");
		map.put("hibernate.hbm2ddl.auto", "update");
		
		bean.setJpaPropertyMap(map);
		bean.setPackagesToScan("com.spring.database.Mysql_2.Entity");
		return bean;
		
	}

	// PlateFormTrasactionalMnager
	
	@Bean(name = "SecondtransactionManagers")
	@Primary
	public PlatformTransactionManager transactionManager()
	{
		
		JpaTransactionManager manager=new JpaTransactionManager();
		
		manager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
		return manager;
		
	}

	
	

}
