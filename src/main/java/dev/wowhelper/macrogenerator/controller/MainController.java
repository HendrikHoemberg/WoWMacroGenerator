package dev.wowhelper.macrogenerator.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dev.wowhelper.macrogenerator.model.Macro;
import dev.wowhelper.macrogenerator.model.MacroType;
import dev.wowhelper.macrogenerator.service.MacroGeneratorService;



@Controller
public class MainController {

    private static final Logger LOG = LoggerFactory.getLogger(MainController.class);
    private final MacroGeneratorService macroGeneratorService;

    public MainController(MacroGeneratorService macroGeneratorService) {
        this.macroGeneratorService = macroGeneratorService;
    }
    
    @GetMapping("/")
    public String showMainPage(Model model) {
        model.addAttribute("macros", macroGeneratorService.getAllMacros());
        model.addAttribute("macroTypes", MacroType.values());
        return "index";
    }
    
    @PostMapping("/createMacro")
    public String createNewMacro(Model model, @RequestParam String spellName, @RequestParam MacroType macroType) {
        if (spellName != null && !spellName.trim().isEmpty()) {
            macroGeneratorService.addNewMacro(spellName, macroType);
            LOG.info("Created new macro for spell: {} with type: {}", spellName, macroType);
        }
        model.addAttribute("macros", macroGeneratorService.getAllMacros());
        
        return "index :: macro-list";
    }

    @DeleteMapping("/deleteMacro")
    public String deleteMacro(Model model, @RequestParam long id) {
        try {
            macroGeneratorService.deleteMacroById(id);
        } catch (Exception e) {
            LOG.warn("Failed to delete macro with id: {}", id, e);
        }
        List<Macro> remainingMacros = macroGeneratorService.getAllMacros();
        model.addAttribute("macros", remainingMacros);

        if (remainingMacros.isEmpty()) {
            return "index :: macro-list";
        }
        
        return "empty-fragment";
    }
    
} 
 