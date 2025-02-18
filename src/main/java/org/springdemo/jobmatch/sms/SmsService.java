package org.springdemo.jobmatch.sms;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SmsService {

    private final TwilioConfig twilioConfig;

    public String sendSms(String to, String messageBody) {
        try {
            Message message = Message.creator(
                            new PhoneNumber("+20"+  to),                      // رقم المستلم
                            new PhoneNumber(twilioConfig.getTwilioPhoneNumber()), // رقم Twilio
                            messageBody)                              // نص الرسالة
                    .create();



            return "Message sent successfully with SID: " + message.getSid();
        } catch (Exception e) {
            return "Failed to send SMS: " + e.getMessage();
        }
    }
}
