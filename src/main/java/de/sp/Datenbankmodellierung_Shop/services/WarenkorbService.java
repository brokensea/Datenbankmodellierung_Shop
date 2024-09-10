package de.sp.Datenbankmodellierung_Shop.services;

import de.sp.Datenbankmodellierung_Shop.dtos.AddArticleToWarenkorbDto;
import de.sp.Datenbankmodellierung_Shop.entities.Warenkorb;

import java.util.List;
import java.util.Optional;

public interface WarenkorbService {
    List<Warenkorb> findAll();

    Optional<Warenkorb> findById(Long id);

    Warenkorb save(Warenkorb warenkorb);

    void deleteById(Long id);

    void addArticleToKundenWarenkorb(AddArticleToWarenkorbDto dto);
}
