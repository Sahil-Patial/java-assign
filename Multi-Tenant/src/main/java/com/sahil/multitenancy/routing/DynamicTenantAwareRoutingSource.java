package com.sahil.multitenancy.routing;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariDataSource;
import com.sahil.multitenancy.core.ThreadLocalStorage;
import com.sahil.multitenancy.routing.config.DatabaseConfiguration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.scheduling.annotation.Scheduled;

import javax.sql.DataSource;

import java.io.File;
import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class DynamicTenantAwareRoutingSource extends AbstractRoutingDataSource {

    private final String filename;
    private final ObjectMapper objectMapper;
    private final ConcurrentMap<String, HikariDataSource> tenants;

    public DynamicTenantAwareRoutingSource(String filename) {
        this(filename, new ObjectMapper());
    }

    public DynamicTenantAwareRoutingSource(String filename, ObjectMapper objectMapper) {
        this.filename = filename;
        this.objectMapper = objectMapper;
        this.tenants = getDataSources();
    }

    @Override
    public void afterPropertiesSet() {
        
    }

    @Override
    protected DataSource determineTargetDataSource() {
        String lookupKey = (String) determineCurrentLookupKey();

        
        return tenants.get(lookupKey);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return ThreadLocalStorage.getTenantName();
    }

    private ConcurrentMap<String, HikariDataSource> getDataSources() {

        
        DatabaseConfiguration[] configurations = getDatabaseConfigurations();

        
        return Arrays
                .stream(configurations)
                .collect(Collectors.toConcurrentMap(x -> x.getTenant(), x -> buildDataSource(x)));
    }

    private DatabaseConfiguration[] getDatabaseConfigurations() {
        try {
            return objectMapper.readValue(new File(filename), DatabaseConfiguration[].class);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    private HikariDataSource buildDataSource(DatabaseConfiguration configuration) {
        HikariDataSource dataSource = new HikariDataSource();

        dataSource.setInitializationFailTimeout(0);
        dataSource.setMaximumPoolSize(5);
        dataSource.setDataSourceClassName(configuration.getDataSourceClassName());
        dataSource.addDataSourceProperty("url", configuration.getUrl());
        dataSource.addDataSourceProperty("user", configuration.getUser());
        dataSource.addDataSourceProperty("password", configuration.getPassword());

        return dataSource;
    }

    @Scheduled(fixedDelay = 5000L)
    public void refreshDataSources() {
        DatabaseConfiguration[] configurations = getDatabaseConfigurations();

        removeObsoleteTenants(configurations);
        insertOrUpdateTenants(configurations);
    }

    private void insertOrUpdateTenants(DatabaseConfiguration[] configurations) {
        for (DatabaseConfiguration configuration : configurations) {
            if (tenants.containsKey(configuration.getTenant())) {
                HikariDataSource dataSource = tenants.get(configuration.getTenant());
                
                if (!isCurrentConfiguration(dataSource, configuration)) {
                    
                    dataSource.close();
                    
                    tenants.put(configuration.getTenant(), buildDataSource(configuration));
                }
            } else {
                tenants.put(configuration.getTenant(), buildDataSource(configuration));
            }
        }
    }
    private void removeObsoleteTenants(DatabaseConfiguration[] configurations) {

        
        Set<String> tenantNamesFromConfiguration = Arrays.stream(configurations)
                .map(x -> x.getTenant())
                .collect(Collectors.toSet());

        for (String tenant : tenants.keySet()) {

            
            if(!tenantNamesFromConfiguration.contains(tenant)) {

                
                HikariDataSource dataSource = tenants.get(tenant);

                
                dataSource.close();

                
                tenants.remove(tenant);
            }
        }
    }

    private boolean isCurrentConfiguration(HikariDataSource dataSource, DatabaseConfiguration configuration) {
        return Objects.equals(dataSource.getDataSourceProperties().getProperty("user"), configuration.getUser())
                && Objects.equals(dataSource.getDataSourceProperties().getProperty("url"), configuration.getUrl())
                && Objects.equals(dataSource.getDataSourceProperties().getProperty("password"), configuration.getPassword())
                && Objects.equals(dataSource.getDataSourceClassName(), configuration.getDataSourceClassName());
    }
}
