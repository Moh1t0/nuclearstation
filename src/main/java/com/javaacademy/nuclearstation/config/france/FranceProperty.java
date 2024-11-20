package com.javaacademy.nuclearstation.config.france;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;

@ConfigurationProperties(prefix = "economic")
@Profile("france")
@Data
public class FranceProperty {

    private BigDecimal basePrice;
    private BigDecimal discountRate;
    private String currency;
    private String countryName;
}
