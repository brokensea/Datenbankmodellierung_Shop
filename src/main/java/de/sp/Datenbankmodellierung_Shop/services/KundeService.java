package de.sp.Datenbankmodellierung_Shop.services;

import de.sp.Datenbankmodellierung_Shop.entities.Kunde;
import java.util.List;
import java.util.Optional;

public interface KundeService {
    List<Kunde> findAll();
    Optional<Kunde> findById(Long id);
    Kunde save(Kunde kunde);
    Kunde update(Long id, Kunde kunde);
    void deleteById(Long id);
}
