package com.jpmc.theater.configuration;

import com.jpmc.theater.service.discount.rule.AmountDiscountRule;
import com.jpmc.theater.service.discount.rule.DiscountRule;
import com.jpmc.theater.service.discount.rule.FractionDiscountRule;

import java.util.List;

public class DiscountConfiguration {
  public List<DiscountRule> discountRules() {
    return List.of(
      new FractionDiscountRule(showing -> showing.getMovie().getSpecialCode() == 1, 0.2),
      new AmountDiscountRule(showing -> showing.getSequenceOfTheDay() == 1, 3.0),
      new AmountDiscountRule(showing -> showing.getSequenceOfTheDay() == 2, 2.0)
    );
  }
}
