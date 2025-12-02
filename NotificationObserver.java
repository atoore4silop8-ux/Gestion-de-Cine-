package com.cinema.patterns.behavioral;

import com.cinema.model.Screening;

/**
 * Observer pattern for notifications.
 */
public interface NotificationObserver {
    void update(Screening screening);
}
