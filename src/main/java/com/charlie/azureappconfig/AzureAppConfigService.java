package com.charlie.azureappconfig;

import com.azure.data.appconfiguration.ConfigurationAsyncClient;
import com.azure.data.appconfiguration.models.ConfigurationSetting;
import com.azure.data.appconfiguration.models.SettingSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AzureAppConfigService {

    private final ConfigurationAsyncClient configurationAsyncClient;
    private static final String APP_CONFIG_KEY_PREFIX = ".appconfig.featureflag/";

    @Autowired
    public AzureAppConfigService(ConfigurationAsyncClient configurationAsyncClient) {
        this.configurationAsyncClient = configurationAsyncClient;
    }

    public Mono<ConfigurationSetting> getConfigurationById(String featureName) {
        String fullKey = APP_CONFIG_KEY_PREFIX + featureName;
        return configurationAsyncClient.getConfigurationSetting(fullKey, null);
    }

    public Flux<ConfigurationSetting> getAllConfigurationSettings() {
        // The label parameter is set to null to get all settings regardless of label.
        return configurationAsyncClient.listConfigurationSettings(new SettingSelector().setLabelFilter(null));
    }
}