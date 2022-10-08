package com.example.service;

import com.example.model.DateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DataService {

    private final InitHolidays initHolidays;
    private static final double averageNumberOfCalendarDays = 29.3;

    public Double calculate(double avgSalaryPerYear, int vacationDays) {
        validateInputParameters(avgSalaryPerYear, vacationDays);
        return new BigDecimal((avgSalaryPerYear / averageNumberOfCalendarDays) * vacationDays)
                .setScale(2, RoundingMode.DOWN)
                .doubleValue();
    }

    private void validateInputParameters(double salary, int vacationDays) {
        if (salary <= 0) {
            throw new IllegalArgumentException("Salary must be greater than 0");
        }

        if (vacationDays <= 0) {
            throw new IllegalArgumentException("Vacation days must be greater than 0");
        }
    }

    public Double calculateWithHolidays(double Salary, DateDto date) {

        int paidVacationDays = 0;

        for (LocalDate dates : date.getDates()) {
            if (!initHolidays.getHolidaysDate().contains(dates)) {
                paidVacationDays++;
            }
        }

        return new BigDecimal((Salary / averageNumberOfCalendarDays) * paidVacationDays)
                .setScale(2, RoundingMode.DOWN)
                .doubleValue();
    }


}
