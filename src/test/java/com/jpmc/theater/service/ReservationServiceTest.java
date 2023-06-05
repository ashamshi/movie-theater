package com.jpmc.theater.service;

import com.jpmc.theater.model.Customer;
import com.jpmc.theater.model.Money;
import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Reservation;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.service.discount.DiscountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.time.LocalDateTime;

import static com.jpmc.theater.model.Money.usd;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {
  @Mock
  DiscountService discountService;
  @InjectMocks
  ReservationService reservationService;

  Showing showing = new Showing(
    new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), usd(12.5), 1),
    1,
    LocalDateTime.now()
  );

  @BeforeEach
  public void setUp() {
    // Given
    given(discountService.calculateTicketPrice(any()))
      .willAnswer((invocation) -> ((Showing) invocation.getArgument(0)).getMovie().getTicketPrice());
  }

  @Test
  void shouldCalculateTotalFee() {
    // When
    Money result = reservationService.calculateTotalFee(showing, 3);
    // Then
    assertEquals(usd(37.5), result);
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
      .totalFee(usd(37.5))
      .build(), result);
  }
}