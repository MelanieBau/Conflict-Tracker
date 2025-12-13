package com.nicole.conflicttracker.controller;

import com.nicole.conflicttracker.dto.*;
import com.nicole.conflicttracker.entity.ConflictStatus;
import com.nicole.conflicttracker.service.ConflictService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/conflicts")
public class ConflictController {

    private final ConflictService service;

    public ConflictController(ConflictService service) {
        this.service = service;
    }

    @GetMapping
    public List<ConflictResponseDto> getAll(@RequestParam Optional<ConflictStatus> status) {
        return service.getAll(status);
    }

    @GetMapping("/{id}")
    public ConflictResponseDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ConflictResponseDto create(@RequestBody ConflictRequestDto dto) {
        return service.create(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}