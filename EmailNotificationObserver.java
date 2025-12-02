package com.cinema.patterns.behavioral;

import com.cinema.model.Screening;

/**
 * Concrete observer for email notifications.
 */
public class EmailNotificationObserver implements NotificationObserver {
    @Override
    public void update(Screening screening) {
        System.out.println("Notificación por email: Cambios en la función " + screening.getPelicula().getTitulo());
    }
}
