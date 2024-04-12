package com.luciorim.notificationservice.message;

import com.luciorim.notificationservice.event.OrderPlacedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaListeners {

    @KafkaListener(topics = "notificationTopic")
    public void handleNotificationMessage(OrderPlacedEvent orderPlacedEvent){



    }
}

