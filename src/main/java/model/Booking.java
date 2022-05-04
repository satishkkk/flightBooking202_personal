package model;

import java.util.Objects;

public class Booking {
    String bookingName;
    String flightNumber;
    SeatCategory seatCategory;
    int numberOfSeat;
    String cardNumber;


    private Booking(Booking.BookingBuilder bookingBuilder) {
        this.bookingName = bookingBuilder.bookingName;
        this.flightNumber = bookingBuilder.flightNumber;
        this.seatCategory = bookingBuilder.seatCategory;
        this.numberOfSeat = bookingBuilder.numberOfSeat;
        this.cardNumber = bookingBuilder.cardNumber;
    }

    public String getBookingName() {
        return bookingName;
    }

    public void setBookingName(String bookingName) {
        this.bookingName = bookingName;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public SeatCategory getSeatCategory() {
        return seatCategory;
    }

    public void setSeatCategory(SeatCategory seatCategory) {
        this.seatCategory = seatCategory;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getNumberOfSeat() {
        return numberOfSeat;
    }

    public void setNumberOfSeat(int numberOfSeat) {
        this.numberOfSeat = numberOfSeat;
    }


    public static class BookingBuilder {
        String bookingName;
        String flightNumber;
        SeatCategory seatCategory;
        int numberOfSeat;
        String cardNumber;

        public BookingBuilder(String bookingName, String flightNumber) {
            this.bookingName = bookingName;
            this.flightNumber = flightNumber;
        }

        public Booking.BookingBuilder seatCategory(SeatCategory seatCategory) {
            this.seatCategory = seatCategory;
            return this;
        }

        public Booking.BookingBuilder numberOfSeat(int numberOfSeat) {
            this.numberOfSeat = numberOfSeat;
            return this;
        }

        public Booking.BookingBuilder cardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
            return this;
        }

        //Return the finally consrcuted User object
        public Booking build() {
            Booking booking = new Booking(this);
            validateBookingObject(booking);
            return booking;
        }

        private void validateBookingObject(Booking booking) {
            //Do some basic validations to check
            if (booking.bookingName == null || booking.bookingName.isBlank()) {
                throw new IllegalStateException("Booking number should not be empty");
            }
            if (booking.flightNumber == null || booking.flightNumber.isBlank()) {
                throw new IllegalStateException("flightNumber should not be empty");
            }
            if (booking.cardNumber == null || booking.cardNumber.isBlank()) {
                throw new IllegalStateException("cardNumber should not be empty");
            }

            if (booking.numberOfSeat <= 0) {
                throw new IllegalStateException("numberOfSeat should be non negative");
            }

            if (this.seatCategory == null) {
                throw new IllegalStateException("seat category is invalid");
            }

        }
    }
}
