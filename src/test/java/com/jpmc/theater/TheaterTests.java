package com.jpmc.theater;

import com.jpmc.theater.model.Customer;
import com.jpmc.theater.model.Reservation;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.schedule.Schedule;
import com.jpmc.theater.schedule.StaticSchedule;
import com.jpmc.theater.service.ReservationService;
import com.jpmc.theater.service.print.PrintService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
public class TheaterTests {
    @Mock
    PrintService printService;
    @Mock
    ReservationService reservationService;
    @Spy
    Schedule schedule = new StaticSchedule(LocalDate::now);
    @InjectMocks
    Theater theater;

    @Test
    void shouldCreateReservationAndCalculateTotalFeeForCustomer() {
        // Given
        Customer john = new Customer("John Doe", "id-12345");
        Showing expectedShowing = schedule.getShowings().get(1);
        Reservation expectedReservation = new Reservation(john, expectedShowing, 4, 50);
        given(reservationService.createReservation(eq(john), eq(expectedShowing), eq(4)))
          .willReturn(expectedReservation);
        // When
        Reservation reservation = theater.reserve(john, 2, 4);
        // Then
        assertSame(expectedReservation, reservation);
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
        Theater theater = new Theater(emptySchedule, reservationService, printService);
        Customer john = new Customer("John Doe", "id-12345");
        // When & Then
        Exception thrown = assertThrows(
          IllegalArgumentException.class,
          () -> theater.reserve(john, 1, 4));
        assertEquals("not able to find any showing for given sequence 1", thrown.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenSequenceIsLessThanOne() {
        // Given
        Customer john = new Customer("John Doe", "id-12345");
        // When & Then
        Exception thrown = assertThrows(
          IllegalArgumentException.class,
          () -> theater.reserve(john, 0, 4));
        assertEquals("not able to find any showing for given sequence 0", thrown.getMessage());
    }

    @Test
    void shouldPrintMovieScheduleStaticData() {
        // Given & When
        theater.printSchedule();
        // Then
        then(printService).should().print(schedule);
    }
}
