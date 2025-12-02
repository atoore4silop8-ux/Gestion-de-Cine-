package com.cinema.patterns.creational;

import com.cinema.model.*;

/**
 * Factory Method pattern for creating different types of screenings.
 */
public class ScreeningFactory {

    public static Screening createScreening(String type, Movie movie, CinemaRoom room, java.time.LocalDateTime dateTime) {
        switch (type.toUpperCase()) {
            case "2D":
                return new Screening("2D", movie, room, dateTime, room.getAsientos());
            case "3D":
                return new Screening("3D", movie, room, dateTime, room.getAsientos());
            case "IMAX":
                return new Screening("IMAX", movie, room, dateTime, room.getAsientos());
            default:
                throw new IllegalArgumentException("Unknown screening type: " + type);
        }
    }
}
