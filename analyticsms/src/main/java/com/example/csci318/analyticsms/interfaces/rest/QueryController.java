package com.example.csci318.analyticsms.interfaces.rest;

import com.example.csci318.analyticsms.applicationservice.InteractiveQuery;
import com.example.csci318.analyticsms.interfaces.rest.dto.BookingsByHotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/queries")
public class QueryController {

    @Autowired
    private final InteractiveQuery interactiveQuery;

    @Autowired
    public QueryController(InteractiveQuery interactiveQuery) {
        this.interactiveQuery = interactiveQuery;
    }

    @GetMapping("/findAllBookingsByHotel")
    @ResponseBody
    public List<BookingsByHotel> findAllBookingsByCity() {
        return interactiveQuery.getAllBookingsByHotel();
    }

    @GetMapping("/testEndpoint")
    public String testEndpoint() {
        return "Routing works!";
    }
}
