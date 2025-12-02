package com.cinema.patterns.structural;

import com.cinema.model.Reservation;

/**
 * Decorator pattern for adding services/discounts to Reservation price.
 */
public abstract class PricingDecorator {
    protected Reservation reservation;

    public PricingDecorator(Reservation reservation) {
        this.reservation = reservation;
    }

    public abstract double getPrice();
    public abstract String getDescription();

    public static class ComboPricingDecorator extends PricingDecorator {
        private final double comboPrice = 5.0;

        public ComboPricingDecorator(Reservation reservation) {
            super(reservation);
        }

        @Override
        public double getPrice() {
            return reservation.getPrecioTotal() + comboPrice;
        }

        @Override
        public String getDescription() {
            return reservation.toString() + " + Combo ($" + comboPrice + ")";
        }
    }

    public static class VIPPricingDecorator extends PricingDecorator {
        private final double vipPrice = 10.0;

        public VIPPricingDecorator(Reservation reservation) {
            super(reservation);
        }

        @Override
        public double getPrice() {
            return reservation.getPrecioTotal() + vipPrice;
        }

        @Override
        public String getDescription() {
            return reservation.toString() + " + VIP ($" + vipPrice + ")";
        }
    }

    public static class PromotionPricingDecorator extends PricingDecorator {
        private final double discount = 2.0;

        public PromotionPricingDecorator(Reservation reservation) {
            super(reservation);
        }

        @Override
        public double getPrice() {
            return Math.max(0, reservation.getPrecioTotal() - discount);
        }

        @Override
        public String getDescription() {
            return reservation.toString() + " - Promoción ($" + discount + " off)";
        }
    }
}
