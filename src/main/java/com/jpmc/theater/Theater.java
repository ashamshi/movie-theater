package com.jpmc.theater;

import com.jpmc.theater.configuration.TheaterConfiguration;
import com.jpmc.theater.model.Customer;
import com.jpmc.theater.model.Reservation;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.model.Schedule;
import com.jpmc.theater.service.ReservationService;
import com.jpmc.theater.service.print.PrintService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Arrays;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class Theater {
    Schedule schedule;
    ReservationService reservationService;
    PrintService printService;

    public Reservation reserve(Customer customer, int sequence, int howManyTickets) {
        if (sequence < 1 || sequence > schedule.getShowings().size()) {
            throw new IllegalArgumentException("not able to find any showing for given sequence " + sequence);
        }
        Showing showing = schedule.getShowings().get(sequence - 1);
        return reservationService.createReservation(customer, showing, howManyTickets);
    }

    public void printSchedule() {
        printService.print(schedule);
    }

    public static void main(String[] args) {
        boolean isPrintToJson = Arrays.stream(args)
          .anyMatch(arg -> arg.equalsIgnoreCase("json"));

        Theater theater = new TheaterConfiguration()
          .withPrintToJson(isPrintToJson)
          .theater();
        theater.printSchedule();
    }
}
