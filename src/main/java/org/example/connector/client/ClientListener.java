package org.example.connector.client;


import javax.management.Notification;
import javax.management.NotificationListener;

public class ClientListener implements NotificationListener {
    @Override
    public void handleNotification(Notification notification, Object handback) {
        System.out.println("\nReceived notification: " + notification);
    }
//    public void handleNotification(Notification notification, Object handback) {
//        System.out.println("\nReceived notification: " + notification);
//    }
} 
 
