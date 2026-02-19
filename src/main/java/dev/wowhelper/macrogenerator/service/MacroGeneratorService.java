package dev.wowhelper.macrogenerator.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.wowhelper.macrogenerator.model.Macro;
import dev.wowhelper.macrogenerator.model.MacroType;
import dev.wowhelper.macrogenerator.repository.MacroRepository;

@Service
public class MacroGeneratorService {

    private final MacroRepository macroRepository;
    
    public MacroGeneratorService(MacroRepository macroRepository) {
        this.macroRepository = macroRepository;
    }

    public List<Macro> getAllMacros() {
        return macroRepository.findAll();
    }

    public void addNewMacro(String spellName, MacroType macroType) {
        Macro newMacro = new Macro();
        newMacro.setType(macroType);
        newMacro.setCommand(generateMacroCommand(spellName, newMacro.getType()));
        
        macroRepository.save(newMacro);
    }

    public void deleteMacroById(Long macroId) {
        macroRepository.deleteById(macroId);
    }

    private String generateMacroCommand(String spellName, MacroType macroType) {
        String macroCommand = "/cast ";

        switch(macroType) {
            case FOCUS:
                macroCommand += "[@Focus] ";
                break;
            case PLAYER:
                macroCommand += "[@Player] ";
                break;
            case CURSOR:
                macroCommand += "[@Cursor] ";
                break;
        }

        macroCommand += spellName;

        return macroCommand;
    }
}
