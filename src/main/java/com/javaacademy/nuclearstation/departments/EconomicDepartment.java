package com.javaacademy.nuclearstation.departments;

import java.math.BigDecimal;

public abstract class EconomicDepartment {


    public abstract BigDecimal computeYearIncomes(long countElectricity);


    public abstract String getCountry();

    public abstract String getCurrency();
}
