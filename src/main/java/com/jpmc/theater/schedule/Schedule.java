package com.jpmc.theater.schedule;

import com.jpmc.theater.model.Showing;

import java.time.LocalDate;
import java.util.List;

public interface Schedule {
  List<Showing> getShowings();

  LocalDate getDate();
}
