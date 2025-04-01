package com.billing_system.notification.Services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {
    private String twilioPhoneNumber = "+13305835239";

    @PostConstruct
    public void initTwilio() {
        Twilio.init("", "");
    }
    public String sendSms(String destinationPhone, String messageText){

        if (!destinationPhone.startsWith("+")) {
            destinationPhone = "+57" + destinationPhone;
        }
        Message message = Message.creator(
                        new PhoneNumber(destinationPhone),
                        new PhoneNumber(twilioPhoneNumber),
                        messageText)
                .create();
        return message.getSid();
    }
}
