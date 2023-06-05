package com.jpmc.theater.service.discount.rule;

import com.jpmc.theater.model.Money;
import com.jpmc.theater.model.Showing;

import java.util.function.Function;

public interface DiscountRule extends Function<Showing, Money> {
}
