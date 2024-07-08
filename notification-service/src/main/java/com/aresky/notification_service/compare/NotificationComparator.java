package com.aresky.notification_service.compare;

import com.aresky.notification_service.entity.Notification;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class NotificationComparator implements Comparator<Notification> {
    @Override
    public int compare(Notification notification, Notification t1) {

        if(notification.getCreatedAt().isBefore(t1.getCreatedAt())){
            return 1;
        }

        if(notification.getCreatedAt().isAfter(t1.getCreatedAt())){
            return -1;
        }

        return 0;
    }
}
