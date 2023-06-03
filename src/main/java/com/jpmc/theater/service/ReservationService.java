package com.jpmc.theater.service;

import com.jpmc.theater.model.Customer;
import com.jpmc.theater.model.Reservation;
import com.jpmc.theater.model.Showing;

public class ReservationService {

  public Reservation createReservation(Customer customer, Showing showing, int howManyTickets) {
    double totalFee = calculateTotalFee(showing, howManyTickets);
    return new Reservation(customer, showing, howManyTickets, totalFee);
  }

  double calculateTotalFee(Showing showing, int howManyTickets) {
    return showing.getMovie().getTicketPrice() * howManyTickets;
  }
}
