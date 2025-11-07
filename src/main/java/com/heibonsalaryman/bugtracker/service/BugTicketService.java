package com.heibonsalaryman.bugtracker.service;

import com.heibonsalaryman.bugtracker.model.BugTicket;
import com.heibonsalaryman.bugtracker.model.BugTicketRegistration;
import com.heibonsalaryman.bugtracker.repository.BugTicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BugTicketService {

    private final BugTicketRepository repository;

    public BugTicketService(BugTicketRepository repository) {
        this.repository = repository;
    }

    public List<BugTicket> findAll() {
        return repository.findAll();
    }

    @Transactional
    public long register(BugTicketRegistration registration) {
        return repository.insert(registration);
    }
}
