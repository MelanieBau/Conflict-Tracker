package com.nicole.conflicttracker.repository;

import com.nicole.conflicttracker.entity.Conflict;
import com.nicole.conflicttracker.entity.ConflictStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConflictRepository extends JpaRepository<Conflict, Long> {

    List<Conflict> findByStatus(ConflictStatus status);

    @Query("select distinct c from Conflict c join c.countries co where co.code = :code")
    List<Conflict> findByCountryCode(@Param("code") String code);
}