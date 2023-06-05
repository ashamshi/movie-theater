package com.jpmc.theater.service.print;

import com.jpmc.theater.configuration.ScheduleConfiguration;
import com.jpmc.theater.model.Schedule;
import com.jpmc.theater.service.print.format.DurationFormatter;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SimpleTextPrintServiceTest {
  DurationFormatter durationFormatter = new DurationFormatter();

  @Test
  void shouldPrintMovieScheduleStaticData() {
    // Given
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    Schedule schedule = new ScheduleConfiguration(() -> LocalDate.of(2023, 6, 3)).schedule();
    PrintService target = new SimpleTextPrintService(durationFormatter, new PrintStream(outputStream));
    // When
    target.print(schedule);
    // Then
    assertEquals("2023-06-03\n" +
      "===================================================\n" +
      "1: 2023-06-03T09:00 Turning Red (1 hour 25 minutes) $11\n" +
      "2: 2023-06-03T11:00 Spider-Man: No Way Home (1 hour 30 minutes) $12.5\n" +
      "3: 2023-06-03T12:50 The Batman (1 hour 35 minutes) $9\n" +
      "4: 2023-06-03T14:30 Turning Red (1 hour 25 minutes) $11\n" +
      "5: 2023-06-03T16:10 Spider-Man: No Way Home (1 hour 30 minutes) $12.5\n" +
      "6: 2023-06-03T17:50 The Batman (1 hour 35 minutes) $9\n" +
      "7: 2023-06-03T19:30 Turning Red (1 hour 25 minutes) $11\n" +
      "8: 2023-06-03T21:10 Spider-Man: No Way Home (1 hour 30 minutes) $12.5\n" +
      "9: 2023-06-03T23:00 The Batman (1 hour 35 minutes) $9\n" +
      "===================================================\n", outputStream.toString());
  }
}
