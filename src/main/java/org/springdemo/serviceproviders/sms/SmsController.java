package org.springdemo.serviceproviders.sms;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sms")
@RequiredArgsConstructor
public class SmsController {

    private final SmsService smsService;

    @PostMapping("/send")
    public String sendSms(@RequestParam String to, @RequestParam String message) {
        return smsService.sendSms(to, message);
    }
}