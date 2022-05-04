package test;

import engine.FlightBookingEngine;
import model.Booking;
import model.Flight;
import utils.CSVReader;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class RunClient {

    public static void main(String[] args) throws IOException {

        String flightDetailsCSVPath = args[0];
        String bookingDetailsCSVPath = args[1];
        String outputPath = args[2];
        String outputExceptionPath = args[3];

        //Get Singleton instance
        FlightBookingEngine flightBookingEngine = FlightBookingEngine.getInstance();
        CSVReader csvReader = CSVReader.getInstance();

        //Get flight details map from CSV
        List<List<String>> flightDetails = csvReader.readCSVFile(flightDetailsCSVPath);
        Map<String, Flight> flightMap = flightBookingEngine.getFlightMap(flightDetails);

        //get booking details from CSV
        List<List<String>> bookingDetails = csvReader.readCSVFile(bookingDetailsCSVPath);
        List<Booking> bookingList = flightBookingEngine.getBookings(bookingDetails);

        //Generate report
        flightBookingEngine.reportGenerator(outputPath, outputExceptionPath, flightMap, bookingList);
    }


}
