package com.cinema.patterns.behavioral;

import com.cinema.model.Reservation;

/**
 * State pattern for reservation states.
 */
public interface ReservationState {
    void handle(Reservation reservation);

    static class EstadoPendiente implements ReservationState {
        @Override
        public void handle(Reservation reservation) {
            System.out.println("Reserva pendiente.");
        }

        @Override
        public String toString() {
            return "Pendiente";
        }
    }

    static class EstadoConfirmada implements ReservationState {
        @Override
        public void handle(Reservation reservation) {
            System.out.println("Reserva confirmada.");
        }

        @Override
        public String toString() {
            return "Confirmada";
        }
    }

    static class EstadoCancelada implements ReservationState {
        @Override
        public void handle(Reservation reservation) {
            System.out.println("Reserva cancelada.");
        }

        @Override
        public String toString() {
            return "Cancelada";
        }
    }

    static class EstadoUtilizada implements ReservationState {
        @Override
        public void handle(Reservation reservation) {
            System.out.println("Reserva utilizada (pasada).");
        }

        @Override
        public String toString() {
            return "Utilizada";
        }
    }
}
