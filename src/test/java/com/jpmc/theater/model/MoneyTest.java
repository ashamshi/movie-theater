package com.jpmc.theater.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Currency;

import static com.jpmc.theater.model.Money.usd;
import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {

  @Test
  void shouldSubtract() {
    // Given
    Money one = usd(10);
    Money other = usd(1);
    // When
    Money result = one.subtract(other);
    // Then
    assertEquals(usd(9), result);
  }

  @Test
  void shouldThrowIllegalArgumentExceptionWhenCurrenciesAreDifferentAndSubtractCalled() {
    // Given
    Money one = usd(10);
    Money other = new Money(Currency.getInstance("EUR"), BigDecimal.valueOf(5));
    // When & Then
    Exception thrown = assertThrows(
      IllegalArgumentException.class,
      () -> one.subtract(other));
    assertEquals("Unable to operate on values with different currency", thrown.getMessage());
  }

  @Test
  void shouldReturnMultiplied() {
    // Given
    Money money = usd(10);
    // When
    Money result = money.multiply(BigDecimal.valueOf(1.1));
    // Then
    assertEquals(usd(11), result);
  }

  @Test
  void shouldReturnTrueIfGreaterThan() {
    // Given
    Money one = usd(10);
    Money other = usd(1);
    // When
    boolean result = one.greaterThan(other);
    // Then
    assertTrue(result);
  }

  @Test
  void shouldReturnFalseIfNotGreaterThan() {
    // Given
    Money one = usd(1);
    Money other = usd(10);
    // When
    boolean result = one.greaterThan(other);
    // Then
    assertFalse(result);
  }

  @Test
  void shouldThrowIllegalArgumentExceptionWhenCurrenciesAreDifferentAndGreaterThanCalled() {
    // Given
    Money one = usd(10);
    Money other = new Money(Currency.getInstance("EUR"), BigDecimal.valueOf(5));
    // When & Then
    Exception thrown = assertThrows(
      IllegalArgumentException.class,
      () -> one.greaterThan(other));
    assertEquals("Unable to operate on values with different currency", thrown.getMessage());
  }

  @Test
  void shouldReturnStringRepresentation() {
    // Given
    Money one = usd(1);
    Money other = usd(10.5);
    // When & Then
    assertEquals("$1", one.toString());
    assertEquals("$10.5", other.toString());
  }

  @Test
  void shouldReturnMoneyWithCurrencyUsd() {
    // Given, When & Then
    assertEquals(new Money(Currency.getInstance("USD"), BigDecimal.valueOf(1)), usd(1));
  }

  @Test
  void shouldBeEqualWhenObjectsEqual() {
    // Given, When & Then
    assertEquals(new Money(Currency.getInstance("USD"), BigDecimal.valueOf(1)),
      new Money(Currency.getInstance("USD"), BigDecimal.valueOf(1)));
  }

  @Test
  void shouldBeNotEqualWhenObjectsAreNotEqual() {
    assertNotEquals(new Money(Currency.getInstance("USD"), BigDecimal.valueOf(1)),
      new Money(Currency.getInstance("USD"), BigDecimal.valueOf(2)));
    assertNotEquals(new Money(Currency.getInstance("EUR"), BigDecimal.valueOf(1)),
      new Money(Currency.getInstance("USD"), BigDecimal.valueOf(1)));
    assertNotEquals(new Object(),
      new Money(Currency.getInstance("USD"), BigDecimal.valueOf(1)));
    assertNotEquals(new Money(Currency.getInstance("USD"), BigDecimal.valueOf(1)),
      new Object());
  }

  @Test
  void shouldCalculateSameHashCode() {
    assertEquals(new Money(Currency.getInstance("USD"), BigDecimal.valueOf(1)).hashCode(),
      new Money(Currency.getInstance("USD"), BigDecimal.valueOf(1)).hashCode());
  }

  @Test
  void shouldCalculateDifferentHashCodes() {
    assertNotEquals(new Money(Currency.getInstance("USD"), BigDecimal.valueOf(1)).hashCode(),
      new Money(Currency.getInstance("USD"), BigDecimal.valueOf(2)).hashCode());
    assertNotEquals(new Money(Currency.getInstance("EUR"), BigDecimal.valueOf(1)).hashCode(),
      new Money(Currency.getInstance("USD"), BigDecimal.valueOf(1)).hashCode());
  }

  @Test
  void shouldGetCurrency() {
    // Given
    Money money = usd(1);
    // When & Then
    assertEquals(Currency.getInstance("USD"), money.getCurrency());
  }

  @Test
  void shouldGetAmount() {
    // Given
    Money money = usd(1);
    // When & Then
    assertEquals(BigDecimal.valueOf(1), money.getAmount());
  }
}
