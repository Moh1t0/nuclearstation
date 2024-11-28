package com.javaacademy.nuclearstation;

import com.javaacademy.nuclearstation.departments.EconomicDepartment;
import com.javaacademy.nuclearstation.departments.ReactorDepartment;
import com.javaacademy.nuclearstation.departments.SecurityDepartment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.math.RoundingMode;

@SpringBootTest
@ActiveProfiles("france")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class NuclearStationTest {

    private static final long TOTAL_ENERGY_IN_THREE_YEARS = 10_850_000_000L;
    private static final int START_YEARS = 3;
    private static final int EXPECTED_COUNT = 10;

    @Autowired
    private SecurityDepartment securityDepartment;
    @Autowired
    private NuclearStation nuclearStation;
    @Autowired
    private ReactorDepartment reactorDepartment;
    @Autowired
    private EconomicDepartment economicDepartment;


    @Test
    @DisplayName("Расчет количества выработанной энергии и количества инцидентов за 3 года")
    public void startYearSuccess() {
        BigDecimal expectedEnergy = BigDecimal.ZERO;
        nuclearStation.start(START_YEARS);
        BigDecimal resultTotalEnergyGenerated = nuclearStation.getTotalQuantityOfEnergy().
                setScale(2, RoundingMode.HALF_EVEN);
        expectedEnergy = BigDecimal.valueOf(TOTAL_ENERGY_IN_THREE_YEARS)
                .setScale(2, RoundingMode.HALF_EVEN);

        int resultAccidentCountAllTime = nuclearStation.getAccidentCountAllTime();
        int expectedCount = EXPECTED_COUNT;

        Assertions.assertEquals(expectedEnergy, resultTotalEnergyGenerated);
        Assertions.assertEquals(expectedCount, resultAccidentCountAllTime);
    }

    @Test
    @DisplayName("Работа счетчика Аварий")
    void successIncrementAccident() {
        nuclearStation.incrementAccident(1);
        int result = nuclearStation.getAccidentCountAllTime();
        int expected = 1;
        Assertions.assertEquals(expected, result);
    }
}
