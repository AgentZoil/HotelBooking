# Hotel Booking Application

## Introduction

Welcome to the Hotel Booking Application! This project is a simple but powerful tool, built with Java Spring Boot. This software will make reserving hotel rooms simple and effective. Whether you're a hotel administrator or a guest, this app has you covered. It helps manage hotel details, room availability, and bookings with ease. Think of it as a virtual assistant for your hotel booking needs.

## Main Functionalities

### For Hotel Administrators
- **Manage Hotels**: Add new hotels, update their details, or remove them when needed. It’s all about keeping your hotel info up-to-date.
- **Room Management**: Add new rooms to your hotels, update existing ones, or remove rooms that are no longer available. You can also check if rooms are free based on the dates and type.

### For Guests
- **Booking Rooms**: Make a new booking, update an existing one, or cancel if your plans change. You can see all your booking details, like check-in/check-out dates and room type.
- **Search and Filter**: Find hotels by location, price, and other criteria. You can also filter available rooms to match your preferences and dates.

### User Accounts (if included)
- **Account Management**: Register for an account, log in, and manage your bookings easily.

## Apache Kafka Setup
This Spring Boot project uses Apache Kafka as a messaging platform.
To run this project, you need to set up Kafka first.

#### Linux and MacOS
Download a **binary package** of Apache Kafka (e.g., `kafka_2.13-3.7.0.tgz`) from
[https://kafka.apache.org/downloads](https://kafka.apache.org/downloads)
and upzip it.
In the Terminal, `cd` to the unzip folder, and start Kakfa with the following commands (each in a separate Terminal session):
```bash
./bin/zookeeper-server-start.sh ./config/zookeeper.properties
```
```bash
./bin/kafka-server-start.sh ./config/server.properties
```

#### Windows
Download a **binary package** of Apache Kafka (e.g., `kafka_2.13-3.7.0.tgz`) from
[https://kafka.apache.org/downloads](https://kafka.apache.org/downloads)
and unzip it to a directory, e.g., `C:\kafka`&mdash;Windows does not like a complex path name (!).

<!--
In the configuration file `C:\kafka\config\zookeeper.properties`, comment out the line `"dataDir=/tmp/zookeeper"`. In `C:\kafka\config\server.properties`, change the line `"log.dirs=/tmp/kafka-logs"` to `"log.dirs=.kafka-logs"`.
-->

Use the following two commands in the Windows CMD (one in each window) to start Kafka:
```bash
C:\kafka\bin\windows\zookeeper-server-start.bat C:\kafka\config\zookeeper.properties
```
```bash
C:\kafka\bin\windows\kafka-server-start.bat C:\kafka\config\server.properties
```
### View Kafka Topics
After running the `HotelBookingApplication`'s main class, check the Kafka topics with the following command:

(Linux/MacOS)
```shell
./bin/kafka-topics.sh --bootstrap-server=localhost:9092 --list
```
(Windows)
```shell
C:\kafka\bin\windows\kafka-topics.bat --bootstrap-server=localhost:9092 --list
```
You should see a topic name `booking-events`. You can read data in the `booking-events` topic:

(Linux/MacOS)
```shell
./bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic booking-events --from-beginning
```
(Windows)
```shell
c:\kafka\bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic booking-events --from-beginning
```
#### REST Request for Interactive Query

Run the DemoClient and get the changing query results: 

(Linux/MacOS)
```shell
curl -X GET -H "Content-Type:application/json" http://localhost:8081/queries/findAllBookingsByHotel
```
(windows)
```shell
curl -X GET -H "Content-Type:application/json" http://localhost:8081/queries/findAllBookingsByHotel
```

## H2 Console
The console http://localhost:8080/h2-console/. To log on, change the value in the JDBC URL entry to jdbc:h2:mem:testdb.

## Getting Started

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/AgentZoil/HotelBooking.git

## CRUD Request Examples

### Hotels

- **Create a New Hotel**
    - In Windows CMD:
      ```shell
      curl -X POST -H "Content-Type:application/json" -d "{\"name\":\"Stockton Hotel\",\"location\":\"Ohio\",\"description\":\"A budget hotel in the heart of the city.\",\"pricePerNight\":50.0}" http://localhost:8080/hotels
      ```
    - In MacOS/Linux:
      ```bash
      curl -X POST -H "Content-Type:application/json" -d '{"name":"Stockton Hotel","location":"Ohio","description":"A budget hotel in the heart of the city.","pricePerNight":50.0}' http://localhost:8080/hotels
      ```
    - Response:
      ```json
      {"id":1,"name":"Stockton Hotel","location":"Ohio","description":"A budget hotel in the heart of the city.","pricePerNight":50.0,"rooms":null}
      ```

- **Retrieve All Hotels**
    - Request:
      ```shell
      curl -X GET http://localhost:8080/hotels
      ```
    - Response:
      ```json
      [
        {"id":1,"name":"Stockton Hotel","location":"Ohio","description":"A budget hotel in the heart of the city.","pricePerNight":50.0,"rooms":null}
      ]
      ```

- **Retrieve a Specific Hotel**
    - Request:
      ```shell
      curl -X GET http://localhost:8080/hotels/1
      ```
    - Response:
      ```json
      {"id":1,"name":"Stockton Hotel","location":"Ohio","description":"A budget hotel in the heart of the city.","pricePerNight":50.0,"rooms":null}
      ```

- **Update a Hotel**
    - In Windows CMD:
      ```shell
      curl -X PUT -H "Content-Type:application/json" -d "{\"name\":\"Stockton Hotel\",\"location\":\"Ohio\",\"description\":\"An updated description of the budget hotel.\",\"pricePerNight\":55.0}" http://localhost:8080/hotels/1
      ```
    - In MacOS/Linux:
      ```bash
      curl -X PUT -H "Content-Type:application/json" -d '{"name":"Stockton Hotel","location":"Ohio","description":"An updated description of the budget hotel.","pricePerNight":55.0}' http://localhost:8080/hotels/1
      ```
    - Response:
      ```json
      {"id":1,"name":"Stockton Hotel","location":"Ohio","description":"An updated description of the budget hotel.","pricePerNight":55.0,"rooms":[]}
      ```

- **Delete a Hotel**
    - Request:
      ```shell
      curl -X DELETE http://localhost:8080/hotels/1
      ```
    - Response:
      ```json
      {}
      ```
- **Find all available rooms of a Hotel**
    - Request:
      ```shell
      curl -X GET http://localhost:8080/hotels/1/availableRooms
      ```
    - Response:
      ```json
      [
          {
              "id": 1,
              "roomNumber": "001",
              "type": "type 1",
              "price": 100.0,
              "availability": true
          },
          {
              "id": 2,
              "roomNumber": "002",
              "type": "type 2",
              "price": 150.0,
              "availability": true
          }
      ]
      ```

### Rooms

- **Create a New Room**
    - In Windows CMD:
      ```shell
      curl -X POST -H "Content-Type:application/json" -d "{\"roomNumber\":\"007\",\"type\":\"Deluxe\",\"price\":150.0,\"availability\":true,\"hotel\":{\"id\":1}}" http://localhost:8080/rooms
      ```
    - In MacOS/Linux:
      ```bash
      curl -X POST -H "Content-Type:application/json" -d '{"roomNumber":"007","type":"Deluxe","price":150.0,"availability":true,"hotel":{"id":1}}' http://localhost:8080/rooms
      ```
    - Response:
      ```json
      {"id":1,"roomNumber":"007","type":"Deluxe","price":150.0,"availability":true}
      ```

- **Retrieve All Rooms**
    - Request:
      ```shell
      curl -X GET http://localhost:8080/rooms
      ```
    - Response:
      ```json
      [
        {"id":1,"roomNumber":"007","type":"Deluxe","price":150.0,"availability":true}
      ]
      ```

- **Retrieve a Specific Room**
    - Request:
      ```shell
      curl -X GET http://localhost:8080/rooms/1
      ```
    - Response:
      ```json
      {"id":1,"roomNumber":"007","type":"Deluxe","price":150.0,"availability":true}
      ```

- **Update a Room**
    - In Windows CMD:
      ```shell
      curl -X PUT -H "Content-Type:application/json" -d "{\"roomNumber\":\"007\",\"type\":\"Suite\",\"price\":200.0,\"availability\":false,\"hotel\":{\"id\":1}}" http://localhost:8080/rooms/1
      ```
    - In MacOS/Linux:
      ```bash
      curl -X PUT -H "Content-Type:application/json" -d '{"roomNumber":"007","type":"Suite","price":200.0,"availability":false,"hotel":{"id":1}}' http://localhost:8080/rooms/1
      ```
    - Response:
      ```json
      {"id":1,"roomNumber":"007","type":"Suite","price":200.0,"availability":false}
      ```

- **Delete a Room**
    - Request:
      ```shell
      curl -X DELETE http://localhost:8080/rooms/1
      ```
    - Response:
      ```json
      {}
      ```

- **Find information of the Hotel where the room is located at**
    - Request:
      ```shell
      curl -X GET http://localhost:8080/rooms/1/hotels
      ```
    - Response:
      ```json
      {
          "id": 1,
          "name": "Nice hotel 1",
          "location": "Somewhere nice",
          "description": "This is a nice hotel",
          "pricePerNight": 100.0,
          "rooms": [
              {
                  "id": 1,
                  "roomNumber": "001",
                  "type": "type 1",
                  "price": 100.0,
                  "availability": true
              },
              {
                  "id": 2,
                  "roomNumber": "002",
                  "type": "type 2",
                  "price": 150.0,
                  "availability": true
              }
          ],
          "availableRooms": [
              1,
              2
          ]
      }
      ```
      
### Users

- **Create a New User**
    - In Windows CMD:
      ```shell
      curl -X POST -H "Content-Type:application/json" -d "{\"name\":\"Gia Bach Nhu\",\"email\":\"gbn447@uow.com\",\"password\":\"123\",\"phoneNumber\":\"0423398765\"}" http://localhost:8080/users
      ```
    - In MacOS/Linux:
      ```bash
      curl -X POST -H "Content-Type:application/json" -d '{"name":"Gia Bach Nhu","email":"gbn447@uow.com","password":"123","phoneNumber":"0423398765"}' http://localhost:8080/users
      ```
    - Response:
      ```json
      {"id":1,"name":"Gia Bach Nhu","email":"gbn447@uow.com","password":"123","phoneNumber":"0423398765"}
      ```

- **Retrieve All Users**
    - Request:
      ```shell
      curl -X GET http://localhost:8080/users
      ```
    - Response:
      ```json
      [
        {"id":1,"name":"Gia Bach Nhu","email":"gbn447@uow.com","password":"123","phoneNumber": "0423398765"}
      ]
      ```

- **Retrieve a Specific User**
    - Request:
      ```shell
      curl -X GET http://localhost:8080/users/1
      ```
    - Response:
      ```json
      [
          {
              "id": 1,
              "user": {
                  "id": 1,
                  "name": "Gia Bach Nhu",
                  "email": "gbn447@uow.com",
                  "password": "123",
                  "phoneNumber": "0423398765"
              },
              "hotel": {
                  "id": 1,
                  "name": "Nice hotel 1",
                  "location": "Somewhere nice",
                  "description": "This is a nice hotel",
                  "pricePerNight": 100.0,
                  "rooms": [
                      {
                          "id": 1,
                          "roomNumber": "001",
                          "type": "type 1",
                          "price": 100.0,
                          "availability": false
                      },
                      {
                          "id": 2,
                          "roomNumber": "002",
                          "type": "type 2",
                          "price": 150.0,
                          "availability": true
                      }
                  ],
                  "availableRooms": [
                      1,
                      2
                  ]
              },
              "room": {
                  "id": 1,
                  "roomNumber": "001",
                  "type": "type 1",
                  "price": 100.0,
                  "availability": false
              },
              "checkInDate": "2024-08-01T00:00:00.000+00:00",
              "checkOutDate": "2024-08-07T00:00:00.000+00:00"
          }
      ]
      {"id":1,"name":"Gia Bach Nhu","email":"gbn447@uow.com","password":"123","phoneNumber": "0423398765"}
      ```

- **Update a User**
    - In Windows CMD:
      ```shell
      curl -X PUT -H "Content-Type:application/json" -d "{\"name\":\"Gia Bach Nhu\",\"email\":\"gbn447@uow.com\",\"password\":\"newpassword\"}" http://localhost:8080/users/1
      ```
    - In MacOS/Linux:
      ```bash
      curl -X PUT -H "Content-Type:application/json" -d '{"name":"Gia Bach Nhu","email":"gbn447@uow.com","password":"newpassword"}' http://localhost:8080/users/1
      ```
    - Response:
      ```json
      {"id":1,"name":"Gia Bach Nhu","email":"gbn447@uow.com","password":"newpassword","phoneNumber": "0423398765"}
      ```

- **Delete a User**
    - Request:
      ```shell
      curl -X DELETE http://localhost:8080/users/1
      ```
    - Response:
      ```json
      {}
      ```
### Bookings

- **Create a New Booking**
    - In Windows CMD:
      ```shell
      curl -X POST -H "Content-Type:application/json" \
      -d '{
        "checkInDate": "2024-08-01",
        "checkOutDate": "2024-08-07",
        "hotel": { "id": 1 },
        "room": { "id": 1 },
        "user": { "id": 1 }
      }' \
      http://localhost:8080/bookings


      ```
    - In MacOS/Linux:
      ```bash
      curl -X POST -H "Content-Type:application/json" -d '{"checkInDate":"2024-08-01","checkOutDate":"2024-08-07","hotel": {"id": 1 },"room": {"id": 1 }' http://localhost:8080/bookings
      ```
    - Response:
      ```json
      {"id":1,"user":null,"hotel":{"id":1,"name":null,"location":null,"description":null,"pricePerNight":0.0,"rooms":null},"room":{"id":1,"roomNumber":null,"type":null,"price":0.0,"availability":false},"checkInDate":"2024-08-01T00:00:00.000+00:00","checkOutDate":"2024-08-07T00:00:00.000+00:00"}
      ```

- **Retrieve All Bookings**
    - Request:
      ```shell
      curl -X GET http://localhost:8080/bookings
      ```
    - Response:
      ```json
      [
        {"id":1,"user":null,"hotel":{"id":1,"name":"Stockton Hotel","location":"Ohio","description":"An updated description of the budget hotel.","pricePerNight":55.0,"rooms":[{"id":1,"roomNumber":"007","type":"Suite","price":200.0,"availability":false}]},"room":{"id":1,"roomNumber":"007","type":"Suite","price":200.0,"availability":false},"checkInDate":"2024-08-01T00:00:00.000+00:00","checkOutDate":"2024-08-07T00:00:00.000+00:00"}
      ]
      ```

- **Retrieve a Specific Booking**
    - Request:
      ```shell
      curl -X GET http://localhost:8080/bookings/1
      ```
    - Response:
      ```json
      {"id":1,"user":null,"hotel":{"id":1,"name":"Stockton Hotel","location":"Ohio","description":"An updated description of the budget hotel.","pricePerNight":55.0,"rooms":[{"id":1,"roomNumber":"007","type":"Suite","price":200.0,"availability":false}]},"room":{"id":1,"roomNumber":"007","type":"Suite","price":200.0,"availability":false},"checkInDate":"2024-08-01T00:00:00.000+00:00","checkOutDate":"2024-08-07T00:00:00.000+00:00"}
      ```

- **Update a Booking**
    - In Windows CMD:
      ```shell
      curl -X PUT -H "Content-Type:application/json" -d "{\"checkInDate\":\"2024-08-05\",\"checkOutDate\":\"2024-08-10\",\"hotel\": {\"id\": 1 },\"room\": {\"id\": 1 }}" http://localhost:8080/bookings/1
      ```
    - In MacOS/Linux:
      ```bash
      curl -X PUT -H "Content-Type:application/json" -d '{"checkInDate":"2024-08-05","checkOutDate":"2024-08-10","hotel": {"id": 1 },"room": {"id": 1 }}' http://localhost:8080/bookings/1
      ```
    - Response:
      ```json
      {"id":1,"user":null,"hotel":{"id":1,"name":null,"location":null,"description":null,"pricePerNight":0.0,"rooms":null},"room":{"id":1,"roomNumber":null,"type":null,"price":0.0,"availability":false},"checkInDate":"2024-08-05T00:00:00.000+00:00","checkOutDate":"2024-08-10T00:00:00.000+00:00"}
      ```

- **Delete a Booking**
    - Request:
      ```shell
      curl -X DELETE http://localhost:8080/bookings/1
      ```
    - Response:
      ```json
      {}
      ```


