package com.jpmc.theater;

import com.jpmc.theater.model.Customer;
import com.jpmc.theater.model.Reservation;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.schedule.Schedule;
import com.jpmc.theater.schedule.StaticSchedule;
import com.jpmc.theater.service.ReservationService;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TheaterTests {
    ReservationService reservationService = new ReservationService();

    @Test
    void shouldCreateReservationAndCalculateTotalFeeForCustomer() {
        // Given
        Schedule schedule = new StaticSchedule(LocalDate::now);
        Theater theater = new Theater(schedule, reservationService);
        Customer john = new Customer("John Doe", "id-12345");
        // When
        Reservation reservation = theater.reserve(john, 2, 4);
        // Then
        assertEquals(reservation.getTotalFee(), 50);
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenSequenceIsBiggerThanScheduledItemsNumber() {
        // Given
        Schedule emptySchedule = new Schedule() {
            @Override
            public List<Showing> getShowings() {
                return Collections.emptyList();
            }

            @Override
            public LocalDate getDate() {
                return LocalDate.now();
            }
        };
        Theater theater = new Theater(emptySchedule, reservationService);
        Customer john = new Customer("John Doe", "id-12345");
        // When & Then
        Exception thrown = assertThrows(
          IllegalArgumentException.class,
          () -> theater.reserve(john, 1, 4));
        assertEquals("not able to find any showing for given sequence 1", thrown.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenSequenceIsLessThanOne() {
        Schedule schedule = new StaticSchedule(LocalDate::now);
        Theater theater = new Theater(schedule, reservationService);
        Customer john = new Customer("John Doe", "id-12345");
        // When & Then
        Exception thrown = assertThrows(
          IllegalArgumentException.class,
          () -> theater.reserve(john, 0, 4));
        assertEquals("not able to find any showing for given sequence 0", thrown.getMessage());
    }

    @Test
    void shouldPrintMovieScheduleStaticData() {
        // Given
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Schedule schedule = new StaticSchedule(() -> LocalDate.of(2023, 6, 3));
        Theater theater = new Theater(schedule, reservationService, new PrintStream(outputStream));
        // When
        theater.printSchedule();
        // Then
        assertEquals("2023-06-03\n" +
          "===================================================\n" +
          "1: 2023-06-03T09:00 Turning Red (1 hour 25 minutes) $11.0\n" +
          "2: 2023-06-03T11:00 Spider-Man: No Way Home (1 hour 30 minutes) $12.5\n" +
          "3: 2023-06-03T12:50 The Batman (1 hour 35 minutes) $9.0\n" +
          "4: 2023-06-03T14:30 Turning Red (1 hour 25 minutes) $11.0\n" +
          "5: 2023-06-03T16:10 Spider-Man: No Way Home (1 hour 30 minutes) $12.5\n" +
          "6: 2023-06-03T17:50 The Batman (1 hour 35 minutes) $9.0\n" +
          "7: 2023-06-03T19:30 Turning Red (1 hour 25 minutes) $11.0\n" +
          "8: 2023-06-03T21:10 Spider-Man: No Way Home (1 hour 30 minutes) $12.5\n" +
          "9: 2023-06-03T23:00 The Batman (1 hour 35 minutes) $9.0\n" +
          "===================================================\n", outputStream.toString());
    }
}
