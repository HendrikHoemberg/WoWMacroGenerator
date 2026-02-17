package dev.wowhelper.macrogenerator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.wowhelper.macrogenerator.model.Macro;

@Repository
public interface MacroRepository extends JpaRepository<Macro, Long> {

}
