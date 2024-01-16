package com.gridnine.testing;

import java.util.List;

public interface FlightService {

    /**
     * Возвращает список полётов, у которых время полёта составляет 2 часа.
     * В методе используются параллельные потоки {@link @parallelStream} предусмотренные для работы с большими списками
     *
     * @param flights список полётов, которые будут отфильтрованы
     * @return список полётов, у которых время полёта составляет 2 часа.
     */
    List<Flight> flightsWithTwoHourDuration(List<Flight> flights);

    /**
     * Возвращает список полётов, у которых несколько пересадок
     * В методе используются параллельные потоки {@link java.util.stream.Stream@parallelStream()} предусмотренные для работы с большими списками
     *
     * @param flights список полётов, которые будут отфильтрованы
     * @return список полётов, у которых несколько пересадок
     */
    List<Flight> multiSegmentFlight(List<Flight> flights);

    /**
     * Возвращает список полётов, у которых общее время ожидания между всеми пересадками больше заданного времени.
     * В методе используются параллельные потоки {@link java.util.stream.Stream@parallelStream()} предусмотренные для работы с большими списками
     *
     * @param flights список полётов, которые будут отфильтрованы
     * @param hours   время ожидания
     * @return список полётов, у которых общее время ожидания между всеми пересадками больше заданного времени.
     */
    List<Flight> flightWithMoreThanGivenHours(int hours, List<Flight> flights);

    /**
     * Возвращает список полётов, без тех, которые уже вылетели.
     * В методе используются параллельные потоки {@link java.util.stream.Stream@parallelStream()} предусмотренные для работы с большими списками
     *
     * @param flights список полётов, которые будут отфильтрованы
     * @return список полётов, без тех, которые уже вылетели.
     */
    List<Flight> flightsInThePresentOrFutureOnly(List<Flight> flights);

    /**
     * Возвращает список полётов, без тех, у которых дата вылета позже чем дата прилёта.
     * В методе используются параллельные потоки {@link java.util.stream.Stream@parallelStream()} предусмотренные для работы с большими списками
     *
     * @param flights список полётов, которые будут отфильтрованы
     * @return список полётов, без тех, у которых дата вылета позже чем дата прилёта.
     */
    List<Flight> flightsThatArrivesAfterItDeparts(List<Flight> flights);

    /**
     * Возвращает список полётов, у которых время ожидания между двумя пересадками больше 2 часов
     * В методе используются параллельные потоки {@link java.util.stream.Stream@parallelStream()} предусмотренные для работы с большими списками
     *
     * @param flights список полётов, которые будут отфильтрованы
     * @return список полётов, у которых общее время ожидания пересадок больше 2 часов
     */
    List<Flight> flightWithMoreThanTwoHoursGroundTimeBetweenTwoSegments(List<Flight> flights);

    /**
     * Возвращает список полётов, у которых общее время ожидания между всеми пересадками больше 2 часов
     * В методе используются параллельные потоки {@link java.util.stream.Stream@parallelStream()} предусмотренные для работы с большими списками
     *
     * @param flights список полётов, которые будут отфильтрованы
     * @return список полётов, у которых общее время ожидания между всеми пересадками больше 2 часов
     */
    List<Flight> flightWithMoreThanTwoHoursGroundTime(List<Flight> flights);

    /**
     * Возвращает список полётов, которые вылетают в заданную дату
     * В методе используются параллельные потоки {@link java.util.stream.Stream@parallelStream()} предусмотренные для работы с большими списками
     *
     * @param date    формат ("dd.MM.yyyy") даты, для поиска вылетов в этот день.
     * @param flights список полётов, которые будут отфильтрованы
     * @return список полётов, которые вылетают в заданную дату
     */
    List<Flight> flightsByGivenDate(String date, List<Flight> flights);

    /**
     * Возвращает список полётов, которые вылетают в диапазоне заданных дат
     * В методе используются параллельные потоки {@link java.util.stream.Stream@parallelStream()} предусмотренные для работы с большими списками
     *
     * @param startDate формат ("dd.MM.yyyy") даты, первая граница времени
     * @param endDate   формат ("dd.MM.yyyy") даты, вторая граница времени, включительно
     * @param flights   список полётов, которые будут отфильтрованы
     * @return список полётов, которые вылетают в диапазоне заданных дат
     */
    List<Flight> flightsInADateRangeGiven(String startDate, String endDate, List<Flight> flights);

    /**
     * Возвращает список полётов, которые вылетают в диапазоне заданной даты с некоторым количеством дней до и после предпочтительной даты
     * В методе используются параллельные потоки {@link java.util.stream.Stream@parallelStream()} предусмотренные для работы с большими списками
     *
     * @param date           формат ("dd.MM.yyyy") даты, первая граница времени
     * @param dateRange количеством дней до и после предпочтительной даты
     * @param flights        список полётов, которые будут отфильтрованы
     * @return список полётов, которые вылетают в диапазоне заданной даты с некоторым количеством дней до и после предпочтительной даты
     */
    List<Flight> flightsWithFlexibleDates(String date, int dateRange, List<Flight> flights);
    /**
     * Возвращает список полётов, которые вылетают после полудня
     * В методе используются параллельные потоки {@link java.util.stream.Stream@parallelStream()} предусмотренные для работы с большими списками
     * @param flights список полётов, которые будут отфильтрованы
     * @return список полётов, которые вылетают после полудня
     */
    List<Flight> flightsAfterNoon(List<Flight> flights);
    /**
     * Возвращает список полётов, которые вылетают до полудня
     * В методе используются параллельные потоки {@link java.util.stream.Stream@parallelStream()} предусмотренные для работы с большими списками
     * @param flights список полётов, которые будут отфильтрованы
     * @return список полётов, которые вылетают до полудня
     */
    List<Flight> flightsBeforeNoon(List<Flight> flights);
    /**
     * Возвращает список полётов, которые вылетают до заданного времени
     * В методе используются параллельные потоки {@link java.util.stream.Stream@parallelStream()} предусмотренные для работы с большими списками
     * @param flights список полётов, которые будут отфильтрованы
     * @param hours формат часов 0-23. Задать желательное время отправление
     * @return список полётов, которые вылетают до заданного времени
     */
    List<Flight> flightsDeparturesBeforeGivenTime(int hours, List<Flight> flights);
    /**
     * Возвращает список полётов, которые вылетают в заданное времени
     * В методе используются параллельные потоки {@link java.util.stream.Stream@parallelStream()} предусмотренные для работы с большими списками
     * @param flights список полётов, которые будут отфильтрованы
     * @param hours формат часов 0-23. Задать желательное время отправление
     * @return список полётов, которые вылетают в заданного времени
     */
    List<Flight> flightsDeparturesAtGivenTime(int hours, List<Flight> flights);
    /**
     * Возвращает список полётов, которые прилетают до заданного времени
     * В методе используются параллельные потоки {@link java.util.stream.Stream@parallelStream()} предусмотренные для работы с большими списками
     * @param flights список полётов, которые будут отфильтрованы
     * @param hours формат часов 0-23. Задать желательное время отправление
     * @return список полётов, которые вылетают до полудня
     */
    List<Flight> flightsArrivesAtGivenTime(int hours, List<Flight> flights);






}