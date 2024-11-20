package com.javaacademy.nuclearstation.config.marocco;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;

@Profile("morocco")
@ConfigurationProperties(prefix = "economic")
@Data
public class MoroccoProperty {
    private BigDecimal basePrice;
    private BigDecimal increasedPrice;
    private BigDecimal threshold;
    private String currency;
    private String countryName;
}
