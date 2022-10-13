package com.example.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Configuration
@AllArgsConstructor
public class Properties {

    private Set<LocalDate> holidayDates;

    private final ObjectMapper objectMapper;

    @PostConstruct
    @SneakyThrows
    public void initHolidayDates() {
        String holidays = Files.readString(Path.of("src/main/resources/holidays.json"));
        holidayDates = objectMapper.readValue(holidays, objectMapper.getTypeFactory().constructCollectionType(Set.class, LocalDate.class));
    }

}
