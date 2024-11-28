package com.javaacademy.nuclearstation.unit;


import com.javaacademy.nuclearstation.departments.ReactorDepartment;
import com.javaacademy.nuclearstation.departments.SecurityDepartment;
import com.javaacademy.nuclearstation.exceptions.NuclearFuelIsEmptyException;
import com.javaacademy.nuclearstation.exceptions.ReactorWorkException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("morocco")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ReactorDepartmentTest {


    @Autowired
    private ReactorDepartment reactorDepartment;
    @MockBean
    private SecurityDepartment securityDepartment;

    private static final int MAX_DAYS_WORKING = 100;
    private static final int MIN_DAYS_WORKING = 1;

    @Test
    @DisplayName("Выбрасывает исключение NuclearFuelIsEmptyException")
    public void doNuclearFuelIsEmptyException()
            throws NuclearFuelIsEmptyException {
        for (int i = MIN_DAYS_WORKING; i < MAX_DAYS_WORKING; i++) {
            reactorDepartment.run();
            reactorDepartment.stop();
        }
        NuclearFuelIsEmptyException exception = assertThrows(NuclearFuelIsEmptyException.class,
                () -> {
                    reactorDepartment.run();
                });
    }

    @Test
    @DisplayName("Выбрасывает исключение ReactorWorkException")
    public void doReactorWorkException() throws ReactorWorkException {
        reactorDepartment.run();
        ReactorWorkException exception = assertThrows(ReactorWorkException.class, () -> {
            reactorDepartment.run();
        });
        assertEquals("Реактор уже работает", exception.getMessage());
    }

    @Test
    @DisplayName("При первом вызове stop(), выбрасывается исключение")
    public void doThrowsReactorWorkException() {
        ReactorWorkException exception = assertThrows(ReactorWorkException.class, () -> {
            reactorDepartment.stop();
        });
        assertEquals("Реактор уже выключен", exception.getMessage());
    }

    @Test
    @DisplayName("При повторном вызове stop(), выбрасывается исключение")
    public void doReactorWorkExceptionForSecondTime()
            throws ReactorWorkException {
        reactorDepartment.run();
        reactorDepartment.stop();

        ReactorWorkException exception = assertThrows(ReactorWorkException.class, () -> {
            reactorDepartment.stop();
        });
        assertEquals("Реактор уже выключен", exception.getMessage());
    }
}
