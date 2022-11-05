package com.ryanrivera.app.frontdesk.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Data
@Component
public class AppConfig {

    @Value("${service.appcode}")
    private String appCode;
}
