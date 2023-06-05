package com.jpmc.theater.service.print;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.jpmc.theater.configuration.ScheduleConfiguration;
import com.jpmc.theater.model.Schedule;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonPrintServiceTest {

  @Test
  void shouldPrintToJsonFormat() {
    // Given
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    Schedule schedule = new ScheduleConfiguration(() -> LocalDate.of(2023, 6, 3)).schedule();
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    JavaTimeModule javaTimeModule = new JavaTimeModule();
    javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ISO_DATE_TIME));
    objectMapper.registerModule(javaTimeModule);
    JsonPrintService target = new JsonPrintService(objectMapper, new PrintStream(outputStream));
    // When
    target.print(schedule);
    // Then
    assertEquals("{\"date\":\"2023-06-03\",\"showings\":[{\"movie\":{\"title\":\"Turning Red\",\"runningTime\":5100.000000000,\"ticketPrice\":{\"currency\":\"USD\",\"amount\":11},\"specialCode\":0},\"sequenceOfTheDay\":1,\"showStartTime\":\"2023-06-03T09:00:00\"},{\"movie\":{\"title\":\"Spider-Man: No Way Home\",\"runningTime\":5400.000000000,\"ticketPrice\":{\"currency\":\"USD\",\"amount\":12.5},\"specialCode\":1},\"sequenceOfTheDay\":2,\"showStartTime\":\"2023-06-03T11:00:00\"},{\"movie\":{\"title\":\"The Batman\",\"runningTime\":5700.000000000,\"ticketPrice\":{\"currency\":\"USD\",\"amount\":9},\"specialCode\":0},\"sequenceOfTheDay\":3,\"showStartTime\":\"2023-06-03T12:50:00\"},{\"movie\":{\"title\":\"Turning Red\",\"runningTime\":5100.000000000,\"ticketPrice\":{\"currency\":\"USD\",\"amount\":11},\"specialCode\":0},\"sequenceOfTheDay\":4,\"showStartTime\":\"2023-06-03T14:30:00\"},{\"movie\":{\"title\":\"Spider-Man: No Way Home\",\"runningTime\":5400.000000000,\"ticketPrice\":{\"currency\":\"USD\",\"amount\":12.5},\"specialCode\":1},\"sequenceOfTheDay\":5,\"showStartTime\":\"2023-06-03T16:10:00\"},{\"movie\":{\"title\":\"The Batman\",\"runningTime\":5700.000000000,\"ticketPrice\":{\"currency\":\"USD\",\"amount\":9},\"specialCode\":0},\"sequenceOfTheDay\":6,\"showStartTime\":\"2023-06-03T17:50:00\"},{\"movie\":{\"title\":\"Turning Red\",\"runningTime\":5100.000000000,\"ticketPrice\":{\"currency\":\"USD\",\"amount\":11},\"specialCode\":0},\"sequenceOfTheDay\":7,\"showStartTime\":\"2023-06-03T19:30:00\"},{\"movie\":{\"title\":\"Spider-Man: No Way Home\",\"runningTime\":5400.000000000,\"ticketPrice\":{\"currency\":\"USD\",\"amount\":12.5},\"specialCode\":1},\"sequenceOfTheDay\":8,\"showStartTime\":\"2023-06-03T21:10:00\"},{\"movie\":{\"title\":\"The Batman\",\"runningTime\":5700.000000000,\"ticketPrice\":{\"currency\":\"USD\",\"amount\":9},\"specialCode\":0},\"sequenceOfTheDay\":9,\"showStartTime\":\"2023-06-03T23:00:00\"}]}",
      outputStream.toString());
  }
}
