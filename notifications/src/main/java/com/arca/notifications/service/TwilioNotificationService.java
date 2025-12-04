package com.arca.notifications.service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class TwilioNotificationService {

    @Value("${twilio.phone-number}")
    private String fromPhone;

    @Value("${area.el.salvador}")
    private String area;

    public Mono<String> sendSms(String to, String messageBody) {
        String phone = area.concat(to);
        System.out.println("numero de telefono: ".concat(phone));
        System.out.println("numero de twilio: ".concat(fromPhone));
        return Mono.fromCallable(() -> {
            Message message = Message.creator(
                    new PhoneNumber(phone),      // Destinatario
                    new PhoneNumber(fromPhone), // Número Twilio
                    messageBody
            ).create();
            return message.getSid(); // Devuelve el ID del mensaje
        });
    }

    public Mono<String> sendWhatsApp(String to, String messageBody) {
        return Mono.fromCallable(() -> {
            Message message = Message.creator(
                    new PhoneNumber("whatsapp:" + to),
                    new PhoneNumber("whatsapp:" + fromPhone),
                    messageBody
            ).create();
            return message.getSid();
        });
    }
}
