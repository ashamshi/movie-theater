package com.jpmc.theater.model;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.jpmc.theater.model.Money.usd;
import static org.junit.jupiter.api.Assertions.*;

class MovieTest {
  Movie target = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), usd(12.5), 1);

  @Test
  void shouldBeEqualWhenObjectsEqual() {
    assertEquals(new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), usd(12.5), 1), target);
  }

  @Test
  void shouldBeNotEqualWhenObjectsAreNotEqual() {
    assertNotEquals(new Movie("Spider-Man: Way Home", Duration.ofMinutes(90), usd(12.5), 1), target);
    assertNotEquals(new Movie("Spider-Man: No Way Home", Duration.ofMinutes(91),usd(12.5), 1), target);
    assertNotEquals(new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),usd(12.6), 1), target);
    assertNotEquals(new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),usd(12.5), 2), target);
    assertNotEquals(new Movie(null, Duration.ofMinutes(90),usd(12.5), 1), target);
    assertNotEquals(new Movie("Spider-Man: No Way Home", null,usd(12.5), 1), target);
    assertNotEquals(new Movie(null, null,usd(12.5), 1), target);
    assertNotEquals(new Object(), target);
    assertNotEquals(null, target);
  }

  @Test
  void shouldCalculateSameHashCode() {
    assertEquals(new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), usd(12.5), 1).hashCode(), target.hashCode());
  }

  @Test
  void shouldCalculateDifferentHashCode() {
    assertNotEquals(new Movie("Spider-Man: Way Home", Duration.ofMinutes(90), usd(12.5), 1).hashCode(), target.hashCode());
    assertNotEquals(new Movie("Spider-Man: No Way Home", Duration.ofMinutes(91), usd(12.5), 1).hashCode(), target.hashCode());
    assertNotEquals(new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), usd(12.6), 1).hashCode(), target.hashCode());
    assertNotEquals(new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), usd(12.5), 2).hashCode(), target.hashCode());
    assertNotEquals(new Movie(null, Duration.ofMinutes(90), usd(12.5), 1).hashCode(), target.hashCode());
    assertNotEquals(new Movie("Spider-Man: No Way Home", null, usd(12.5), 1).hashCode(), target.hashCode());
    assertNotEquals(new Movie(null, null, usd(12.5), 1).hashCode(), target.hashCode());
  }

  @Test
  void shouldGetTitleName() {
    assertEquals("Spider-Man: No Way Home", target.getTitle());
  }

  @Test
  void shouldGetRunningTime() {
    assertEquals(Duration.ofMinutes(90), target.getRunningTime());
  }

  @Test
  void shouldGetTicketPrice() {
    assertEquals(usd(12.5), target.getTicketPrice());
  }

  @Test
  void shouldGetSpecialCode() {
    assertEquals(1, target.getSpecialCode());
  }
}