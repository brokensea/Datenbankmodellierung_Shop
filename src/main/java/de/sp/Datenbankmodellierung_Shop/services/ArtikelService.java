package de.sp.Datenbankmodellierung_Shop.services;

import de.sp.Datenbankmodellierung_Shop.entities.Artikel;
import java.util.List;
import java.util.Optional;

public interface ArtikelService {
    List<Artikel> findAll();
    Optional<Artikel> findById(Long id);
    Artikel save(Artikel artikel);
    void deleteById(Long id);
}
