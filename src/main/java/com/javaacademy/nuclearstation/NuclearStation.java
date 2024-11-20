package com.javaacademy.nuclearstation;


import com.javaacademy.nuclearstation.departments.EconomicDepartment;
import com.javaacademy.nuclearstation.departments.ReactorDepartment;
import com.javaacademy.nuclearstation.departments.SecurityDepartment;
import com.javaacademy.nuclearstation.exceptions.NuclearFuelIsEmptyException;
import com.javaacademy.nuclearstation.exceptions.ReactorWorkException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

/**
 * атомная станция
 */

@Component
@Data
@Slf4j
@RequiredArgsConstructor
public class NuclearStation {

    private static final int DAYS_IN_YEARS = 365;
    private static final int ONE = 1;

    private BigDecimal totalQuantityOfEnergy = BigDecimal.ZERO;
    private int accidentCountAllTime;
    private BigDecimal totalIncome = BigDecimal.ZERO;

    private final EconomicDepartment economicDepartment;
    private final ReactorDepartment reactorDepartment;
    private final SecurityDepartment securityDepartment;

    public void startYear() {
        log.info("Атомная станция начала работу");
        BigDecimal yearlyEnergy = BigDecimal.ZERO;

        for (int day = ONE; day <= DAYS_IN_YEARS; day++) {
            try {
                yearlyEnergy = yearlyEnergy.add(reactorDepartment.run());
                reactorDepartment.stop();
            } catch (NuclearFuelIsEmptyException | ReactorWorkException e) {
                if (e instanceof NuclearFuelIsEmptyException) {
                    log.warn("День {}: Авария! Реактор остановлен, слишком много топлива.", day);
                } else {
                    log.warn("День {}: Авария! Ошибка работы реактора.", day);
                    securityDepartment.addAccident();
                }
            }
        }
        totalQuantityOfEnergy = totalQuantityOfEnergy.add(yearlyEnergy);

        BigDecimal yearlyIncome = economicDepartment.computeYearIncomes(yearlyEnergy.longValue());
        totalIncome = totalIncome.add(yearlyIncome);

        log.info("Доход за год составил {} {}", yearlyIncome, economicDepartment.getCurrency());
        log.info("Атомная станция закончила работу. За год выработано {} киловатт/часов.", yearlyEnergy);
        log.info("Количество инцидентов за год: {}", securityDepartment.getAccidentCountPeriod());
        securityDepartment.reset();
    }

        public void start(int years) {
            for (int year = 0; year < years; year++) {
                log.info("Действие происходит в стране: {} ", economicDepartment.getCountry());
                log.info("Начало работы");
                startYear();
            }
            log.info("Работа завершена за {} года/лет. Общее количество энергии: {} киловатт/часов.",
                    years, totalQuantityOfEnergy);
            log.info("Всего инцидентов: {}", accidentCountAllTime);
            log.info("Всего заработанно {} {} ", totalIncome, economicDepartment.getCurrency());
        }

        public void incrementAccident(int count) {
           this.accidentCountAllTime += count;
        }
}
