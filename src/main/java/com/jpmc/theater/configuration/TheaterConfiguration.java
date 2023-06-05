package com.jpmc.theater.configuration;

import com.jpmc.theater.Theater;
import com.jpmc.theater.model.Schedule;
import com.jpmc.theater.service.ReservationService;
import com.jpmc.theater.service.discount.DiscountService;
import com.jpmc.theater.service.print.PrintService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.PrintStream;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TheaterConfiguration {
  ScheduleConfiguration scheduleConfiguration = new ScheduleConfiguration();
  PrintConfiguration printConfiguration = new PrintConfiguration();

  public Theater theater() {
    Schedule schedule = scheduleConfiguration.schedule();
    DiscountConfiguration discountConfiguration = new DiscountConfiguration();
    DiscountService discountService = new DiscountService(discountConfiguration.discountRules());
    ReservationService reservationService = new ReservationService(discountService);
    PrintService printService = printConfiguration.printService();
    return new Theater(schedule, reservationService, printService);
  }

  public TheaterConfiguration withPrintToJson(boolean printToJson) {
    return new TheaterConfiguration(scheduleConfiguration, printConfiguration.withPrintToJson(printToJson));
  }

  TheaterConfiguration withOutputStream(PrintStream out) {
    return new TheaterConfiguration(scheduleConfiguration, printConfiguration.withOutputStream(out));
  }

  TheaterConfiguration withScheduleLocalDateProvider(LocalDateProvider localDateProvider) {
    return new TheaterConfiguration(scheduleConfiguration.withLocalDateProvider(localDateProvider),
      this.printConfiguration);
  }
}
