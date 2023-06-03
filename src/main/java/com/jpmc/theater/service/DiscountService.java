package com.jpmc.theater.service;

import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;

public class DiscountService {
  public double calculateTicketPrice(Showing showing) {
    return showing.getMovie().getTicketPrice() - getDiscount(showing);
  }

  private double getDiscount(Showing showing) {
    double specialDiscount = 0;
    Movie movie = showing.getMovie();
    int MOVIE_CODE_SPECIAL = 1;
    if (MOVIE_CODE_SPECIAL == movie.getSpecialCode()) {
      specialDiscount = movie.getTicketPrice() * 0.2;  // 20% discount for special movie
    }

    double sequenceDiscount = 0;
    if (showing.getSequenceOfTheDay() == 1) {
      sequenceDiscount = 3; // $3 discount for 1st show
    } else if (showing.getSequenceOfTheDay() == 2) {

      sequenceDiscount = 2; // $2 discount for 2nd show
    }

    // biggest discount wins
    return Math.max(specialDiscount, sequenceDiscount);
  }
}
