package com.nicole.conflicttracker.controller;

import com.nicole.conflicttracker.dto.FactionRequestDto;
import com.nicole.conflicttracker.dto.FactionResponseDto;
import com.nicole.conflicttracker.service.FactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/factions")
public class FactionController {

    private final FactionService factionService;

    public FactionController(FactionService factionService) {
        this.factionService = factionService;
    }

    @GetMapping
    public List<FactionResponseDto> getAll() {
        return factionService.getAll();
    }

    @GetMapping("/{id}")
    public FactionResponseDto getById(@PathVariable Long id) {
        return factionService.getById(id);
    }

    @PostMapping
    public ResponseEntity<FactionResponseDto> create(@RequestBody FactionRequestDto dto) {
        FactionResponseDto created = factionService.create(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public FactionResponseDto update(@PathVariable Long id, @RequestBody FactionRequestDto dto) {
        return factionService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        factionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}