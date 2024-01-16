package com.gridnine.testing;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlightServiceTest {
    FlightServiceImlp flightsService = new FlightServiceImlp();
    List<Flight> flights = FlightBuilder.createFlights();



    @Test
    public void testFlightsWithTwoHourDuration() {
        List<Flight> result = flightsService.flightsWithTwoHourDuration(flights);

        assertEquals(1, result.size());
    }

    @Test
    public void testMultiSegmentFlight() {
        List<Flight> result = flightsService.multiSegmentFlight(flights);

        assertEquals(3, result.size());
    }

    @Test
    public void testFlightWithMoreThanGivenHours() {
        List<Flight> result = flightsService.flightWithMoreThanGivenHours(3, flights);

        assertEquals(2, result.size());

        List<Flight> result1 = flightsService.flightWithMoreThanGivenHours(4, flights);

        assertEquals(0, result1.size());
    }

    @Test
    public void testFlightsInThePresentOrFutureOnly() {
        List<Flight> result = flightsService.flightsInThePresentOrFutureOnly(flights);

        assertEquals(5, result.size());
    }

    @Test
    public void testFlightsThatArrivesAfterItDeparts() {
        List<Flight> result = flightsService.flightsInThePresentOrFutureOnly(flights);

        assertEquals(5, result.size());
    }

    @Test
    public void testFlightWithMoreThanTwoHoursGroundTimeBetweenTwoSegments() {
        List<Flight> result = flightsService.flightWithMoreThanTwoHoursGroundTimeBetweenTwoSegments(flights);

        assertEquals(1, result.size());
    }

    @Test
    public void flightWithMoreThanTwoHoursGroundTime() {
        List<Flight> result = flightsService.flightWithMoreThanTwoHoursGroundTime(flights);

        assertEquals(3, result.size());
    }

    @Test
    public void testFlightsByGivenDate() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = LocalDateTime.now().plusDays(3).format(fmt);
        List<Flight> result = flightsService.flightsByGivenDate(date, flights);

        assertEquals(5, result.size());
    }

    @Test
    public void testFlightsInADateRangeGiven() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String startDate = LocalDateTime.now().plusDays(4).format(fmt);
        String endDate = LocalDateTime.now().plusDays(5).format(fmt);
        List<Flight> result = flightsService.flightsInADateRangeGiven(startDate, endDate, flights);

        assertEquals(2, result.size());
    }

    @Test
    public void testFightsWithFlexibleDates() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String startDate = LocalDateTime.now().plusDays(3).format(fmt);
        int daysRange = 2;
        List<Flight> result = flightsService.flightsWithFlexibleDates(startDate, daysRange, flights);

        assertEquals(7, result.size());
    }

    @Test
    public void testFlightsAfterNoon() {
        if (LocalDateTime.now().getHour() > 12){
            List<Flight> resultAfterNoon = flightsService.flightsAfterNoon(flights);
            assertEquals(8, resultAfterNoon.size());

        }
    }

    @Test
    public void testFlightBeforeNoon() {
        if (LocalDateTime.now().getHour() < 12) {
            List<Flight> resultBeforeNoon = flightsService.flightsBeforeNoon(flights);
            assertEquals(1, resultBeforeNoon.size());
        }
    }

    @Test
    public void testFlightsDeparturesAtGivenTime() {
        int hours = LocalDateTime.now().plusDays(3).plusHours(12).getHour();
        List<Flight> result = flightsService.flightsDeparturesAtGivenTime(hours, flights);

        assertEquals(1, result.size());
    }

    @Test
    public void testFlightsDeparturesBeforeGivenTime() {
        int hours = LocalDateTime.now().plusDays(3).plusHours(2).getHour();
        List<Flight> result = flightsService.flightsDeparturesBeforeGivenTime(hours, flights);

        assertEquals(9, result.size());
    }

}