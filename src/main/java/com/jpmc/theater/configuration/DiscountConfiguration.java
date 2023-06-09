package com.jpmc.theater.configuration;

import com.jpmc.theater.service.discount.rule.AmountDiscountRule;
import com.jpmc.theater.service.discount.rule.DiscountRule;
import com.jpmc.theater.service.discount.rule.FractionDiscountRule;

import java.util.List;

import static com.jpmc.theater.model.Money.usd;

public class DiscountConfiguration {
  public List<DiscountRule> discountRules() {
    return List.of(
      new FractionDiscountRule(showing -> showing.getMovie().getSpecialCode() == 1, 0.2),
      new AmountDiscountRule(showing -> showing.getSequenceOfTheDay() == 1, usd(3.0)),
      new AmountDiscountRule(showing -> showing.getSequenceOfTheDay() == 2, usd(2.0)),
      new FractionDiscountRule(showing ->
        showing.getShowStartTime()
          .isAfter(showing.getShowStartTime().withHour(11).withMinute(0))
          && showing.getShowStartTime()
          .isBefore(showing.getShowStartTime().withHour(16).withMinute(0)), 0.25),
      new AmountDiscountRule(showing -> showing.getShowStartTime().getDayOfMonth() == 7, usd(1.0)));
  }
}
