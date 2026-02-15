package com.nicole.conflicttracker.service.impl;

import com.nicole.conflicttracker.dto.FactionRequestDto;
import com.nicole.conflicttracker.dto.FactionResponseDto;
import com.nicole.conflicttracker.entity.Conflict;
import com.nicole.conflicttracker.entity.Country;
import com.nicole.conflicttracker.entity.Faction;
import com.nicole.conflicttracker.mapper.FactionMapper;
import com.nicole.conflicttracker.repository.ConflictRepository;
import com.nicole.conflicttracker.repository.CountryRepository;
import com.nicole.conflicttracker.repository.FactionRepository;
import com.nicole.conflicttracker.service.FactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class FactionServiceImpl implements FactionService {

    private final FactionRepository factionRepository;
    private final ConflictRepository conflictRepository;
    private final CountryRepository countryRepository;

    public FactionServiceImpl(FactionRepository factionRepository,
                              ConflictRepository conflictRepository,
                              CountryRepository countryRepository) {
        this.factionRepository = factionRepository;
        this.conflictRepository = conflictRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<FactionResponseDto> getAll() {
        return factionRepository.findAll()
                .stream()
                .map(FactionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public FactionResponseDto getById(Long id) {
        Faction faction = factionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Faction not found: " + id));
        return FactionMapper.toDto(faction);
    }

    @Override
    public FactionResponseDto create(FactionRequestDto dto) {
        Conflict conflict = conflictRepository.findById(dto.getConflictId())
                .orElseThrow(() -> new RuntimeException("Conflict not found: " + dto.getConflictId()));

        Set<Country> countries = resolveCountries(dto.getCountryCodes());

        Faction faction = new Faction();
        faction.setName(dto.getName());
        faction.setConflict(conflict);
        faction.setCountries(countries);

        Faction saved = factionRepository.save(faction);
        return FactionMapper.toDto(saved);
    }

    @Override
    public FactionResponseDto update(Long id, FactionRequestDto dto) {
        Faction faction = factionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Faction not found: " + id));

        Conflict conflict = conflictRepository.findById(dto.getConflictId())
                .orElseThrow(() -> new RuntimeException("Conflict not found: " + dto.getConflictId()));

        Set<Country> countries = resolveCountries(dto.getCountryCodes());

        faction.setName(dto.getName());
        faction.setConflict(conflict);
        faction.setCountries(countries);

        return FactionMapper.toDto(faction);
    }

    @Override
    public void delete(Long id) {
        if (!factionRepository.existsById(id)) {
            throw new RuntimeException("Faction not found: " + id);
        }
        factionRepository.deleteById(id);
    }

    private Set<Country> resolveCountries(Set<String> codes) {
        if (codes == null || codes.isEmpty()) {
            return new HashSet<>();
        }

        List<Country> found = countryRepository.findAll().stream()
                .filter(c -> codes.contains(c.getCode()))
                .toList();

        Set<String> foundCodes = found.stream().map(Country::getCode).collect(Collectors.toSet());
        Set<String> missing = new HashSet<>(codes);
        missing.removeAll(foundCodes);

        if (!missing.isEmpty()) {
            throw new RuntimeException("Unknown country codes: " + missing);
        }

        return new HashSet<>(found);
    }
}