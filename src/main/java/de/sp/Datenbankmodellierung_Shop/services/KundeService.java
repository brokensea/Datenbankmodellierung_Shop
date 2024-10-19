package de.sp.Datenbankmodellierung_Shop.services;

import de.sp.Datenbankmodellierung_Shop.dtos.requestDTO.KundeRequestDTO;
import de.sp.Datenbankmodellierung_Shop.dtos.responseDTO.KundeResponseDTO;
import de.sp.Datenbankmodellierung_Shop.entities.Kunde;

import java.util.List;
import java.util.Optional;

public interface KundeService {
    List<Kunde> findAll();

    Optional<Kunde> findById(Long id);

    KundeResponseDTO save(KundeRequestDTO kundeRequestDTO);

    KundeResponseDTO update(Long id, KundeRequestDTO kunde);

    void deleteById(Long id);
}
