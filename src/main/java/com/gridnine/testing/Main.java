package com.gridnine.testing;


import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println(FlightBuilder.createFlights());
        FlightServiceImlp flightService = new FlightServiceImlp();

        List<Flight> flights = FlightBuilder.createFlights();

    }



}


