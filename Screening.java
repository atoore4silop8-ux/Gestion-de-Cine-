package com.cinema.model;

import com.cinema.patterns.behavioral.NotificationObserver;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa una función de cine con película, sala, horario y asientos disponibles.
 * Observer pattern: Screening notifies observers on changes.
 */
public class Screening {
    private String tipo; // 2D, 3D, IMAX
    private Movie pelicula;
    private CinemaRoom sala;
    private LocalDateTime horario;
    private List<Seat> asientosDisponibles;
    private List<NotificationObserver> observers = new ArrayList<>();

    public Screening(String tipo, Movie pelicula, CinemaRoom sala, LocalDateTime horario, List<Seat> asientosDisponibles) {
        this.tipo = tipo;
        this.pelicula = pelicula;
        this.sala = sala;
        this.horario = horario;
        this.asientosDisponibles = asientosDisponibles;
    }

    // Observer methods
    public void addObserver(NotificationObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(NotificationObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (NotificationObserver observer : observers) {
            observer.update(this);
        }
    }

    // Method to simulate change (e.g., seat availability)
    public void updateAvailability() {
        // Simulate change
        notifyObservers();
    }

    // Getters y Setters
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Movie getPelicula() {
        return pelicula;
    }

    public void setPelicula(Movie pelicula) {
        this.pelicula = pelicula;
    }

    public CinemaRoom getSala() {
        return sala;
    }

    public void setSala(CinemaRoom sala) {
        this.sala = sala;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }

    public List<Seat> getAsientosDisponibles() {
        return asientosDisponibles;
    }

    public void setAsientosDisponibles(List<Seat> asientosDisponibles) {
        this.asientosDisponibles = asientosDisponibles;
        notifyObservers(); // Notify on change
    }

    @Override
    public String toString() {
        return "Screening{" +
                "tipo='" + tipo + '\'' +
                ", pelicula=" + pelicula +
                ", sala=" + sala +
                ", horario=" + horario +
                ", asientosDisponibles=" + asientosDisponibles +
                '}';
    }
}
