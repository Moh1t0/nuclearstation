package com.javaacademy.nuclearstation;

import com.javaacademy.nuclearstation.departments.SecurityDepartment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("morocco")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class SecurityDepartmentTest {
    @Autowired
    private SecurityDepartment securityDepartment;

    @Test
    @DisplayName("Работа счетчика")
    public void addAccidentSuccess() {
        securityDepartment.addAccident();
        int result = securityDepartment.getCountAccidents();
        int expected = 1;
        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("Обнуление счетчика")
    public void resetSuccess() {
        securityDepartment.addAccident();
        securityDepartment.reset();
        int result = securityDepartment.getCountAccidents();
        int expected = 0;
        Assertions.assertEquals(expected, result);
    }
}
