package com.jpmc.theater.service;

import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiscountServiceTest {
  DiscountService target = new DiscountService();

  @Test
  void shouldCalculateTicketPrice() {
    // Given
    Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
    Showing showing = new Showing(spiderMan, 5, LocalDateTime.now());
    // When & Then
    assertEquals(10, target.calculateTicketPrice(showing));
  }
}
