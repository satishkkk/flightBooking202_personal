package engine;

import model.Booking;
import model.Flight;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FlightBookingEngineTest {

    FlightBookingEngine client = FlightBookingEngine.getInstance();
    @Test
    void getInstance() {
        client = FlightBookingEngine.getInstance();
        Assert.assertNotNull(client);
    }

    @Test
    void getFlightMap() {
        List<List<String>> FlightDetails = new ArrayList<>();
        List<String> header = new ArrayList<>(Arrays.asList("Category(Economy,PremiumEconomy,Business)","FlightNumber","AvailableSeats","Price","Arrival","Departure"));
        List<String> flight = new ArrayList<>(Arrays.asList("Economy","SJ456","5","250","Seattle","San Jose"));
        FlightDetails.add(header);
        FlightDetails.add(flight);
        Map<String, Flight> map  = client.getFlightMap(FlightDetails);
        Assert.assertEquals(map.size(), 1);
    }

    @Test
    void reportGenerator() throws IOException {
        List<List<String>> FlightDetails = new ArrayList<>();
        List<String> header = new ArrayList<>(Arrays.asList("Category(Economy,PremiumEconomy,Business)","FlightNumber","AvailableSeats","Price","Arrival","Departure"));
        List<String> flight = new ArrayList<>(Arrays.asList("Economy","SJ456","5","250","Seattle","San Jose"));
        FlightDetails.add(header);
        FlightDetails.add(flight);
        Map<String, Flight> map  = client.getFlightMap(FlightDetails);

        List<List<String>> bookingDetails = new ArrayList<>();
        List<String> bookingHeader = new ArrayList<>(Arrays.asList("BookingName", "flightNumber", "seatCategory", "numberOfSeats", "paymentCardNumber"));
        List<String> booking = new ArrayList<>(Arrays.asList("Sam", "SJ456", "Economy", "2", "5410000000000000"));
        List<String> booking2 = new ArrayList<>(Arrays.asList("kam", "SJ4569", "Economy", "2", "5410000000000000"));
        List<String> booking3 = new ArrayList<>(Arrays.asList("mam", "SJ456", "Economy", "122", "5410000000000000"));
        List<String> booking4 = new ArrayList<>(Arrays.asList("pam", "SJ456", "Economy", "2", "541000000000000034534"));
        bookingDetails.add(bookingHeader);
        bookingDetails.add(booking);
        bookingDetails.add(booking2);
        bookingDetails.add(booking3);
        bookingDetails.add(booking4);
        List<Booking> bookings = client.getBookings(bookingDetails);

        client.reportGenerator("C:\\Users\\satis\\Desktop\\java\\FlightBooking_202_personal\\src\\test\\java\\resources\\testData\\testOutput\\out.csv",
                "C:\\Users\\satis\\Desktop\\java\\FlightBooking_202_personal\\src\\test\\java\\resources\\testData\\testOutput\\out.txt",
                map,bookings);


    }

    @Test
    public void getBookings() {
        List<List<String>> bookingDetails = new ArrayList<>();
        List<String> header = new ArrayList<>(Arrays.asList("BookingName", "flightNumber", "seatCategory", "numberOfSeats", "paymentCardNumber"));
        List<String> booking = new ArrayList<>(Arrays.asList("Sam", "SJ456", "Economy", "2", "5410000000000000"));
        bookingDetails.add(header);
        bookingDetails.add(booking);
        List<Booking> bookings = client.getBookings(bookingDetails);
        Assert.assertEquals(bookings.size(), 1);
    }
}