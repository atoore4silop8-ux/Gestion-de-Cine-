package com.cinema.model;

import java.io.Serializable;

/**
 * Representa un asiento en la sala de cine.
 */
public class Seat implements Serializable {
    private static final long serialVersionUID = 1L;
    private String fila;
    private int numero;
    private boolean ocupado;

    public Seat(String fila, int numero) {
        this.fila = fila;
        this.numero = numero;
        this.ocupado = false;
    }

    // Getters y Setters
    public String getFila() {
        return fila;
    }

    public void setFila(String fila) {
        this.fila = fila;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    @Override
    public String toString() {
        return fila + numero;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Seat seat = (Seat) obj;
        return numero == seat.numero && fila.equals(seat.fila);
    }

    @Override
    public int hashCode() {
        return fila.hashCode() * 31 + numero;
    }
}
