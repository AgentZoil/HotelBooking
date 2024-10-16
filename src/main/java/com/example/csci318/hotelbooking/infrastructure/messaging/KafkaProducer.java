package com.example.csci318.hotelbooking.infrastructure.messaging;

import com.example.csci318.hotelbooking.domain.event.BookingEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    public void sendBookingEvent(BookingEvent event){
        try {
            String eventJson = objectMapper.writeValueAsString(event);
            kafkaTemplate.send("booking_topic", eventJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
