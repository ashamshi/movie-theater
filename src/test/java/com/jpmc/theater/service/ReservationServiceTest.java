package com.jpmc.theater.service;

import com.jpmc.theater.model.Customer;
import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Reservation;
import com.jpmc.theater.model.Showing;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ReservationServiceTest {
  ReservationService reservationService = new ReservationService();
  Showing showing = new Showing(
    new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1),
    1,
    LocalDateTime.now()
  );

  @Test
  void shouldCalculateTotalFee() {
    // When
    double result = reservationService.calculateTotalFee(showing, 3);
    // Then
    assertEquals(37.5, result);
  }

  @Test
  void shouldCreateReservation() {
    // Given
    Customer customer = new Customer("John Doe", "unused-id");
    // When
    Reservation result = reservationService.createReservation(customer, showing, 3);
    // Then
    assertEquals(Reservation.builder()
      .customer(customer)
      .showing(showing)
      .audienceCount(3)
      .totalFee(37.5)
      .build(), result);
  }
}