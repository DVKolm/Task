package com.example.controller;

import com.example.model.DateDto;
import com.example.service.VacationPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/calculate")
public class DateCalculateController {

    private final VacationPayService vacationPayService;

    public DateCalculateController(VacationPayService vacationPayService) {
        this.vacationPayService = vacationPayService;
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Double vacationPayCalculation(@RequestParam("salary") double avgSalaryPerYear,
                                         @RequestParam("days") int vacationDays) {

        log.info("Received a request to calculate the amount of vacation pay");
        return vacationPayService.calculateVacationPay(avgSalaryPerYear, vacationDays);
    }

    @GetMapping("/holidays")
    @ResponseStatus(HttpStatus.OK)
    public Double holidayPayCalculation(@RequestParam("salary") double avgSalaryPerYear,
                                        @RequestBody DateDto dates) {
        log.info("Vacation pay is calculated taking into account holidays");
        return vacationPayService.holidayPayCalculation(avgSalaryPerYear, dates);
    }

}
