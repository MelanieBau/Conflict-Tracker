
package com.nicole.conflicttracker.controller;

import com.nicole.conflicttracker.dto.ConflictResponseDto;
import com.nicole.conflicttracker.entity.Country;
import com.nicole.conflicttracker.repository.CountryRepository;
import com.nicole.conflicttracker.service.ConflictService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/countries")
public class CountryController {

    private final ConflictService conflictService;
    private final CountryRepository countryRepository;

    public CountryController(ConflictService conflictService, CountryRepository countryRepository) {
        this.conflictService = conflictService;
        this.countryRepository = countryRepository;
    }

    @GetMapping
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @GetMapping("/{code}/conflicts")
    public List<ConflictResponseDto> getConflictsByCountry(@PathVariable String code) {
        return conflictService.getByCountryCode(code);
    }
}