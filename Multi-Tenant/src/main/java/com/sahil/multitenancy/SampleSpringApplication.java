package com.sahil.multitenancy;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import com.sahil.multitenancy.routing.TenantAwareRoutingSource;
import com.zaxxer.hikari.HikariDataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAsync
@EnableScheduling
@EnableTransactionManagement
public class SampleSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleSpringApplication.class, args);
	}

	@Bean
	public DataSource dataSource() {

		AbstractRoutingDataSource dataSource = new TenantAwareRoutingSource();

		Map<Object,Object> targetDataSources = new HashMap<>();

		targetDataSources.put("TenantOne", tenantOne());
		targetDataSources.put("TenantTwo", tenantTwo());

		dataSource.setTargetDataSources(targetDataSources);

		dataSource.afterPropertiesSet();

		return dataSource;
	}

	public DataSource tenantOne() {

		HikariDataSource dataSource = new HikariDataSource();

		dataSource.setInitializationFailTimeout(0);
		dataSource.setMaximumPoolSize(5);
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/sampledb1");
		dataSource.addDataSourceProperty("user", "root");
		dataSource.addDataSourceProperty("password", "2001");

		return dataSource;
	}

	public DataSource tenantTwo() {

		HikariDataSource dataSource = new HikariDataSource();

		dataSource.setInitializationFailTimeout(0);
		dataSource.setMaximumPoolSize(5);
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/sampldb2");
		dataSource.addDataSourceProperty("user", "root");
		dataSource.addDataSourceProperty("password", "2001");

		return dataSource;
	}
}