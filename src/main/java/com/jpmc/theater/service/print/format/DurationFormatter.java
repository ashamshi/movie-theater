package com.jpmc.theater.service.print.format;

import java.time.Duration;

public class DurationFormatter {
  public String toHumanReadableFormat(Duration duration) {
    long hour = duration.toHours();
    long min = duration.toMinutesPart();
    return String.format("(%s hour%s %s minute%s)", hour, handlePlural(hour), min, handlePlural(min));
  }

  // (s) postfix should be added to handle plural correctly
  private String handlePlural(long value) {
    return value == 1 ? "" : "s";
  }
}
