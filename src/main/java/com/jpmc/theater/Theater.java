package com.jpmc.theater;

import com.jpmc.theater.schedule.Schedule;
import com.jpmc.theater.schedule.StaticSchedule;

import java.io.PrintStream;
import java.time.Duration;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

public class Theater {
    private final Schedule schedule;
    private final PrintStream out;

    public Theater(Schedule schedule) {
        this(schedule, System.out);
    }
    public Theater(Schedule schedule, PrintStream out) {
        this.schedule = schedule;
        this.out = out;
    }

    public Reservation reserve(Customer customer, int sequence, int howManyTickets) {
        Showing showing;
        try {
            showing = schedule.getShowings().get(sequence - 1);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            throw new IllegalStateException("not able to find any showing for given sequence " + sequence);
        }
        return new Reservation(customer, showing, howManyTickets);
    }

    public void printSchedule() {
        out.println(schedule.getDate());
        out.println("===================================================");
        schedule.getShowings().forEach(s ->
                out.println(s.getSequenceOfTheDay() + ": " + s.getStartTime() + " " + s.getMovie().getTitle() + " "
                  + humanReadableFormat(s.getMovie().getRunningTime()) + " $" + s.getMovieFee())
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
        Theater theater = new Theater(schedule);
        theater.printSchedule();
    }
}
