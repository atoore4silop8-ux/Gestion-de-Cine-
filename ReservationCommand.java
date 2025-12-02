package com.cinema.patterns.behavioral;

import com.cinema.model.Reservation;
import com.cinema.service.ReservationService;

/**
 * Patrón Command: Ejecución y deshacer de operaciones de reserva.
 */
public interface ReservationCommand {
    void ejecutar();
    void deshacer();

    // Command to create reservation
    class CreateReservationCommand implements ReservationCommand {
        private Reservation reserva;
        private ReservationService service;

        public CreateReservationCommand(Reservation reserva, ReservationService service) {
            this.reserva = reserva;
            this.service = service;
        }

        @Override
        public void ejecutar() {
            service.addReservation(reserva);
            System.out.println("Reserva creada: " + reserva.getId());
        }

        @Override
        public void deshacer() {
            service.getAllReservations().remove(reserva);
            System.out.println("Reserva eliminada: " + reserva.getId());
        }
    }

    // Command to cancel reservation
    class CancelReservationCommand implements ReservationCommand {
        private Reservation reserva;
        private ReservationState previousState;

        public CancelReservationCommand(Reservation reserva) {
            this.reserva = reserva;
            this.previousState = reserva.getEstado();
        }

        @Override
        public void ejecutar() {
            reserva.setEstado(new ReservationState.EstadoCancelada());
            System.out.println("Reserva cancelada: " + reserva.getId());
        }

        @Override
        public void deshacer() {
            reserva.setEstado(previousState);
            System.out.println("Cancelación revertida: " + reserva.getId());
        }
    }
}

// Concrete command types are now nested inside the ReservationCommand interface.
