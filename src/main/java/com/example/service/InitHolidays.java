package com.example.service;

import com.example.model.Holidays;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Service
@RequiredArgsConstructor
public class InitHolidays {

    private Set<LocalDate> holidayDates;

    private final ObjectMapper objectMapper;

    @PostConstruct
    @SneakyThrows
    public void initHolidayDates() {
        String holidays = Files.readString(Path.of("src/main/resources/holidays.json"));
        holidayDates = objectMapper.readValue(holidays, Holidays.class).getHolidayDates()
                .stream().map(LocalDate::parse).collect(Collectors.toSet());
    }

}
