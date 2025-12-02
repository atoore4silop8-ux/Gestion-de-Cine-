package com.cinema.patterns.creational;

import com.cinema.model.*;
import com.cinema.patterns.behavioral.PricingStrategy;
import java.util.ArrayList;
import java.util.List;

/**
 * Builder pattern for constructing complex reservations.
 */
public class ReservationBuilder {
    private Customer customer;
    private Screening screening;
    private List<Seat> seats = new ArrayList<>();
    private PricingStrategy pricingStrategy;

    public ReservationBuilder setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public ReservationBuilder setScreening(Screening screening) {
        this.screening = screening;
        return this;
    }

    public ReservationBuilder addSeat(Seat seat) {
        this.seats.add(seat);
        return this;
    }

    public ReservationBuilder setPricingStrategy(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
        return this;
    }

    public Reservation build() {
        if (customer == null || screening == null || seats.isEmpty()) {
            throw new IllegalStateException("Customer, screening, and seats must be set");
        }
        double basePrice = 20.0; // Base price per seat
        double totalPrice = pricingStrategy != null ? pricingStrategy.calculatePrice(basePrice, seats.size()) : basePrice * seats.size();
        return new Reservation("R" + System.currentTimeMillis(), customer, screening, seats, totalPrice);
    }
}
