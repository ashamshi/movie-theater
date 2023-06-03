package com.jpmc.theater.service.print.format;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class DurationFormatterTest {
  DurationFormatter target = new DurationFormatter();

  @Test
  void shouldConvertToHumanReadableFormatWithZeroHoursAndOneMinute() {
    // Given
    Duration duration = Duration.ofMinutes(1);
    // When & Then
    assertEquals("(0 hours 1 minute)", target.toHumanReadableFormat(duration));
  }

  @Test
  void shouldConvertToHumanReadableFormatWithOneHourAndZeroMinutes() {
    // Given
    Duration duration = Duration.ofMinutes(60);
    // When & Then
    assertEquals("(1 hour 0 minutes)", target.toHumanReadableFormat(duration));
  }

  @Test
  void shouldConvertToHumanReadableFormatWithPluralHoursAndZeroMinutes() {
    // Given
    Duration duration = Duration.ofMinutes(120);
    // When & Then
    assertEquals("(2 hours 0 minutes)", target.toHumanReadableFormat(duration));
  }

  @Test
  void shouldConvertToHumanReadableFormatWithZeroHoursAndPluralMinutes() {
    // Given
    Duration duration = Duration.ofMinutes(2);
    // When & Then
    assertEquals("(0 hours 2 minutes)", target.toHumanReadableFormat(duration));
  }

  @Test
  void shouldConvertToHumanReadableFormatWithOneHourAndOneMinute() {
    // Given
    Duration duration = Duration.ofMinutes(61);
    // When & Then
    assertEquals("(1 hour 1 minute)", target.toHumanReadableFormat(duration));
  }

  @Test
  void shouldConvertToHumanReadableFormatWithOneHourAndPluralMinutes() {
    // Given
    Duration duration = Duration.ofMinutes(62);
    // When & Then
    assertEquals("(1 hour 2 minutes)", target.toHumanReadableFormat(duration));
  }

  @Test
  void shouldConvertToHumanReadableFormatWithPluralHoursAndPluralMinutes() {
    // Given
    Duration duration = Duration.ofMinutes(122);
    // When & Then
    assertEquals("(2 hours 2 minutes)", target.toHumanReadableFormat(duration));
  }
}
