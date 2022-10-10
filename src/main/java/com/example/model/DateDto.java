package com.example.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@JsonDeserialize(using = DateDto.DateDtoDeserializer.class)
public class DateDto {

    private Set<LocalDate> dates;

    static class DateDtoDeserializer extends JsonDeserializer<DateDto> {

        @SneakyThrows
        @Override
        public DateDto deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            JsonNode treeNode = jsonParser.getCodec().readTree(jsonParser);
            String textValue = treeNode.get("dates").toPrettyString();
            String [] dates = textValue
                    .replaceAll("\\[", "")
                    .replaceAll(" \"", "")
                    .replaceAll("\"", "")
                    .replaceAll("\\]", "")
                    .split(",");
            return new DateDto(Arrays.stream(dates).map(String::trim).map(str -> LocalDate.parse(str)).collect(Collectors.toSet()));
        }
    }

}
