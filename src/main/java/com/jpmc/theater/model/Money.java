package com.jpmc.theater.model;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@EqualsAndHashCode
public class Money {
  Currency currency;
  BigDecimal amount;

  public Money(Currency currency, BigDecimal amount) {
    this.currency = currency;
    this.amount = amount
      .setScale(currency.getDefaultFractionDigits(), RoundingMode.DOWN)
      .stripTrailingZeros();
  }

  public Money subtract(Money subtrahend) {
    checkCurrency(subtrahend);
    return new Money(currency, amount.subtract(subtrahend.amount));
  }

  public Money multiply(BigDecimal multiplier) {
    return new Money(currency, amount.multiply(multiplier));
  }

  public boolean greaterThan(Money other) {
    checkCurrency(other);
    return amount.compareTo(other.amount) > 0;
  }

  @Override
  public String toString() {
    return currency.getSymbol().concat(amount.toPlainString());
  }

  public static Money usd(double amount) {
    return new Money(Currency.getInstance("USD"), BigDecimal.valueOf(amount));
  }

  private void checkCurrency(Money other) {
    if (!currency.equals(other.currency)) {
      throw new IllegalArgumentException("Unable to operate on values with different currency");
    }
  }
}
