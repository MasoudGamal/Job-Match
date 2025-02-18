package org.springdemo.jobmatch.notifications.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.stereotype.Service;

@Service
public class FirebaseNotificationService {

    public String sendNotification(String title, String body, String token) {
        try {
            // إنشاء إشعار جديد
            Notification notification = Notification.builder()
                    .setTitle(title)
                    .setBody(body)
                    .build();

            // إنشاء الرسالة
            Message message = Message.builder()
                    .setToken(token)
                    .setNotification(notification)
                    .build();

            // إرسال الرسالة باستخدام FirebaseMessaging
            String response = FirebaseMessaging.getInstance().send(message);

            // طباعة الرد عند النجاح
            System.out.println("Notification sent successfully: " + response);
            return response;
        } catch (Exception e) {
            // طباعة رسالة خطأ
            System.err.println("Failed to send notification: " + e.getMessage());
            return "Error: " + e.getMessage();
        }
    }
}
