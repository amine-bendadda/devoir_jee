package com.example.mscommande.configurations;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("mes-configs")
@RefreshScope
public class ApplicationPropertiesConfiguration {
    private int limitDeCommandes;
    private int commandeLast;

    public int getLimitDeCommandes() {
        return limitDeCommandes;
    }

    public void setLimitDeCommandes(int limitDeCommandes) {
        this.limitDeCommandes = limitDeCommandes;
    }

    public int getCommandeLast() {
        return commandeLast;
    }

    public void setCommandeLast(int commandeLast) {
        this.commandeLast = commandeLast;
    }
}
