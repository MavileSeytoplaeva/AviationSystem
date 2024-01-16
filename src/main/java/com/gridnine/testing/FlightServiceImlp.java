package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FlightServiceImlp implements FlightService {
    @Override
    public List<Flight> flightsWithTwoHourDuration(List<Flight> flights) {
        return flights.parallelStream()
                .filter(flight -> {
                    List<Segment> segments = flight.getSegments();
                    if (segments.size() == 1) {
                        Segment segment = segments.get(0);
                        Duration duration = Duration.between(segment.getDepartureDate(), segment.getArrivalDate());
                        long differenceInHours = Math.abs(duration.toHours());
                        return differenceInHours == 2;
                    }
                    return false;
                })
                .toList();
    }

    @Override
    public List<Flight> multiSegmentFlight(List<Flight> flights) {
        return flights.parallelStream()
                .filter(flight -> flight.getSegments().size() > 1)
                .toList();
    }

    @Override
    public List<Flight> flightWithMoreThanGivenHours(int hours, List<Flight> flights) {
        return flights.parallelStream()
                .filter(flight -> {
                    List<Segment> segments = flight.getSegments();
                    if (segments.size() > 1) {
                        int totalGroundTime = calculateTotalGroundTime(segments);
                        return totalGroundTime >= (hours * 60); // More than two hours (120 minutes)
                    }
                    return false;
                })
                .toList();
    }

    @Override
    public List<Flight> flightsInThePresentOrFutureOnly(List<Flight> flights) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        return flights.parallelStream()
                .filter(flight -> {
                    List<Segment> segments = flight.getSegments();
                    return segments.stream()
                            .allMatch(segment -> segment.getDepartureDate().isAfter(currentDateTime));
                })
                .toList();
    }

    @Override
    public List<Flight> flightsThatArrivesAfterItDeparts(List<Flight> flights) {
        return flights.parallelStream()
                .filter(flight -> {
                    List<Segment> segments = flight.getSegments();
                    return segments.stream()
                            .allMatch(segment -> segment.getDepartureDate().isBefore(segment.getArrivalDate()));
                })
                .toList();
    }

    @Override
    public List<Flight> flightWithMoreThanTwoHoursGroundTimeBetweenTwoSegments(List<Flight> flights) {
        return flights.parallelStream()
                .filter(flight -> {
                    List<Segment> segments = flight.getSegments();
                    if (segments.size() == 2) {
                        int totalGroundTime = calculateTotalGroundTime(segments);
                        return totalGroundTime > 120; // More than two hours (120 minutes)
                    }
                    return false;
                })
                .toList();
    }

    /**
     * Возвращает число минут проведённых в ожидании пересадки
     * @param segments список сегментов, время между которыми будет складываться
     * @return число минут проведённых в ожидании пересадки
     */
    private int calculateTotalGroundTime(List<Segment> segments) {
        int totalGroundTime = 0;
        for (int i = 0; i < segments.size() - 1; i++) {
            Segment currentSegment = segments.get(i);
            Segment nextSegment = segments.get(i + 1);

            LocalDateTime currentSegmentArrival = currentSegment.getArrivalDate();
            LocalDateTime nextSegmentDeparture = nextSegment.getDepartureDate();

            Duration groundTime = Duration.between(currentSegmentArrival, nextSegmentDeparture);
            totalGroundTime += groundTime.toMinutes();
        }
        return totalGroundTime;
    }

    @Override
    public List<Flight> flightWithMoreThanTwoHoursGroundTime(List<Flight> flights) {
        return flights.parallelStream()
                .filter(flight -> {
                    List<Segment> segments = flight.getSegments();
                    int totalGroundTime = calculateTotalGroundTime(segments);
                    return totalGroundTime > 120; // More than two hours (120 minutes)
                })
                .toList();
    }

    @Override
    public List<Flight> flightsByGivenDate(String date, List<Flight> flights) {
        LocalDate formattedDate = parseToLocalDate(date);

        return flights.parallelStream()
                .filter(flight -> {
                    List<Segment> segments = flight.getSegments();
                    LocalDate departureDate = segments.get(0).getDepartureDate().toLocalDate();
                    return departureDate.equals(formattedDate);
                })
                .toList();
    }

    @Override
    public List<Flight> flightsInADateRangeGiven(String startDate, String endDate, List<Flight> flights) {
        LocalDate formattedStartDate = parseToLocalDate(startDate);
        LocalDate formattedEndDate = parseToLocalDate(endDate);
        return flights.parallelStream()
                .filter(flight -> {
                    List<Segment> segments = flight.getSegments();
                    LocalDate departureDate = segments.get(0).getDepartureDate().toLocalDate();
                    return !departureDate.isBefore(formattedStartDate) && !departureDate.isAfter(formattedEndDate);
                })
                .toList();
    }

    @Override
    public List<Flight> flightsWithFlexibleDates(String date, int dateRange, List<Flight> flights) {
        LocalDate formattedDate = parseToLocalDate(date);

        LocalDate startDate = formattedDate.minusDays(dateRange);
        LocalDate endDate = formattedDate.plusDays(dateRange);

        return flights.parallelStream()
                .filter(flight -> {
                    List<Segment> segments = flight.getSegments();
                    LocalDate departureDate = segments.get(0).getDepartureDate().toLocalDate();
                    return !departureDate.isBefore(startDate) && !departureDate.isAfter(endDate);
                })
                .toList();
    }

    @Override
    public List<Flight> flightsAfterNoon(List<Flight> flights) {
        return flights.parallelStream()
                .filter(flight -> {
                    List<Segment> segments = flight.getSegments();
                    int hours = segments.get(0).getDepartureDate().getHour();
                    return hours > 12;
//                    flight.getSegments().stream().anyMatch(segment -> segment.getDepartureDate().getHour() > 12)
                })
//                        isAfter(ChronoLocalDateTime.from(LocalTime.NOON)))) // Проверяем, что хотя бы один сегмент вылетает после полудня
                .toList();
    }

    @Override
    public List<Flight> flightsBeforeNoon(List<Flight> flights) {
        return flights.parallelStream()
                .filter(flight -> {
                    List<Segment> segments = flight.getSegments();
                    int hours = segments.get(0).getDepartureDate().getHour();
                    return hours < 12;
//                    flight.getSegments().stream().anyMatch(segment -> segment.getDepartureDate().getHour() > 12)
                })

//                        isAfter(ChronoLocalDateTime.from(LocalTime.NOON)))) // Проверяем, что хотя бы один сегмент вылетает после полудня
                .toList();
    }

    @Override
    public List<Flight> flightsDeparturesAtGivenTime(int hours, List<Flight> flights) {
        return flights.parallelStream()
                .filter(flight -> {
                    List<Segment> segments = flight.getSegments();
                    int segmentHours = segments.get(0).getDepartureDate().getHour();
                    return segmentHours == hours;
                })
                .toList();
    }

    @Override
    public List<Flight> flightsArrivesAtGivenTime(int hours, List<Flight> flights) {
        return null;
    }

    @Override
    public List<Flight> flightsDeparturesBeforeGivenTime(int hours, List<Flight> flights) {
        return flights.parallelStream()
                .filter(flight -> {
                    List<Segment> segments = flight.getSegments();
                    int segmentHours = segments.get(0).getDepartureDate().getHour();
                    return segmentHours <= hours;
                })
                .toList();
    }


    public LocalDate parseToLocalDate(String dateString) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate date = LocalDate.parse(dateString, inputFormatter);

        return date;
    }

}

