package com.jpmc.theater.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.Duration;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@EqualsAndHashCode
@AllArgsConstructor
public class Movie {
    String title;
    Duration runningTime;
    Money ticketPrice;
    int specialCode;
}