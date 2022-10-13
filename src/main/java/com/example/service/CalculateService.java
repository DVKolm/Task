package com.example.service;

import com.example.model.DateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CalculateService {

    private final com.example.service.InitHolidays initHolidays;
    private static final double averageNumberOfCalendarDays = 29.3;

    public Double calculateVacationPay(double avgSalaryPerYear, int vacationDays) {
        validateInputParameters(avgSalaryPerYear, vacationDays);
        return new BigDecimal((avgSalaryPerYear / averageNumberOfCalendarDays) * vacationDays)
                .setScale(2, RoundingMode.DOWN)
                .doubleValue();
    }

    public Double holidayPayCalculation(double salary, DateDto dates) {

        int paidVacationDays = 0;

        for (LocalDate date : dates.getDates()) {
            if (!initHolidays.getHolidayDates().contains(date)) {
                paidVacationDays++;
            }
        }
        return calculateVacationPay(salary, paidVacationDays);
    }

    private void validateInputParameters(double salary, int vacationDays) {
        if (salary <= 0) {
            throw new IllegalArgumentException("Salary must be greater than 0");
        }

        if (vacationDays <= 0) {
            throw new IllegalArgumentException("Vacation days must be greater than 0");
        } else if (vacationDays > 28) {
            throw new IllegalArgumentException("The number of vacation days must not exceed 28");
        }
    }


}
