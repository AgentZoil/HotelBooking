package com.example.csci318.hotelbooking;

import com.example.csci318.hotelbooking.domain.model.Hotel;
import com.example.csci318.hotelbooking.domain.model.Room;
import com.example.csci318.hotelbooking.infrastructure.repository.HotelRepository;
import com.example.csci318.hotelbooking.infrastructure.repository.RoomRepository;
import com.example.csci318.hotelbooking.domain.service.HotelService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class HotelbookingApplication {

	public static void main(String[] args) {
		org.springframework.boot.SpringApplication.run(HotelbookingApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner loadDatabase(HotelRepository hotelRepository, RoomRepository roomRepository, HotelService hotelService){
		return args -> {
//			Create a hotel at start
			Hotel entryHotel1 = new Hotel();
			entryHotel1.setLocation("Somewhere nice");
			entryHotel1.setDescription("This is a nice hotel");
			entryHotel1.setName("Nice hotel 1");
			entryHotel1.setPricePerNight(100);

			hotelRepository.save(entryHotel1);

//			Create 2 rooms at start
			Room entryRoom1 = new Room();
			entryRoom1.setAvailability(true);
//			entryRoom1.setHotel(entryHotel1);
			entryRoom1.setType("type 1");
			entryRoom1.setPrice(100);
			entryRoom1.setRoomNumber("001");
			entryRoom1.locatedAt(entryHotel1);

			roomRepository.save(entryRoom1);


			System.out.println(roomRepository.findById(entryRoom1.getId()).orElseThrow()); // print the room

			Room entryRoom2 = new Room();
			entryRoom2.setAvailability(true);
//			entryRoom2.setHotel(entryHotel1);
			entryRoom2.setType("type 2");
			entryRoom2.setPrice(150);
			entryRoom2.setRoomNumber("002");
			entryRoom2.locatedAt(entryHotel1);

			roomRepository.save(entryRoom2);


			System.out.println(roomRepository.findById(entryRoom2.getId()).orElseThrow()); // print the room

			Hotel entryHotel1_1 = hotelRepository.findById(entryHotel1.getId())
					.orElseThrow();

			entryHotel1_1.addAvailableRoom(entryRoom1.getId());
			entryHotel1_1.addAvailableRoom(entryRoom2.getId());

//			hotelService.updateHotel(entryRoom1.getId(), entryHotel1_1);
			// TEST
			hotelRepository.save(entryHotel1_1);
			System.out.println(hotelRepository.findById(entryHotel1.getId()).orElseThrow()); // print the hotel
		};
	}
}
