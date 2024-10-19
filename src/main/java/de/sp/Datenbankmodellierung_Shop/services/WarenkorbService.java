package de.sp.Datenbankmodellierung_Shop.services;

import de.sp.Datenbankmodellierung_Shop.dtos.AddArticleToWarenkorbDto;
import de.sp.Datenbankmodellierung_Shop.dtos.responseDTO.WarenkorbResponseDTO;
import de.sp.Datenbankmodellierung_Shop.entities.Warenkorb;

import java.util.List;
import java.util.Optional;

public interface WarenkorbService {
    List<Warenkorb> findAll();

    Optional<Warenkorb> findById(Long id);
    
    void deleteById(Long id);

    WarenkorbResponseDTO addArticleToKundenWarenkorb(AddArticleToWarenkorbDto dto);
}
