package engine;

import cardValidationService.CardValidation;
import model.Booking;
import model.Flight;
import model.SeatCategory;
import utils.CSVWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightBookingEngine {
    private static FlightBookingEngine instance;
    private FlightBookingEngine() {}

    public static FlightBookingEngine getInstance(){
        if(instance == null){
            instance = new FlightBookingEngine();
        }
        return instance;
    }

    public List<Booking> getBookings(List<List<String>> bookingDetails) {
        //Generate Booking
        bookingDetails.remove(0);
        List<Booking> bookingList = new ArrayList<>();

        for (List<String> bookingDetail : bookingDetails) {
            String bookingname = bookingDetail.get(0);
            String flightNumber = bookingDetail.get(1);
            SeatCategory seatCategory = SeatCategory.getseatCategory(bookingDetail.get(2));
            int numberOfSeat = Integer.parseInt(bookingDetail.get(3));
            String paymentCardNumber = bookingDetail.get(4);
            Booking booking = new Booking.BookingBuilder(bookingname, flightNumber)
                    .seatCategory(seatCategory)
                    .numberOfSeat(numberOfSeat)
                    .cardNumber(paymentCardNumber)
                    .build();

            bookingList.add(booking);
        }
        return bookingList;
    }

    public Map<String, Flight> getFlightMap(List<List<String>> flightDetails) {
        //Generate Flight
        flightDetails.remove(0);
        Map<String, Flight> flightMap = new HashMap<>();

        int i = 0;
        for (List<String> flightDetail : flightDetails) {
            String flightNumber = flightDetail.get(1);
            int availableSeat = Integer.parseInt(flightDetail.get(2));
            int price = Integer.parseInt(flightDetail.get(3));
            String arrivalCity = flightDetail.get(4);
            String departureCity = flightDetail.get(5);
            SeatCategory seatCategory = SeatCategory.getseatCategory(flightDetail.get(0));

            Flight flight = new Flight.FlightBuilder(flightNumber, arrivalCity, departureCity)
                    .price(price)
                    .availableSeat(availableSeat)
                    .seatCategory(seatCategory)
                    .build();
            //create flight map
            flightMap.put(flight.getFlightNumber() + "_" + flight.getSeatCategory(), flight);
        }
        return flightMap;
    }

    public void reportGenerator(String outputPath, String outputExceptionPath, Map<String, Flight> flightMap, List<Booking> bookingList) throws IOException {
        //ENGIN CODE
        List<String[]> csvOutput = new ArrayList<>();
        List<String> exceptionOutput = new ArrayList<>();

        for (Booking booking : bookingList) {
            String key = booking.getFlightNumber() + "_" + booking.getSeatCategory();
            Flight flight = flightMap.get(key);
            if (flight == null) {
                System.out.println("Invalid Flight number");
                exceptionOutput.add(String.format("Please enter correct booking details for %s : %s", booking.getBookingName(), "Invalid flightNumber"));
                System.out.println(" ---------------------------------------- ");
                continue;
            }
            int seatRequested = booking.getNumberOfSeat();
            if (flight.getAvailableSeat() >= seatRequested) {
                // do chain of responsibility for the card validation
                CardValidation cardValidation = CardValidation.getInstance();
                String cardType = cardValidation.validateCard(booking.getCardNumber());
                if ("Invalid".equals(cardType) || "".equals(cardType)) {
                    System.out.println("booking failed due to invalid card number");
                    exceptionOutput.add(String.format("Please enter correct booking details for %s : %s", booking.getBookingName(), "Invalid CardNumber"));
                    System.out.println(" ---------------------------------------- ");
                    continue;
                }
                flight.setAvailableSeat(flight.getAvailableSeat() - seatRequested);
                int totalPrice = seatRequested * flight.getPrice();
                //write in csv file
                csvOutput.add(new String[]{booking.getBookingName(), booking.getFlightNumber(), booking.getSeatCategory().seatCategory,
                        String.valueOf(booking.getNumberOfSeat()), String.valueOf(totalPrice)});

                System.out.println(booking.getBookingName() + " , " + booking.getFlightNumber() + " , " + booking.getSeatCategory() + " , " + booking.getNumberOfSeat() + " , " + totalPrice);
                System.out.println("booking succesful");
                System.out.println(" ---------------------------------------- ");
            }else{
                System.out.println("booking failed since seat is not available - available seat is : " + flight.getAvailableSeat() + " & u requested : " + seatRequested);
                exceptionOutput.add(String.format("Please enter correct booking details for %s : %s", booking.getBookingName(), "Available seat is " + flight.getAvailableSeat() + " requested " + seatRequested));
                System.out.println(" ---------------------------------------- ");
                continue;
            }
        }

        csvOutput.add(0, new String[]{"Booking name", " flight number", "Category", "number of seats booked", " total price"});

        CSVWriter csvWriter = new CSVWriter();
        csvWriter.csvWriter(outputPath, csvOutput);
        csvWriter.fileWriter(outputExceptionPath, exceptionOutput);
    }
}
