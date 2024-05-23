package com.borisns.securitydemo.service.impl;

import com.borisns.securitydemo.model.EventDetails;
import com.borisns.securitydemo.repository.EventdDetailsRepo;
import com.borisns.securitydemo.service.EventDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventDetailsServiceImpl implements EventDetailsService {


        @Autowired
        private EventdDetailsRepo eventRepository;

        @Override
        public List<EventDetails> findAll() {
            return eventRepository.findAll();
        }

        @Override
        public Optional<EventDetails> findById(Long id) {
            return eventRepository.findById(id);
        }

    @Override
    public EventDetails save(EventDetails event) {
            eventRepository.save(event);
        return event ;
    }

        @Override
        public void deleteById(Long id) {
            eventRepository.deleteById(id);
        }

    @Override
    public void deleteAll() {
        eventRepository.deleteAll();
    }


}
