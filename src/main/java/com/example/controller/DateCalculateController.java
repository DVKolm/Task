package com.example.controller;

import com.example.model.DateDto;
import com.example.service.CalculateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/calculate")
public class DateCalculateController {

    private final CalculateService calculateService;

    public DateCalculateController(CalculateService calculateService) {
        this.calculateService = calculateService;
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Double calculate(@RequestParam("salary") double avgSalaryPerYear,
                            @RequestParam("days") int vacationDays) {

        log.info("Received a request to calculate the amount of vacation pay");
        return calculateService.calculateVacationPay(avgSalaryPerYear, vacationDays);
    }

    @GetMapping("/holidays")
    @ResponseStatus(HttpStatus.OK)
    public Double calculateWithHolidays(@RequestParam("salary") double avgSalaryPerYear,
                                        @RequestBody DateDto dates) {
        log.info("Vacation pay is calculated taking into account holidays");
        return calculateService.holidayPayCalculation(avgSalaryPerYear, dates);
    }

}
