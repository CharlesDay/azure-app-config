package com.charlie.azureappconfig;

import com.azure.data.appconfiguration.ConfigurationAsyncClient;
import com.azure.data.appconfiguration.ConfigurationClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Autowired
    AppConfigProperties appConfigProperties;

    @Bean
    public ConfigurationAsyncClient configurationAsyncClient() {

        // Create a ConfigurationAsyncClient using the provided connection string
        return new ConfigurationClientBuilder()
                .connectionString(appConfigProperties.getConnectionString())
                .buildAsyncClient();
    }
}
