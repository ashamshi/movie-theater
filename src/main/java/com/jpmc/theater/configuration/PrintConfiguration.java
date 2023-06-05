package com.jpmc.theater.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.jpmc.theater.service.print.JsonPrintService;
import com.jpmc.theater.service.print.PrintService;
import com.jpmc.theater.service.print.SimpleTextPrintService;
import com.jpmc.theater.service.print.format.DurationFormatter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class PrintConfiguration {
  PrintStream out = System.out;
  boolean printToJson = false;

  PrintConfiguration withOutputStream(PrintStream out) {
    return new PrintConfiguration(out, printToJson);
  }

  PrintConfiguration withPrintToJson(boolean printToJson) {
    return new PrintConfiguration(this.out, printToJson);
  }

  PrintService printService() {
    if (printToJson) {
      ObjectMapper objectMapper = new ObjectMapper();
      objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
      JavaTimeModule javaTimeModule = new JavaTimeModule();
      javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ISO_DATE_TIME));
      objectMapper.registerModule(javaTimeModule);
      return new JsonPrintService(objectMapper, new PrintStream(out));
    } else {
      DurationFormatter durationFormatter = new DurationFormatter();
      return new SimpleTextPrintService(durationFormatter, out);
    }
  }
}
