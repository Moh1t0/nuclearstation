package com.javaacademy.nuclearstation.config.marocco;

import com.javaacademy.nuclearstation.departments.EconomicDepartment;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Profile("morocco")
@RequiredArgsConstructor
@EnableConfigurationProperties(value = MoroccoProperty.class)
public class MoroccoEconomicDepartment extends EconomicDepartment {

    private final MoroccoProperty moroccoProperty;

    @Override
    public BigDecimal computeYearIncomes(long countElectricity) {
        BigDecimal totalIncome = BigDecimal.ZERO;
        BigDecimal electricity = BigDecimal.valueOf(countElectricity);

        if (electricity.compareTo(moroccoProperty.getThreshold()) > 0) {
            BigDecimal extraElectricity = electricity.subtract(moroccoProperty.getThreshold());
            totalIncome = moroccoProperty.getThreshold().multiply(moroccoProperty.getBasePrice());

            totalIncome = totalIncome.add(extraElectricity.multiply(moroccoProperty.getIncreasedPrice()));
        } else {
            totalIncome = electricity.multiply(moroccoProperty.getBasePrice());
        }
        return totalIncome;
    }

    @Override
    public String getCurrency() {
        return moroccoProperty.getCurrency();
    }

    @Override
    public String getCountry() {
        return moroccoProperty.getCountryName();
    }
}
