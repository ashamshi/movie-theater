package com.jpmc.theater.service.discount;

import com.jpmc.theater.configuration.DiscountConfiguration;
import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiscountServiceTest {
  DiscountService target = new DiscountService(new DiscountConfiguration().discountRules());

  @Test
  void shouldNotApplyDiscountWhenSpecialCodeDoesNotMatch() {
    // Given
    Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 0);
    Showing showing = new Showing(spiderMan, 5, LocalDateTime.now());
    // When & Then
    assertEquals(12.5, target.calculateTicketPrice(showing));
  }

  @Test
  void shouldApplyDiscountWhenSpecialCodeMatches() {
    // Given
    Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
    Showing showing = new Showing(spiderMan, 5, LocalDateTime.now());
    // When & Then
    assertEquals(10, target.calculateTicketPrice(showing));
  }

  @Test
  void shouldApplyDiscountWhenFirstSequence() {
    // Given
    Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 0);
    Showing showing = new Showing(spiderMan, 1, LocalDateTime.now());
    // When & Then
    assertEquals(9.5, target.calculateTicketPrice(showing));
  }

  @Test
  void shouldApplyDiscountWhenSecondSequence() {
    // Given
    Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 0);
    Showing showing = new Showing(spiderMan, 2, LocalDateTime.now());
    // When & Then
    assertEquals(10.5, target.calculateTicketPrice(showing));
  }

  @Test
  void shouldApplyMaxDiscountWhenFirstSequenceAndSpecialCodeMatches() {
    // Given
    Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
    Showing showing = new Showing(spiderMan, 1, LocalDateTime.now());
    // When & Then
    assertEquals(9.5, target.calculateTicketPrice(showing));
  }
}
