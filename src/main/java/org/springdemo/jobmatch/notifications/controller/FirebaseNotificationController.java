package org.springdemo.jobmatch.notifications.controller;

import lombok.RequiredArgsConstructor;
import org.springdemo.jobmatch.notifications.service.FirebaseNotificationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("notification")
@RequiredArgsConstructor
public class FirebaseNotificationController {

    private final FirebaseNotificationService notificationService;

    @PostMapping("send")
    public String sendNotification(@RequestParam String title,
                                   @RequestParam String body,
                                   @RequestParam String token) {

        return notificationService.sendNotification(title, body, token);
    }
}
