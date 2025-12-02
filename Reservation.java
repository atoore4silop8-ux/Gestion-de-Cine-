package com.cinema.model;

import com.cinema.patterns.behavioral.ReservationState;
import com.cinema.patterns.behavioral.ReservationState.EstadoPendiente;

import java.io.Serializable;
import java.util.List;

/**
 * Representa una reserva de asientos para una función.
 */
public class Reservation implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private Customer cliente;
    private Screening funcion;
    private List<Seat> asientos;
    private double precioTotal;
    private ReservationState estado;

    public Reservation(String id, Customer cliente, Screening funcion, List<Seat> asientos, double precioTotal) {
        this.id = id;
        this.cliente = cliente;
        this.funcion = funcion;
        this.asientos = asientos;
        this.precioTotal = precioTotal;
        this.estado = new EstadoPendiente();
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Customer getCliente() {
        return cliente;
    }

    public void setCliente(Customer cliente) {
        this.cliente = cliente;
    }

    public Screening getFuncion() {
        return funcion;
    }

    public void setFuncion(Screening funcion) {
        this.funcion = funcion;
    }

    public List<Seat> getAsientos() {
        return asientos;
    }

    public void setAsientos(List<Seat> asientos) {
        this.asientos = asientos;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public ReservationState getEstado() {
        return estado;
    }

    public void setEstado(ReservationState estado) {
        this.estado = estado;
        estado.handle(this);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id='" + id + '\'' +
                ", cliente=" + cliente +
                ", funcion=" + funcion +
                ", asientos=" + asientos +
                ", precioTotal=" + precioTotal +
                ", estado=" + estado +
                '}';
    }
}
