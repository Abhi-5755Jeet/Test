package com.borisns.securitydemo.service;


import com.borisns.securitydemo.model.EventDetails;

import java.util.List;
import java.util.Optional;

public interface EventDetailsService {
    List<EventDetails> findAll();
    Optional<EventDetails> findById(Long id);
    EventDetails save(EventDetails event);
    void deleteById(Long id);

    void deleteAll();
}
