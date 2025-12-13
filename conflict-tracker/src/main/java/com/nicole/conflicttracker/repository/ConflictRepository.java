package com.nicole.conflicttracker.repository;

import com.nicole.conflicttracker.entity.*;
import org.springframework.data.jpa.repository.*;
import java.util.List;

public interface ConflictRepository extends JpaRepository<Conflict, Long> {

    List<Conflict> findByStatus(ConflictStatus status);

    @Query("select c from Conflict c join c.countries co where co.code = :code")
    List<Conflict> findByCountryCode(String code);
}