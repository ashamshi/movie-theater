package com.jpmc.theater.service.discount.rule;

import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class AmountDiscountRuleTest {
  Movie movie = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);

  @Test
  void shouldGetDiscountAmountIfRuleMatches() {
    // Given
    AmountDiscountRule target = new AmountDiscountRule(Objects::nonNull, 2.5);
    Showing showing = new Showing(movie, 1, LocalDateTime.MIN);
    // When
    Double result = target.apply(showing);
    // Then
    assertEquals(2.5, result);
  }

  @Test
  void shouldGetDiscountAmountZeroIfRuleDoesNotMatch() {
    // Given
    AmountDiscountRule target = new AmountDiscountRule(showing -> showing.getSequenceOfTheDay() == 1, 2.5);
    Showing showing = new Showing(movie, 2, LocalDateTime.MIN);
    // When
    Double result = target.apply(showing);
    // Then
    assertEquals(0.0, result);
  }
}
