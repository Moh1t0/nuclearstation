package com.javaacademy.nuclearstation.config.france;

import com.javaacademy.nuclearstation.departments.EconomicDepartment;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@Profile("france")
@RequiredArgsConstructor
@EnableConfigurationProperties(value = FranceProperty.class)
public class FranceEconomicDepartment extends EconomicDepartment {

    private static final BigDecimal BILLION = BigDecimal.valueOf(1_000_000_000);

    private final FranceProperty franceProperty;


    @Override
    public BigDecimal computeYearIncomes(long countElectricity) {
        BigDecimal totalIncome = BigDecimal.ZERO;
        BigDecimal remainingElectricity = BigDecimal.valueOf(countElectricity);

        while (remainingElectricity.compareTo(BigDecimal.ZERO) > 0) {
            totalIncome = totalIncome.add(
                    remainingElectricity
                            .min(BILLION)
                            .multiply(franceProperty.getBasePrice()));

            remainingElectricity = remainingElectricity.subtract(remainingElectricity.min(BILLION));
            franceProperty.setBasePrice(franceProperty.getBasePrice()
                    .multiply(BigDecimal.ONE.subtract(franceProperty.getDiscountRate())));
        }
        return totalIncome.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String getCountry() {
        return franceProperty.getCountryName();
    }

    @Override
    public String getCurrency() {
        return franceProperty.getCurrency();
    }
}
