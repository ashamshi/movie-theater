package com.jpmc.theater;

import com.jpmc.theater.model.Customer;
import com.jpmc.theater.model.Reservation;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.schedule.Schedule;
import com.jpmc.theater.schedule.StaticSchedule;
import com.jpmc.theater.service.ReservationService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.io.PrintStream;
import java.time.Duration;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Theater {
    Schedule schedule;
    ReservationService reservationService;
    PrintStream out;

    public Theater(Schedule schedule, ReservationService reservationService) {
        this(schedule, reservationService, System.out);
    }
    public Theater(Schedule schedule, ReservationService reservationService, PrintStream out) {
        this.schedule = schedule;
        this.reservationService = reservationService;
        this.out = out;
    }

    public Reservation reserve(Customer customer, int sequence, int howManyTickets) {
        if (sequence < 1 || sequence > schedule.getShowings().size()) {
            throw new IllegalArgumentException("not able to find any showing for given sequence " + sequence);
        }
        Showing showing = schedule.getShowings().get(sequence - 1);
        return reservationService.createReservation(customer, showing, howManyTickets);
    }

    public void printSchedule() {
        out.println(schedule.getDate());
        out.println("===================================================");
        schedule.getShowings().forEach(s ->
                out.println(s.getSequenceOfTheDay() + ": " + s.getShowStartTime() + " " + s.getMovie().getTitle() + " "
                  + humanReadableFormat(s.getMovie().getRunningTime()) + " $" + s.getMovie().getTicketPrice())
        );
        out.println("===================================================");
    }

    public String humanReadableFormat(Duration duration) {
        long hour = duration.toHours();
        long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());

        return String.format("(%s hour%s %s minute%s)", hour, handlePlural(hour), remainingMin, handlePlural(remainingMin));
    }

    // (s) postfix should be added to handle plural correctly
    private String handlePlural(long value) {
        if (value == 1) {
            return "";
        }
        else {
            return "s";
        }
    }

    public static void main(String[] args) {
        Schedule schedule = new StaticSchedule(LocalDate::now);
        ReservationService reservationService = new ReservationService();
        Theater theater = new Theater(schedule, reservationService);
        theater.printSchedule();
    }
}
