package com.jpmc.theater.service.discount;

import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.service.discount.rule.AmountDiscountRule;
import com.jpmc.theater.service.discount.rule.DiscountRule;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static com.jpmc.theater.model.Money.usd;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DiscountServiceTest {
  Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), usd(12.5), 0);
  Showing showing = new Showing(spiderMan, 5, LocalDateTime.now());

  @Test
  void shouldApplyDiscountWhenMultipleRulesMet() {
    // Given
    List<DiscountRule> rules = List.of(
      new AmountDiscountRule(showing -> true, usd(1.0)),
      new AmountDiscountRule(showing -> true, usd(1.0)));
    DiscountService target = new DiscountService(rules);
    // When & Then
    assertEquals(usd(11.5), target.calculateTicketPrice(showing));
  }

  @Test
  void shouldNotApplyDiscountWhenSingleRuleMet() {
    // Given
    List<DiscountRule> rules = List.of(
      new AmountDiscountRule(showing -> true, usd(1.0)),
      new AmountDiscountRule(showing -> false, usd(1.0)));
    DiscountService target = new DiscountService(rules);
    // When & Then
    assertEquals(spiderMan.getTicketPrice(), target.calculateTicketPrice(showing));
  }

  @Test
  void shouldApplyMaximumDiscountWhenMultipleRulesMet() {
    // Given
    List<DiscountRule> rules = List.of(
      new AmountDiscountRule(showing -> true, usd(1.0)),
      new AmountDiscountRule(showing -> true, usd(2.0)));
    DiscountService target = new DiscountService(rules);
    // When & Then
    assertEquals(usd(10.5), target.calculateTicketPrice(showing));
  }
}
