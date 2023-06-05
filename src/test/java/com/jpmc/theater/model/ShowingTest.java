package com.jpmc.theater.model;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static com.jpmc.theater.model.Money.usd;
import static org.junit.jupiter.api.Assertions.*;

class ShowingTest {
  LocalDateTime localDateTime = LocalDateTime.now();
  Movie movie = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), usd(12.5), 1);
  Showing target = new Showing(movie,1, localDateTime);
  @Test
  void shouldBeEqualWhenObjectsEqual() {
    assertEquals(new Showing(movie,1, localDateTime), target);
  }

  @Test
  void shouldBeNotEqualWhenObjectsAreNotEqual() {
    assertNotEquals(new Showing(null,1, localDateTime), target);
    assertNotEquals(new Showing(movie,0, localDateTime), target);
    assertNotEquals(new Showing(movie,1, localDateTime.minusDays(1)), target);
    assertNotEquals(new Showing(movie,1, null), target);
    assertNotEquals(new Showing(null,1, null), target);
    assertNotEquals(new Object(), target);
    assertNotEquals(null, target);
  }

  @Test
  void shouldCalculateSameHashCode() {
    assertEquals(new Showing(movie,1, localDateTime).hashCode(), target.hashCode());
  }

  @Test
  void shouldCalculateDifferentHashCode() {
    assertNotEquals(new Showing(null,1, localDateTime).hashCode(), target.hashCode());
    assertNotEquals(new Showing(movie,0, localDateTime).hashCode(), target.hashCode());
    assertNotEquals(new Showing(movie,1, localDateTime.minusDays(1)).hashCode(), target.hashCode());
    assertNotEquals(new Showing(movie,1, null).hashCode(), target.hashCode());
    assertNotEquals(new Showing(null,1, null).hashCode(), target.hashCode());
  }

  @Test
  void shouldGetMovieName() {
    assertEquals(movie, target.getMovie());
  }

  @Test
  void shouldGetSequenceOfTheDay() {
    assertEquals(1, target.getSequenceOfTheDay());
  }

  @Test
  void shouldGetShowStartTime() {
    assertEquals(localDateTime, target.getShowStartTime());
  }
}
