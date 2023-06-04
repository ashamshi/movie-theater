package com.jpmc.theater.service.discount.rule;

import com.jpmc.theater.model.Showing;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.function.Predicate;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AmountDiscountRule implements DiscountRule {
  Predicate<Showing> predicate;
  Double amount;

  @Override
  public Double apply(Showing showing) {
    if (predicate.test(showing)) {
      return amount;
    } else {
      return 0.0;
    }
  }
}
