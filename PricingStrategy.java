package com.cinema.patterns.behavioral;

import com.cinema.model.Reservation;

/**
 * Strategy pattern for pricing strategies.
 */
public interface PricingStrategy {
    double calculatePrice(double basePrice, int numSeats);
    void applyDiscount(Reservation reservation);

    // Concrete strategies as nested types so they remain within the required file
    class NormalPricing implements PricingStrategy {
        @Override
        public double calculatePrice(double basePrice, int numSeats) {
            return basePrice * numSeats;
        }

        @Override
        public void applyDiscount(Reservation reservation) {
            // No discount for normal pricing
        }
    }

    class StudentPricing implements PricingStrategy {
        @Override
        public double calculatePrice(double basePrice, int numSeats) {
            return (basePrice * 0.8) * numSeats; // 20% discount
        }

        @Override
        public void applyDiscount(Reservation reservation) {
            reservation.setPrecioTotal(reservation.getPrecioTotal() * 0.8);
        }
    }

    class SeniorPricing implements PricingStrategy {
        @Override
        public double calculatePrice(double basePrice, int numSeats) {
            return (basePrice * 0.7) * numSeats; // 30% discount
        }

        @Override
        public void applyDiscount(Reservation reservation) {
            reservation.setPrecioTotal(reservation.getPrecioTotal() * 0.7);
        }
    }
}
