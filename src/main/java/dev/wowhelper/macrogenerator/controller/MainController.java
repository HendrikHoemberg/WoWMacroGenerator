package dev.wowhelper.macrogenerator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dev.wowhelper.macrogenerator.model.MacroType;
import dev.wowhelper.macrogenerator.service.MacroGeneratorService;



@Controller
public class MainController {

    private final MacroGeneratorService macroGeneratorService;

    public MainController(MacroGeneratorService macroGeneratorService) {
        this.macroGeneratorService = macroGeneratorService;
    }
    
    @GetMapping("/")
    public String showMainPage(Model model) {
        model.addAttribute("macros", macroGeneratorService.getAllMacros());
        model.addAttribute("macro-types", MacroType.values());
        return "index";
    }
    
    @PostMapping("/createMacro")
    public String createNewMacro(Model model, @RequestParam  String spellName, @RequestParam MacroType macroType) {
        macroGeneratorService.addNewMacro(spellName, macroType);
        model.addAttribute("macros", macroGeneratorService.getAllMacros());
        
        return "index :: macro-list";
    }
    
}
