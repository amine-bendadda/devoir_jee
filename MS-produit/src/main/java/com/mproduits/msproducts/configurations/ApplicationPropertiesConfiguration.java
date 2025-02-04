package com.mproduits.msproducts.configurations;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("mes-configs")
@RefreshScope
@Data
public class ApplicationPropertiesConfiguration {
    private int limitDeProduits;
}
