package com.jpmc.theater.service.discount.rule;

import com.jpmc.theater.model.Money;
import com.jpmc.theater.model.Showing;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.function.Predicate;

import static java.math.BigDecimal.ZERO;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FractionDiscountRule implements DiscountRule {
  Predicate<Showing> predicate;
  Double fraction;

  @Override
  public Money apply(Showing showing) {
    Money price = showing.getMovie().getTicketPrice();
    if (predicate.test(showing)) {
      return price.multiply(BigDecimal.valueOf(fraction));
    } else {
      return new Money(price.getCurrency(), ZERO);
    }
  }
}
