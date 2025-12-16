package com.nicole.conflicttracker.repository;

import com.nicole.conflicttracker.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}