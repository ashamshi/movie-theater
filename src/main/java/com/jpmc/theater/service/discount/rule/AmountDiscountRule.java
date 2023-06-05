package com.jpmc.theater.service.discount.rule;

import com.jpmc.theater.model.Money;
import com.jpmc.theater.model.Showing;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.function.Predicate;

import static java.math.BigDecimal.ZERO;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AmountDiscountRule implements DiscountRule {
  Predicate<Showing> predicate;
  Money amount;

  @Override
  public Money apply(Showing showing) {
    if (predicate.test(showing)) {
      return amount;
    } else {
      return new Money(amount.getCurrency(), ZERO);
    }
  }
}
