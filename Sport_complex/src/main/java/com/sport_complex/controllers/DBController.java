package com.sport_complex.controllers;

import com.sport_complex.models.Ticket;
import com.sport_complex.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class DBController {

    TicketRepository repository;

    @GetMapping("/all/tickets")
    public String ticketsList(Model model) {
        Iterable<Ticket> tickets = repository.findAll();
        model.addAttribute("tickets", tickets);
        return "ticket_list";
    }

    @GetMapping("/add/new/ticket")
    public String addTopic() {
        return "ticket_add";
    }

    @PostMapping("/add/new/ticket")
    public String postNewTicket(
            @RequestParam String userName,
            @RequestParam Integer userAge,
            @RequestParam String ticketType) {
        Ticket ticket = new Ticket(userName, userAge, ticketType);
        repository.save(ticket);
        return "redirect:/all/tickets";
    }
}
