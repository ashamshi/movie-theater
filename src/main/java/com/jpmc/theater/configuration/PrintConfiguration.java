package com.jpmc.theater.configuration;

import com.jpmc.theater.service.print.PrintService;
import com.jpmc.theater.service.print.SimpleTextPrintService;
import com.jpmc.theater.service.print.format.DurationFormatter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.PrintStream;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class PrintConfiguration {
  PrintStream out = System.out;

  PrintConfiguration withOutputStream(PrintStream out) {
    return new PrintConfiguration(out);
  }

  PrintService printService() {
    DurationFormatter durationFormatter = new DurationFormatter();
    return new SimpleTextPrintService(durationFormatter, out);
  }
}
