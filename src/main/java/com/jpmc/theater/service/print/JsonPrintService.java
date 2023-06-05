package com.jpmc.theater.service.print;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpmc.theater.model.Schedule;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;

import java.io.PrintStream;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JsonPrintService implements PrintService {
  ObjectMapper objectMapper;
  PrintStream out;

  @Override
  @SneakyThrows
  public void print(Schedule schedule) {
    objectMapper.writeValue(out, schedule);
  }
}
