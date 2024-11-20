package com.javaacademy.nuclearstation.departments;

import com.javaacademy.nuclearstation.exceptions.NuclearFuelIsEmptyException;
import com.javaacademy.nuclearstation.exceptions.ReactorWorkException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

/**
 * реакторный цех
*/

@Component
@Data
@Slf4j
@RequiredArgsConstructor
public class ReactorDepartment {

    private static final BigDecimal KILOWATT = BigDecimal.valueOf(10_000_000);
    private static final int COUNT_OF_WORK_DAYS = 100;
    private boolean isWork;
    private int runCount;
    private final SecurityDepartment securityDepartment;


    public BigDecimal run() {
        if (isWork) {
            securityDepartment.addAccident();
            throw new ReactorWorkException("Реактор уже работает");
        }
        runCount++;
        if (runCount % COUNT_OF_WORK_DAYS == 0) {
            securityDepartment.addAccident();
            throw new NuclearFuelIsEmptyException("Реактор заполен!");
        }
        setWork(true);
        return KILOWATT;
    }

    public void stop() {
        if (isWork()) {
            setWork(false);
        } else {
            throw new ReactorWorkException("Реактор уже выключен");
        }
    }
}
