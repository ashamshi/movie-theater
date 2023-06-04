package com.jpmc.theater.service.print;

import com.jpmc.theater.model.Schedule;
import com.jpmc.theater.service.print.format.DurationFormatter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.PrintStream;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class SimpleTextPrintService implements PrintService {
  DurationFormatter durationFormatter;
  PrintStream out;

  @Override
  public void print(Schedule schedule) {
    out.println(schedule.getDate());
    out.println("===================================================");
    schedule.getShowings().forEach(s ->
      out.println(s.getSequenceOfTheDay() + ": " + s.getShowStartTime() + " " + s.getMovie().getTitle() + " "
        + durationFormatter.toHumanReadableFormat(s.getMovie().getRunningTime()) + " $" + s.getMovie().getTicketPrice())
    );
    out.println("===================================================");
  }
}
