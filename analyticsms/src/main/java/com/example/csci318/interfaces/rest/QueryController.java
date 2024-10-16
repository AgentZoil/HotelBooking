package com.example.csci318.interfaces.rest;

import com.example.csci318.analyticsms.applicationservice.InteractiveQuery;
import com.example.csci318.interfaces.rest.dto.BookingsByHotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/queries")
public class QueryController {

    @Autowired
    private final InteractiveQuery interactiveQuery;

    public QueryController(InteractiveQuery interactiveQuery) {
        this.interactiveQuery = interactiveQuery;
    }

    @GetMapping("/findAllBookingsByHotel")
    @ResponseBody
    public List<BookingsByHotel> findAllBookingsByCity() {
        return interactiveQuery.getAllBookingsByHotel();
    }

    @GetMapping("/testEndpoint")
    @ResponseBody
    public String testEndpoint() {
        return "Routing works!";
    }
}
