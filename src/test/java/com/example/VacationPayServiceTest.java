package com.example;

import com.example.model.DateDto;
import com.example.service.VacationPayService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class VacationPayServiceTest {

    @Autowired
    private VacationPayService vacationPayService;

    @Test
    public void when_calculateVacationPay_then_OK() {
        Assertions.assertEquals(15358.36, vacationPayService.calculateVacationPay(30000, 15));
    }

    @Test
    public void when_calculateVacationPayWithHolidays_then_OK() {
        Set<LocalDate> vacationDays = new HashSet<>();
        vacationDays.add(LocalDate.of(2023, 1, 9));
        vacationDays.add(LocalDate.of(2023, 1, 2));
        DateDto dates = new DateDto(vacationDays);
        Assertions.assertEquals(1023.89, vacationPayService.holidayPayCalculation(30000, dates));
    }

    @Test
    public void when_ageIsLessThanOrEqualToZero_then_throwException() {
        assertThrows(IllegalArgumentException.class, () -> vacationPayService.calculateVacationPay(0, 0));
    }
}