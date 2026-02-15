package com.nicole.conflicttracker.web;

import com.nicole.conflicttracker.service.ConflictService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/web/conflicts")
public class ConflictWebController {

    private final ConflictService conflictService;

    public ConflictWebController(ConflictService conflictService) {
        this.conflictService = conflictService;
    }

    @GetMapping
    public String listConflicts(Model model) {
        model.addAttribute("conflicts", conflictService.getAll(Optional.empty()));
        return "conflicts";
    }
}