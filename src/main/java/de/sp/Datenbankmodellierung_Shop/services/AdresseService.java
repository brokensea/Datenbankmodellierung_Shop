package de.sp.Datenbankmodellierung_Shop.services;

import de.sp.Datenbankmodellierung_Shop.entities.Adresse;
import java.util.List;
import java.util.Optional;

public interface AdresseService {
    List<Adresse> findAll();
    Optional<Adresse> findById(Long id);
    Adresse save(Adresse adresse);
    void deleteById(Long id);

    Adresse addKundeToAdresse(Long adresseId, Long kundeId);
    Adresse removeKundeFromAdresse(Long adresseId, Long kundeId);
}
