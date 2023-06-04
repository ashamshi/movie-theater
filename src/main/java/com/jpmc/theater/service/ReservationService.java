package com.jpmc.theater.service;

import com.jpmc.theater.model.Customer;
import com.jpmc.theater.model.Reservation;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.service.discount.DiscountService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReservationService {
  DiscountService discountService;

  public Reservation createReservation(Customer customer, Showing showing, int howManyTickets) {
    double totalFee = calculateTotalFee(showing, howManyTickets);
    return new Reservation(customer, showing, howManyTickets, totalFee);
  }

  double calculateTotalFee(Showing showing, int howManyTickets) {
    double ticketPrice = discountService.calculateTicketPrice(showing);
    return ticketPrice * howManyTickets;
  }
}
