package com.jpmc.theater.service.discount;

import com.jpmc.theater.model.Money;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.service.discount.rule.DiscountRule;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

import static java.math.BigDecimal.ZERO;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DiscountService {
  List<DiscountRule> discountRules;

  public Money calculateTicketPrice(Showing showing) {
    return showing.getMovie().getTicketPrice().subtract(getDiscount(showing));
  }

  private Money getDiscount(Showing showing) {
    Money maxDiscount = new Money(showing.getMovie().getTicketPrice().getCurrency(), ZERO);
    int discountsToApply = 0;
    for (DiscountRule rule : discountRules) {
      Money discount = rule.apply(showing);

      if (discount.getAmount().compareTo(ZERO) > 0) {
        discountsToApply++;
      }

      if (discount.greaterThan(maxDiscount)) {
        maxDiscount = discount;
      }
    }

    // biggest discount wins
    return discountsToApply > 1 ? maxDiscount : new Money(maxDiscount.getCurrency(), ZERO);
  }
}
