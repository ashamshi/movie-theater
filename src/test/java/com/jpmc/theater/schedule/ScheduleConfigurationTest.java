package com.jpmc.theater.schedule;

import com.jpmc.theater.configuration.ScheduleConfiguration;
import com.jpmc.theater.model.Schedule;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ScheduleConfigurationTest {

  @Test
  void shouldGetShowingsStartTimeAndDateSetFromLocalDateProvider() {
    // Given
    LocalDate date = LocalDate.of(2023, 6, 3);
    ScheduleConfiguration scheduleConfiguration = new ScheduleConfiguration(() -> date);
    // When
    Schedule schedule = scheduleConfiguration.schedule();
    // Then
    assertFalse(schedule.getShowings().isEmpty());
    schedule.getShowings().forEach(showing -> assertEquals(date, showing.getShowStartTime().toLocalDate()));
    assertEquals(date, schedule.getDate());
  }
}
