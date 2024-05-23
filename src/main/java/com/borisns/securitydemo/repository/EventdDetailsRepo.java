package com.borisns.securitydemo.repository;

import com.borisns.securitydemo.model.EventDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventdDetailsRepo extends JpaRepository<EventDetails, Long> {
}

