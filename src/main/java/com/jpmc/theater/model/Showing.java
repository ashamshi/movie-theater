package com.jpmc.theater.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@EqualsAndHashCode
public class Showing {
    private Movie movie;
    private int sequenceOfTheDay;
    private LocalDateTime showStartTime;
}
