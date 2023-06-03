package com.jpmc.theater.schedule;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class StaticScheduleTest {

  @Test
  void shouldGetShowingsStartTimeAndDateSetFromLocalDateProvider() {
    // Given & When
    LocalDate date = LocalDate.of(2023, 6, 3);
    Schedule schedule = new StaticSchedule(() -> date);
    // Then
    assertFalse(schedule.getShowings().isEmpty());
    schedule.getShowings().forEach(showing -> assertEquals(date, showing.getStartTime().toLocalDate()));
    assertEquals(date, schedule.getDate());
  }
}
