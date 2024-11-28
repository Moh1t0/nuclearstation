package com.javaacademy.nuclearstation.unit;

import com.javaacademy.nuclearstation.config.marocco.MoroccoEconomicDepartment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.math.RoundingMode;

@SpringBootTest
@ActiveProfiles("morocco")

public class MoroccoEconomicDepartmentTest {
    @Autowired
    private MoroccoEconomicDepartment moroccoEconomicDepartment;

    private static final long DEFAULT_EXPECTED_ELECTRICITY = 4_000_000;
    private static final int BASE_PRICE = 5;



    @Test
    @DisplayName("Расчет энергии до 5 млн")
    public void energy() {
        BigDecimal expected = BigDecimal.valueOf(DEFAULT_EXPECTED_ELECTRICITY)
                .multiply(BigDecimal.valueOf(BASE_PRICE)).setScale(2, RoundingMode.HALF_EVEN);

        BigDecimal result = moroccoEconomicDepartment.computeYearIncomes(DEFAULT_EXPECTED_ELECTRICITY);
        Assertions.assertEquals(expected, result.setScale(2));
    }
}
