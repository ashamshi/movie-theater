package com.jpmc.theater.configuration;

import com.jpmc.theater.service.discount.rule.DiscountRule;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DiscountConfigurationTest {

  @Test
  void shouldGetDiscountRules() {
    // Given
    DiscountConfiguration target = new DiscountConfiguration();
    // When
    List<DiscountRule> result = target.discountRules();
    // Then
    assertNotNull(result);
    assertFalse(result.isEmpty());
  }
}
