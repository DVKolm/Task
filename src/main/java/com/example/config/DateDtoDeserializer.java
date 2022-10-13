package com.example.config;

import com.example.model.DateDto;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Collectors;

@Configuration
@JsonDeserialize(using = DateDtoDeserializer.class)
public class DateDtoDeserializer extends JsonDeserializer<DateDto> {

    @SneakyThrows
    @Override
    public DateDto deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonNode treeNode = jsonParser.getCodec().readTree(jsonParser);
        String textValue = treeNode.get("dates").toPrettyString();
        String[] dates = textValue
                .replaceAll("\\[", "")
                .replaceAll(" \"", "")
                .replaceAll("\"", "")
                .replaceAll("]", "")
                .split(",");
        return new DateDto(Arrays.stream(dates).map(String::trim).map(LocalDate::parse).collect(Collectors.toSet()));
    }
}

