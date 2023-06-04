package com.jpmc.theater.configuration;

import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.model.Schedule;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleConfiguration {
  LocalDateProvider localDateProvider = LocalDate::now;

  public ScheduleConfiguration withLocalDateProvider(LocalDateProvider localDateProvider) {
    return new ScheduleConfiguration(localDateProvider);
  }

  public Schedule schedule() {
    Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
    Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, 0);
    Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, 0);
    LocalDate scheduleDate = localDateProvider.get();
    return new Schedule(scheduleDate, List.of(
      new Showing(turningRed, 1, LocalDateTime.of(scheduleDate, LocalTime.of(9, 0))),
      new Showing(spiderMan, 2, LocalDateTime.of(scheduleDate, LocalTime.of(11, 0))),
      new Showing(theBatMan, 3, LocalDateTime.of(scheduleDate, LocalTime.of(12, 50))),
      new Showing(turningRed, 4, LocalDateTime.of(scheduleDate, LocalTime.of(14, 30))),
      new Showing(spiderMan, 5, LocalDateTime.of(scheduleDate, LocalTime.of(16, 10))),
      new Showing(theBatMan, 6, LocalDateTime.of(scheduleDate, LocalTime.of(17, 50))),
      new Showing(turningRed, 7, LocalDateTime.of(scheduleDate, LocalTime.of(19, 30))),
      new Showing(spiderMan, 8, LocalDateTime.of(scheduleDate, LocalTime.of(21, 10))),
      new Showing(theBatMan, 9, LocalDateTime.of(scheduleDate, LocalTime.of(23, 0)))
    ));
  }
}
