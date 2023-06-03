package com.jpmc.theater.schedule;

import com.jpmc.theater.Movie;
import com.jpmc.theater.Showing;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class StaticSchedule implements Schedule {
  private final LocalDate scheduleDate;

  public StaticSchedule(LocalDateProvider provider) {
    this.scheduleDate = provider.get();
  }

  @Override
  public List<Showing> getShowings() {
    Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
    Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, 0);
    Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, 0);
    return List.of(
      new Showing(turningRed, 1, LocalDateTime.of(scheduleDate, LocalTime.of(9, 0))),
      new Showing(spiderMan, 2, LocalDateTime.of(scheduleDate, LocalTime.of(11, 0))),
      new Showing(theBatMan, 3, LocalDateTime.of(scheduleDate, LocalTime.of(12, 50))),
      new Showing(turningRed, 4, LocalDateTime.of(scheduleDate, LocalTime.of(14, 30))),
      new Showing(spiderMan, 5, LocalDateTime.of(scheduleDate, LocalTime.of(16, 10))),
      new Showing(theBatMan, 6, LocalDateTime.of(scheduleDate, LocalTime.of(17, 50))),
      new Showing(turningRed, 7, LocalDateTime.of(scheduleDate, LocalTime.of(19, 30))),
      new Showing(spiderMan, 8, LocalDateTime.of(scheduleDate, LocalTime.of(21, 10))),
      new Showing(theBatMan, 9, LocalDateTime.of(scheduleDate, LocalTime.of(23, 0)))
    );
  }

  @Override
  public LocalDate getDate() {
    return scheduleDate;
  }
}
