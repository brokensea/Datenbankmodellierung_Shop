package de.sp.Datenbankmodellierung_Shop.services;

import de.sp.Datenbankmodellierung_Shop.dtos.AddArticleToKundenBestellungDto;
import de.sp.Datenbankmodellierung_Shop.entities.Bestellung;

import java.util.List;
import java.util.Optional;

public interface BestellungService {
    List<Bestellung> findAll();

    Optional<Bestellung> findById(Long id);

    Bestellung save(Bestellung bestellung);

    void deleteById(Long id);

    void addArticleToKundenBestellung(AddArticleToKundenBestellungDto dto);
}
