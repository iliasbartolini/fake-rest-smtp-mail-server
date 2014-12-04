package com.thoughtworks.fakemail.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "fakeMail")
public class Config {

    private final String port;

    public Config() {
        this.port = "25";
    }

    public Config(String port) {
        this.port = port;
    }
}
