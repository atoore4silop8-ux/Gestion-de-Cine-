package com.cinema;

import com.cinema.model.*;
import com.cinema.service.*;
import com.cinema.patterns.creational.*;
import com.cinema.patterns.behavioral.*;
import com.cinema.patterns.structural.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main class to run the cinema booking system.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Sistema de Reservas para Cine");

        // Crear servicios
        RoomService roomService = new RoomService();
        MovieService movieService = new MovieService();
        ReservationService reservationService = new ReservationService();
        ReportService reportService = new ReportService();

        // Crear una sala
        CinemaRoom room = new CinemaRoom("Sala 1", 100);
        roomService.addRoom(room);

        // Crear una película
        Movie movie = new Movie("M001", "Pelicula 1", "Genero", 120);
        movieService.addMovie(movie);

        // Crear una función usando Factory Method
        Screening screening = ScreeningFactory.createScreening("2D", movie, room, java.time.LocalDateTime.of(2023, 12, 1, 20, 0));

        // Crear un cliente
        Customer customer = new Customer("C001", "Juan", "juan@example.com", "123456789");

        // Crear una reserva usando Builder
        Reservation reservation = new ReservationBuilder()
                .setCustomer(customer)
                .setScreening(screening)
                .addSeat(room.getAsientos().get(0))
                .addSeat(room.getAsientos().get(1))
                .setPricingStrategy(new PricingStrategy.NormalPricing())
                .build();
        reservationService.addReservation(reservation);

        System.out.println("=== Sistema de Reservas para Cine ===");
        System.out.println("Reserva inicial creada exitosamente.");
        System.out.println("Salas disponibles: " + roomService.getAllRooms().size());
        System.out.println("Películas disponibles: " + movieService.getAllMovies().size());
        System.out.println("=====================================");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Administrar Sistema");
            System.out.println("2. Reservar / Consultar");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el newline

            switch (opcion) {
                case 1:
                    adminMenu(scanner, roomService, movieService, reservationService, reportService);
                    break;
                case 2:
                    clientMenu(scanner, roomService, movieService, reservationService);
                    break;
                case 3:
                    System.out.println("Saliendo del sistema...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private static double basePrice = 20.0; // Patrón Strategy: precio base

    private static void adminMenu(Scanner scanner, RoomService roomService, MovieService movieService, ReservationService reservationService, ReportService reportService) {
        while (true) {
            System.out.println("\n--- Módulo de Administración ---");
            System.out.println("1. Gestión de Películas");
            System.out.println("2. Gestión de Salas");
            System.out.println("3. Gestión de Programación (Funciones)");
            System.out.println("4. Ver todas las Reservas");
            System.out.println("5. Establecer Estrategia de Precios");
            System.out.println("6. Generar Reporte de Scripts de Prueba en PDF");
            System.out.println("7. Generar Reporte del Sistema en PDF");
            System.out.println("0. Regresar");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("\n--- Gestión de Películas ---");
                    System.out.print("Título: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Género: ");
                    String genero = scanner.nextLine();
                    System.out.print("Duración (min): ");
                    int duracion = scanner.nextInt();
                    scanner.nextLine();
                    Movie movie = new Movie("M" + (movieService.getAllMovies().size() + 1), titulo, genero, duracion);
                    movieService.addMovie(movie);
                    System.out.println("Película agregada: " + movie.getTitulo());
                    break;
                case 2:
                    System.out.println("\n--- Gestión de Salas ---");
                    System.out.print("Nombre de la sala: ");
                    String nombreSala = scanner.nextLine();
                    System.out.print("Capacidad: ");
                    int capacidad = scanner.nextInt();
                    scanner.nextLine();
                    CinemaRoom room = new CinemaRoom(nombreSala, capacidad);
                    roomService.addRoom(room);
                    System.out.println("Sala creada: " + room.getNombre());
                    break;
                case 3:
                    System.out.println("\n--- Gestión de Programación ---");
                    System.out.println("Películas disponibles:");
                    for (Movie m : movieService.getAllMovies()) {
                        System.out.println("  ID: " + m.getId() + " - " + m.getTitulo());
                    }
                    System.out.print("ID de Película: ");
                    String movieId = scanner.nextLine();
                    Movie selectedMovie = movieService.getAllMovies().stream().filter(m -> m.getId().equals(movieId)).findFirst().orElse(null);
                    if (selectedMovie == null) {
                        System.out.println("Película no encontrada.");
                        break;
                    }
                    System.out.println("Salas disponibles:");
                    for (CinemaRoom r : roomService.getAllRooms()) {
                        System.out.println("  ID: " + r.getNombre() + " - Capacidad: " + r.getCapacidad());
                    }
                    System.out.print("Nombre de Sala: ");
                    String roomName = scanner.nextLine();
                    CinemaRoom selectedRoom = roomService.getAllRooms().stream().filter(r -> r.getNombre().equals(roomName)).findFirst().orElse(null);
                    if (selectedRoom == null) {
                        System.out.println("Sala no encontrada.");
                        break;
                    }
                    System.out.println("Tipos de función disponibles: 2D, 3D, IMAX");
                    System.out.print("Tipo de función: ");
                    String screeningType = scanner.nextLine().toUpperCase();
                    if (!screeningType.equals("2D") && !screeningType.equals("3D") && !screeningType.equals("IMAX")) {
                        System.out.println("Tipo no válido. Usando 2D por defecto.");
                        screeningType = "2D";
                    }
                    System.out.print("Fecha (YYYY-MM-DD): ");
                    String date = scanner.nextLine();
                    System.out.print("Hora (HH:MM): ");
                    String time = scanner.nextLine();
                    java.time.LocalDateTime dateTime = java.time.LocalDateTime.parse(date + "T" + time);
                    Screening screening = ScreeningFactory.createScreening(screeningType, selectedMovie, selectedRoom, dateTime);
                    System.out.println("Función programada: " + selectedMovie.getTitulo() + " (" + screeningType + ") en " + selectedRoom.getNombre() + " a las " + dateTime);
                    break;
                case 4:
                    System.out.println("\n--- Todas las Reservas ---");
                    for (Reservation res : reservationService.getAllReservations()) {
                        System.out.println("  - ID: " + res.getId() + ", Cliente: " + res.getCliente().getNombre() + ", Estado: " + res.getEstado().toString());
                    }
                    break;
                case 6:
                    System.out.println("\n--- Generar Reporte de Scripts de Prueba en PDF ---");
                    try {
                        reportService.generateTestScriptsPDF("TestScripts.md", "TestScripts.pdf");
                    } catch (Exception e) {
                        System.out.println("Error al generar el PDF: " + e.getMessage());
                    }
                    break;
                case 7:
                    System.out.println("\n--- Generar Reporte del Sistema en PDF ---");
                    try {
                        reportService.generateSystemReportPDF("SystemReport.pdf", roomService, movieService, reservationService);
                    } catch (Exception e) {
                        System.out.println("Error al generar el PDF del sistema: " + e.getMessage());
                    }
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static void testPDFGeneration() {
        ReportService reportService = new ReportService();
        try {
            reportService.generateTestScriptsPDF("TestScripts.md", "TestScripts.pdf");
            System.out.println("PDF generado exitosamente para testing.");
        } catch (Exception e) {
            System.out.println("Error al generar PDF: " + e.getMessage());
        }
    }

    private static void clientMenu(Scanner scanner, RoomService roomService, MovieService movieService, ReservationService reservationService) {
        while (true) {
            System.out.println("\n--- Módulo de Cliente ---");
            System.out.println("1. Ver Cartelera / Funciones");
            System.out.println("2. Crear Nueva Reserva");
            System.out.println("3. Gestionar (Confirmar/Cancelar) Reserva");
            System.out.println("0. Regresar");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("\n--- Cartelera / Funciones Disponibles ---");
                    // Note: Screenings are not stored, so show movies and rooms as available functions
                    System.out.println("Funciones disponibles:");
                    for (Movie m : movieService.getAllMovies()) {
                        for (CinemaRoom r : roomService.getAllRooms()) {
                            System.out.println("  - " + m.getTitulo() + " en " + r.getNombre() + " (2D)");
                        }
                    }
                    break;
                case 2:
                    System.out.println("\n--- Crear Nueva Reserva ---");
                    System.out.println("Funciones disponibles:");
                    int index = 1;
                    List<String> functionIds = new ArrayList<>();
                    for (Movie m : movieService.getAllMovies()) {
                        for (CinemaRoom r : roomService.getAllRooms()) {
                            System.out.println("  " + index + ". " + m.getTitulo() + " en " + r.getNombre());
                            functionIds.add(m.getId() + "-" + r.getNombre());
                            index++;
                        }
                    }
                    System.out.print("Seleccione función (número): ");
                    int funcIndex = scanner.nextInt() - 1;
                    scanner.nextLine();
                    if (funcIndex < 0 || funcIndex >= functionIds.size()) {
                        System.out.println("Selección inválida.");
                        break;
                    }
                    String[] parts = functionIds.get(funcIndex).split("-");
                    Movie selectedMovie = movieService.getAllMovies().stream().filter(m -> m.getId().equals(parts[0])).findFirst().orElse(null);
                    CinemaRoom selectedRoom = roomService.getAllRooms().stream().filter(r -> r.getNombre().equals(parts[1])).findFirst().orElse(null);
                    System.out.print("Nombre del cliente: ");
                    String nombreCliente = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Teléfono: ");
                    String telefono = scanner.nextLine();
                    Customer customer = new Customer("C" + (reservationService.getAllReservations().size() + 1), nombreCliente, email, telefono);
                    System.out.println("Asientos disponibles:");
                    for (Seat s : selectedRoom.getAsientos()) {
                        System.out.print(s + " ");
                    }
                    System.out.println();
                    System.out.print("Ingrese los asientos a reservar (ej: A1 B2): ");
                    String seatsInput = scanner.nextLine();
                    String[] seatIds = seatsInput.split("\\s+");
                    List<Seat> selectedSeats = new ArrayList<>();
                    for (String seatId : seatIds) {
                        Seat seat = selectedRoom.getAsientos().stream()
                                .filter(s -> (s.getFila().charAt(5) + String.valueOf(s.getNumero())).equals(seatId))
                                .findFirst().orElse(null);
                        if (seat != null) {
                            selectedSeats.add(seat);
                        } else {
                            System.out.println("Asiento " + seatId + " no encontrado.");
                        }
                    }
                    if (selectedSeats.isEmpty()) {
                        System.out.println("No se seleccionaron asientos válidos.");
                        break;
                    }
                    System.out.println("Estrategias de precio: 1. Normal, 2. Estudiante, 3. Tercera Edad");
                    System.out.print("Seleccione estrategia (número): ");
                    int strategyChoice = scanner.nextInt();
                    scanner.nextLine();
                    PricingStrategy strategy;
                    switch (strategyChoice) {
                        case 2:
                            strategy = new PricingStrategy.StudentPricing();
                            break;
                        case 3:
                            strategy = new PricingStrategy.SeniorPricing();
                            break;
                        default:
                            strategy = new PricingStrategy.NormalPricing();
                    }
                    Screening screening = ScreeningFactory.createScreening("2D", selectedMovie, selectedRoom, java.time.LocalDateTime.now());
                    // Add observer to screening
                    screening.addObserver(new EmailNotificationObserver());
                    ReservationBuilder builder = new ReservationBuilder()
                            .setCustomer(customer)
                            .setScreening(screening)
                            .setPricingStrategy(strategy);
                    for (Seat seat : selectedSeats) {
                        builder.addSeat(seat);
                    }
                    Reservation reservation = builder.build();
                    // Apply discount using strategy
                    strategy.applyDiscount(reservation);
                    // Add decorators
                    System.out.println("Servicios adicionales: 1. Combo, 2. VIP, 3. Promoción");
                    System.out.print("Seleccione servicio (número, 0 para ninguno): ");
                    int decoratorChoice = scanner.nextInt();
                    scanner.nextLine();
                    if (decoratorChoice == 1) {
                        PricingDecorator decorator = new PricingDecorator.ComboPricingDecorator(reservation);
                        reservation.setPrecioTotal(decorator.getPrice());
                        System.out.println("[APLICANDO PATRÓN DECORATOR] Servicio Combo agregado.");
                    } else if (decoratorChoice == 2) {
                        PricingDecorator decorator = new PricingDecorator.VIPPricingDecorator(reservation);
                        reservation.setPrecioTotal(decorator.getPrice());
                        System.out.println("[APLICANDO PATRÓN DECORATOR] Servicio VIP agregado.");
                    } else if (decoratorChoice == 3) {
                        PricingDecorator decorator = new PricingDecorator.PromotionPricingDecorator(reservation);
                        reservation.setPrecioTotal(decorator.getPrice());
                        System.out.println("[APLICANDO PATRÓN DECORATOR] Promoción aplicada.");
                    }
                    // Use Command to create reservation
                    ReservationCommand.CreateReservationCommand createCommand = new ReservationCommand.CreateReservationCommand(reservation, reservationService);
                    createCommand.ejecutar();
                    System.out.println("[APLICANDO PATRÓN COMMAND] Reserva creada usando comando.");
                    System.out.println("Reserva creada: ID " + reservation.getId() + ", Precio total: $" + reservation.getPrecioTotal());
                    break;
                case 3:
                    System.out.println("\n--- Gestionar Reserva ---");
                    System.out.print("ID de Reserva: ");
                    String resId = scanner.nextLine();
                    Reservation res = reservationService.getAllReservations().stream().filter(r -> r.getId().equals(resId)).findFirst().orElse(null);
                    if (res == null) {
                        System.out.println("Reserva no encontrada.");
                        break;
                    }
                    System.out.println("Reserva " + res.getId() + ". Estado actual: " + res.getEstado().toString());
                    System.out.print("¿Desea confirmar (C) o cancelar (X)?: ");
                    String action = scanner.nextLine().toUpperCase();
                    if (action.equals("C")) {
                        ReservationState.EstadoConfirmada estadoConfirmada = new ReservationState.EstadoConfirmada();
                        res.setEstado(estadoConfirmada);
                        res.getEstado().handle(res);
                        System.out.println("[APLICANDO PATRÓN STATE] Transición: Reserva " + res.getId() + " -> Confirmada.");
                    } else if (action.equals("X")) {
                        ReservationState.EstadoCancelada estadoCancelada = new ReservationState.EstadoCancelada();
                        res.setEstado(estadoCancelada);
                        res.getEstado().handle(res);
                        System.out.println("[APLICANDO PATRÓN STATE] Transición: Reserva " + res.getId() + " -> Cancelada.");
                    } else {
                        System.out.println("Acción no válida.");
                    }
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}

