package model;

import java.util.Objects;

public class Flight {

    private String flightNumber;
    private String arrivalCity;
    private String departureCity;
    private int price;
    private int availableSeat;
    private int totalSeat;
    private SeatCategory seatCategory;

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAvailableSeat() {
        return availableSeat;
    }

    public void setAvailableSeat(int availableSeat) {
        this.availableSeat = availableSeat;
    }

    public int getTotalSeat() {
        return totalSeat;
    }

    public void setTotalSeat(int totalSeat) {
        this.totalSeat = totalSeat;
    }

    public SeatCategory getSeatCategory() {
        return seatCategory;
    }

    public void setSeatCategory(SeatCategory seatCategory) {
        this.seatCategory = seatCategory;
    }

    private Flight(FlightBuilder flightBuilder) {
        this.flightNumber = flightBuilder.flightNumber;
        this.arrivalCity = flightBuilder.arrivalCity;
        this.departureCity = flightBuilder.departureCity;
        this.price = flightBuilder.price;
        this.availableSeat = flightBuilder.availableSeat;
        this.totalSeat = flightBuilder.totalSeat;
        this.seatCategory = flightBuilder.seatCategory;
    }

    public static class FlightBuilder {

        private final String flightNumber;
        private final String arrivalCity;
        private final String departureCity;
        private int price;
        private int availableSeat;
        private int totalSeat;
        private SeatCategory seatCategory;

        public FlightBuilder(String flightNumber, String arrivalCity, String departureCity) {
            this.flightNumber = flightNumber;
            this.arrivalCity = arrivalCity;
            this.departureCity = departureCity;
        }

        public FlightBuilder price(int price) {
            this.price = price;
            return this;
        }

        public FlightBuilder availableSeat(int availableSeat) {
            this.availableSeat = availableSeat;
            this.totalSeat = availableSeat;
            return this;
        }

        public FlightBuilder seatCategory(SeatCategory seatCategory) {
            this.seatCategory = seatCategory;
            return this;
        }

        //Return the finally consrcuted User object
        public Flight build() {
            Flight flight = new Flight(this);
            validateFlightObject(flight);
            return flight;
        }

        private void validateFlightObject(Flight flight) {
            //Do some basic validations to check
            if (flight.flightNumber == null || flight.flightNumber.isBlank()) {
                throw new IllegalStateException("Flight number should not be empty");
            }
            if (flight.arrivalCity == null || flight.arrivalCity.isBlank()) {
                throw new IllegalStateException("arrivalCity should not be empty");
            }
            if (flight.departureCity == null || flight.departureCity.isBlank()) {
                throw new IllegalStateException("departureCity should not be empty");
            }

            if (flight.price < 0) {
                throw new IllegalStateException("Price should be non negative number");
            }

            if (flight.availableSeat < 0) {
                throw new IllegalStateException("availableSeat should be non negative number");
            }

            if (this.seatCategory == null) {
                throw new IllegalStateException("seat category is invalid");
            }

        }
    }


}
