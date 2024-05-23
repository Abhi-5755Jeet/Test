package com.borisns.securitydemo.controller;



import com.borisns.securitydemo.model.EventDetails;
import com.borisns.securitydemo.service.EventDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
public class EventManagementController {

        @Autowired
        private EventDetailsService eventService;


        @GetMapping("/get-all")
        public ResponseEntity<List<EventDetails>> getAllEvents() {
            return ResponseEntity.ok(eventService.findAll());
        }

        @GetMapping("getbyid/{id}")
        public ResponseEntity<EventDetails> getEventById(@PathVariable Long id) {
            Optional<EventDetails> event = eventService.findById(id);
            return event.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        }

        @PostMapping("/addevent")
        public EventDetails addEvent(@RequestBody EventDetails event) {
            return eventService.save(event);
        }

    @PutMapping("update/{id}")
    public ResponseEntity<EventDetails> updateEvent(@PathVariable Long id, @RequestBody EventDetails eventDetails) {
        Optional<EventDetails> optionalEvent = eventService.findById(id);
        if (optionalEvent.isPresent()) {
            EventDetails event = optionalEvent.get();
            event.setName(eventDetails.getName());
            event.setDate(eventDetails.getDate());
            event.setLocation(eventDetails.getLocation());

            final EventDetails updatedEvent = eventService.save(event);
            return ResponseEntity.ok(updatedEvent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

        @DeleteMapping("delete/{id}")
        public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
            eventService.deleteById(id);
            return ResponseEntity.noContent().build();
        }

        @DeleteMapping("/deleteall")
        public ResponseEntity<Void> deleteAllEvent() {
            eventService.deleteAll();
            return ResponseEntity.noContent().build();
        }
    }





