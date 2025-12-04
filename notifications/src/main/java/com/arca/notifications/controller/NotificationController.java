package com.arca.notifications.controller;

import com.arca.notifications.models.RequestNotification;
import com.arca.notifications.service.TwilioNotificationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/notificaciones")
public class NotificationController {

    private final TwilioNotificationService twilioService;

    public NotificationController(TwilioNotificationService twilioService) {
        this.twilioService = twilioService;
    }

    @PostMapping("/sms")
    public Mono<String> enviarSms(@Valid @RequestBody RequestNotification request) {
        return twilioService.sendSms(request.getCelphone(), request.getMesage());
    }

    @PostMapping("/whatsapp")
    public Mono<String> enviarWhatsApp(@Valid @RequestBody RequestNotification request) {
        return twilioService.sendWhatsApp(request.getCelphone(), request.getMesage());
    }

    @PostMapping("/mensajediego")
    public Mono<String> pruebadiego(@Valid @RequestBody RequestNotification request) {
        return twilioService.sendSms(request.getCelphone(), request.getMesage());
    }
}
