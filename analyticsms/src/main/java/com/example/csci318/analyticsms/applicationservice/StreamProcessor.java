package com.example.csci318.analyticsms.applicationservice;

import com.example.csci318.shareddomain.events.BookingEvent;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.KeyValueStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class StreamProcessor {
    public final static String TOTAL_BOOKINGS = "total-bookings";

    @Bean
    public Consumer<KStream<String, BookingEvent>> process() {
        return inputStream -> {
            KTable<String, Long> totalBookings = inputStream
                    .map((key, value) -> KeyValue.pair(value.getBookingEventData().getHotelName(), 1L))
                    .groupByKey(Grouped.with(Serdes.String(), Serdes.Long()))
                    .reduce(Long::sum,
                            Materialized.<String, Long, KeyValueStore<Bytes, byte[]>>as(TOTAL_BOOKINGS)
                                    .withKeySerde(Serdes.String())
                                    .withValueSerde(Serdes.Long()));

            // Output results
            totalBookings.toStream().print(Printed.<String, Long>toSysOut().withLabel("Total bookings by hotel"));
        };
    }
}
