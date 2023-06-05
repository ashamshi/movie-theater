package com.jpmc.theater.configuration;

import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.service.discount.rule.DiscountRule;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiscountConfigurationTest {
  List<DiscountRule> rules = new DiscountConfiguration().discountRules();
  Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);

  @Test
  void shouldReturnDiscountAmountWhenSpecialCodeMatches() {
    // Given
    Showing showing = new Showing(spiderMan, 2, LocalDateTime.now());
    // When & Then
    assertEquals(2.5, rules.get(0).apply(showing));
  }

  @Test
  void shouldReturnDiscountAmountZeroWhenSpecialCodeDoesNotMatch() {
    // Given
    Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 0);
    Showing showing = new Showing(spiderMan, 2, LocalDateTime.now());
    // When & Then
    assertEquals(0, rules.get(0).apply(showing));
  }

  @Test
  void shouldReturnDiscountAmountWhenFirstSequence() {
    // Given
    Showing showing = new Showing(spiderMan, 1, LocalDateTime.now());
    // When & Then
    assertEquals(3, rules.get(1).apply(showing));
  }

  @Test
  void shouldReturnDiscountAmountZeroWhenNotFirstSequence() {
    // Given
    Showing showing = new Showing(spiderMan, 2, LocalDateTime.now());
    // When & Then
    assertEquals(0, rules.get(1).apply(showing));
  }

  @Test
  void shouldReturnDiscountAmountWhenSecondSequence() {
    // Given
    Showing showing = new Showing(spiderMan, 2, LocalDateTime.now());
    // When & Then
    assertEquals(2, rules.get(2).apply(showing));
  }

  @Test
  void shouldReturnDiscountAmountZeroWhenNotSecondSequence() {
    // Given
    Showing showing = new Showing(spiderMan, 3, LocalDateTime.now());
    // When & Then
    assertEquals(0, rules.get(2).apply(showing));
  }

  @Test
  void shouldReturnDiscountAmountWhenStartTimeBetween11And16() {
    // Given
    Showing showing = new Showing(spiderMan, 2, LocalDateTime.of(LocalDate.now(),
      LocalTime.of(11, 15, 0)));
    // When & Then
    assertEquals(3.125, rules.get(3).apply(showing));
  }

  @Test
  void shouldReturnDiscountAmountZeroWhenStartTimeBefore11() {
    // Given
    Showing showing = new Showing(spiderMan, 2, LocalDateTime.of(LocalDate.now(),
      LocalTime.of(10, 55, 0)));
    // When & Then
    assertEquals(0, rules.get(3).apply(showing));
  }

  @Test
  void shouldReturnDiscountAmountZeroWhenStartTimeAfter16() {
    // Given
    Showing showing = new Showing(spiderMan, 2, LocalDateTime.of(LocalDate.now(),
      LocalTime.of(16, 15, 0)));
    // When & Then
    assertEquals(0, rules.get(3).apply(showing));
  }

  @Test
  void shouldReturnDiscountAmountWhenShowingOn7th() {
    // Given
    Showing showing = new Showing(spiderMan, 2, LocalDateTime.of(LocalDate.of(2023, 6, 7),
      LocalTime.now()));
    // When & Then
    assertEquals(1, rules.get(4).apply(showing));
  }

  @Test
  void shouldReturnDiscountAmountZeroWhenShowingNotOn7th() {
    // Given
    Showing showing = new Showing(spiderMan, 2, LocalDateTime.of(LocalDate.of(2023, 6, 6),
      LocalTime.now()));
    // When & Then
    assertEquals(0, rules.get(4).apply(showing));
  }
}
