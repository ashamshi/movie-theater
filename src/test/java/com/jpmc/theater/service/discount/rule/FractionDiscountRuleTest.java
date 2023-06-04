package com.jpmc.theater.service.discount.rule;

import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class FractionDiscountRuleTest {
  Movie movie = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);

  @Test
  void shouldGetDiscountAmountIfRuleMatches() {
    // Given
    FractionDiscountRule target = new FractionDiscountRule(Objects::nonNull, 0.2);
    Showing showing = new Showing(movie, 1, LocalDateTime.MIN);
    // When
    Double result = target.apply(showing);
    // Then
    assertEquals(2.5, result);
  }

  @Test
  void shouldGetDiscountAmountZeroIfRuleDoesNotMatch() {
    // Given
    FractionDiscountRule target = new FractionDiscountRule(showing -> showing.getSequenceOfTheDay() == 1, 0.2);
    Showing showing = new Showing(movie, 2, LocalDateTime.MIN);
    // When
    Double result = target.apply(showing);
    // Then
    assertEquals(0.0, result);
  }
}