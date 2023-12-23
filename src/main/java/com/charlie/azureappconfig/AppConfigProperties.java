package com.charlie.azureappconfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "azure.app")
@Data
public class AppConfigProperties {
    private String connectionString;
}
