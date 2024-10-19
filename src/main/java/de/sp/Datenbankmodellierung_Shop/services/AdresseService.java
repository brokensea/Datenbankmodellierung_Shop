package de.sp.Datenbankmodellierung_Shop.services;

import de.sp.Datenbankmodellierung_Shop.dtos.requestDTO.AdresseRequestDTO;
import de.sp.Datenbankmodellierung_Shop.dtos.responseDTO.AdresseResponseDTO;
import de.sp.Datenbankmodellierung_Shop.entities.Adresse;

import java.util.List;
import java.util.Optional;

public interface AdresseService {
    List<AdresseResponseDTO> findAllAddresses();

    Optional<AdresseResponseDTO> findById(Long id);

    AdresseResponseDTO save(AdresseRequestDTO adresseDTO);
    
    boolean deleteById(Long id);

    Adresse addKundeToAdresse(Long adresseId, Long kundeId);

    Adresse removeKundeFromAdresse(Long adresseId, Long kundeId);
}
