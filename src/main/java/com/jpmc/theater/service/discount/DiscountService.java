package com.jpmc.theater.service.discount;

import com.jpmc.theater.model.Showing;
import com.jpmc.theater.service.discount.rule.DiscountRule;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DiscountService {
  List<DiscountRule> discountRules;

  public double calculateTicketPrice(Showing showing) {
    return showing.getMovie().getTicketPrice() - getDiscount(showing);
  }

  private double getDiscount(Showing showing) {
    double maxDiscount = 0;
    int discountsToApply = 0;
    for (DiscountRule rule : discountRules) {
      Double discount = rule.apply(showing);

      if (discount > 0) {
        discountsToApply++;
      }

      if (discount > maxDiscount) {
        maxDiscount = discount;
      }
    }

    // biggest discount wins
    return discountsToApply > 1 ? maxDiscount : 0;
  }
}
