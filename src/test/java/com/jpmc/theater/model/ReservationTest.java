package com.jpmc.theater.model;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ReservationTest {
  Customer customer = new Customer("John Doe", "unused-id");
  Showing showing = new Showing(
    new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1),
    1,
    LocalDateTime.now()
  );
  Reservation target = new Reservation(customer, showing, 3, 37.5);
  
  @Test
  void shouldBeEqualWhenObjectsEqual() {
    assertEquals(new Reservation(customer, showing, 3, 37.5), target);
  }

  @Test
  void shouldBeEqualWhenObjectsEqualBuiltWithBuilder() {
    assertEquals(Reservation.builder()
      .customer(customer)
      .showing(showing)
      .audienceCount(3)
      .totalFee(37.5)
      .build(), target);
  }

  @Test
  void shouldReturnStringRepresentationOfReservationBuilder() {
    assertEquals("Reservation.ReservationBuilder(customer=null, showing=null, audienceCount=0, totalFee=0.0)", Reservation.builder().toString());
  }

  @Test
  void shouldBeNotEqualWhenObjectsAreNotEqual() {
    assertNotEquals(new Reservation(customer, null, 3, 37.5), target);
    assertNotEquals(new Reservation(null, showing, 3, 37.5), target);
    assertNotEquals(new Reservation(null, null, 3, 37.5), target);
    assertNotEquals(new Reservation(customer, showing, 4, 37.5), target);
    assertNotEquals(new Reservation(customer, showing, 3, 37.6), target);
    assertNotEquals(new Object(), target);
    assertNotEquals(null, target);
  }

  @Test
  void shouldCalculateSameHashCode() {
    assertEquals(new Reservation(customer, showing, 3, 37.5).hashCode(), target.hashCode());
  }

  @Test
  void shouldCalculateDifferentHashCode() {
    assertNotEquals(new Reservation(customer, null, 3, 37.5).hashCode(), target.hashCode());
    assertNotEquals(new Reservation(null, showing, 3, 37.5).hashCode(), target.hashCode());
    assertNotEquals(new Reservation(null, null, 3, 37.5).hashCode(), target.hashCode());
    assertNotEquals(new Reservation(customer, showing, 4, 37.5).hashCode(), target.hashCode());
    assertNotEquals(new Reservation(customer, showing, 3, 37.6).hashCode(), target.hashCode());
  }

  @Test
  void shouldGetCustomer() {
    assertEquals(customer, target.getCustomer());
  }

  @Test
  void shouldGetShowing() {
    assertEquals(showing, target.getShowing());
  }

  @Test
  void shouldGetAudienceCount() {
    assertEquals(3, target.getAudienceCount());
  }

  @Test
  void shouldGetTotalFee() {
    assertEquals(37.5, target.getTotalFee());
  }
}
