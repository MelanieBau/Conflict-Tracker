package com.nicole.conflicttracker.repository;

import com.nicole.conflicttracker.entity.Faction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactionRepository extends JpaRepository<Faction, Long> {
}