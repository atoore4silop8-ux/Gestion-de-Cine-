package com.cinema.patterns.structural;

import com.cinema.model.Seat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * SeatComposite that groups Room -> Row -> SeatLeaf using nested static classes.
 * This file replaces multiple separate classes to obey the project structure.
 */
public class SeatComposite {
    private final String name;

    public SeatComposite(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static class SeatLeaf {
        private final Seat seat;

        public SeatLeaf(Seat seat) {
            this.seat = seat;
        }

        public Seat getSeat() {
            return seat;
        }

        @Override
        public String toString() {
            return seat.toString();
        }
    }

    public static class Row {
        private final String name;
        private final List<SeatLeaf> seats = new ArrayList<>();

        public Row(String name) {
            this.name = name;
        }

        public void addSeat(SeatLeaf seatLeaf) {
            seats.add(seatLeaf);
        }

        public List<SeatLeaf> getSeats() {
            return Collections.unmodifiableList(seats);
        }

        public String getName() {
            return name;
        }
    }

    public static class Room {
        private final String name;
        private final List<Row> rows = new ArrayList<>();

        public Room(String name) {
            this.name = name;
        }

        public void addRow(Row row) {
            rows.add(row);
        }

        public List<Row> getRows() {
            return Collections.unmodifiableList(rows);
        }

        public String getName() {
            return name;
        }
    }
}
// Reserved legacy file. The composite implementation is in SeatComponent.java
