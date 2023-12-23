package com.charlie.azureappconfig;


import com.azure.data.appconfiguration.models.ConfigurationSetting;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Objects;

@RestController
public class Controller {
    @Autowired
    private AzureAppConfigService appConfigService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/config/{id}")
    public ResponseEntity<?> checkConfig(@PathVariable String id) {
        try {
            String value = Objects.requireNonNull(appConfigService.getConfigurationById(id).block()).getValue();
            return ResponseEntity.ok().body(objectMapper.readValue(value, FeatureToggle.class));
        } catch (Exception e) {
            // ignored
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public Flux<ConfigurationSetting> getAll() {
        return appConfigService.getAllConfigurationSettings();
    }
}