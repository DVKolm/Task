package com.example.controller;

import com.example.model.DateDto;
import com.example.service.DataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/calculate")
public class APIController {
    private final DataService dataService;

    public APIController(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Double calculate(@RequestParam("salary") double avgSalaryPerYear,
                            @RequestParam("days") int vacationDays) {

        log.info("Received a request to calculate the amount of vacation pay");
        return dataService.calculate(avgSalaryPerYear, vacationDays);
    }

    @GetMapping("/holidays")
    @ResponseStatus(HttpStatus.OK)
    public Double calculateWithHolidays(@RequestParam("salary") double avgSalaryPerYear,
                                        @RequestBody DateDto dates){
        log.info("Vacation pay is calculated taking into account holidays");
        return dataService.calculateWithHolidays(avgSalaryPerYear, dates);
    }

}
