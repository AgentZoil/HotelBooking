package com.example.csci318.interfaces.rest;

import com.example.csci318.analyticsms.applicationservice.InteractiveQuery;
import com.example.csci318.interfaces.rest.dto.BookingsByHotel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller    // This means that this class is a Controller
@RequestMapping("/queries")
public class QueryController {

    private final InteractiveQuery interactiveQuery;

    public QueryController(InteractiveQuery interactiveQuery) {
        this.interactiveQuery = interactiveQuery;
    }

    @GetMapping("/findAllBookingsByHotel")
    @ResponseBody
    public List<BookingsByHotel> findAllBookingsByCity() {
        return interactiveQuery.getAllBookingsByHotel();
    }
}
