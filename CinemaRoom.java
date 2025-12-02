package com.cinema.model;

import com.cinema.patterns.structural.SeatComposite;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
 

/**
 * Composite pattern: CinemaRoom contains rows, each row contains seats.
 */
public class CinemaRoom implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private int capacidad;
    private SeatComposite.Room roomComposite;

    public CinemaRoom(String nombre, int capacidad) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.roomComposite = new SeatComposite.Room(nombre);
        // Initialize rows and seats using composite
        int rowsCount = 10; // Default rows
        int seatsPerRow = Math.max(1, capacidad / rowsCount);
        for (int i = 0; i < rowsCount; i++) {
            SeatComposite.Row row = new SeatComposite.Row("Fila " + (char)('A' + i));
            for (int j = 1; j <= seatsPerRow; j++) {
                Seat seatModel = new Seat("Fila " + (char)('A' + i), j);
                row.addSeat(new SeatComposite.SeatLeaf(seatModel));
            }
            roomComposite.addRow(row);
        }
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public List<SeatComposite.Row> getFilas() {
        return roomComposite.getRows();
    }

    public void setFilas(List<com.cinema.patterns.structural.SeatComposite.Row> filas) {
        // Replace the composite rows based on provided rows
        this.roomComposite = new SeatComposite.Room(this.nombre);
        for (com.cinema.patterns.structural.SeatComposite.Row r : filas) {
            this.roomComposite.addRow(r);
        }
    }

    // For compatibility, provide a flat list of seats
    public List<Seat> getAsientos() {
        List<Seat> allSeats = new ArrayList<>();
        for (SeatComposite.Row row : roomComposite.getRows()) {
            for (SeatComposite.SeatLeaf seatLeaf : row.getSeats()) {
                allSeats.add(seatLeaf.getSeat());
            }
        }
        return allSeats;
    }

    @Override
    public String toString() {
        return "CinemaRoom{" +
                "nombre='" + nombre + '\'' +
                ", capacidad=" + capacidad +
                ", filas=" + roomComposite.getRows().size() +
                '}';
    }
    }
