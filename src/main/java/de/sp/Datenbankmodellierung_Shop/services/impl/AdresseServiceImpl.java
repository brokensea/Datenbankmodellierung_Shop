package de.sp.Datenbankmodellierung_Shop.services.impl;

import de.sp.Datenbankmodellierung_Shop.dtos.requestDTO.AdresseRequestDTO;
import de.sp.Datenbankmodellierung_Shop.dtos.responseDTO.AdresseResponseDTO;
import de.sp.Datenbankmodellierung_Shop.entities.Adresse;
import de.sp.Datenbankmodellierung_Shop.entities.Kunde;
import de.sp.Datenbankmodellierung_Shop.mapper.AdresseMapper;
import de.sp.Datenbankmodellierung_Shop.repositories.AdresseRepository;
import de.sp.Datenbankmodellierung_Shop.repositories.KundeRepository;
import de.sp.Datenbankmodellierung_Shop.services.AdresseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdresseServiceImpl implements AdresseService {

    private final AdresseRepository adresseRepository;
    private final KundeRepository kundeRepository;
    private final AdresseMapper adresseMapper;

    @Autowired
    public AdresseServiceImpl(AdresseRepository adresseRepository, KundeRepository kundeRepository, AdresseMapper adresseMapper) {
        this.adresseRepository = adresseRepository;
        this.kundeRepository = kundeRepository;
        this.adresseMapper = adresseMapper;
    }

    @Override
    public List<AdresseResponseDTO> findAllAddresses() {
        return adresseRepository.findAll().stream()
                .map(AdresseMapper::toResponseDTO)
                .collect(Collectors.toList());
    }


    @Override
    public Optional<AdresseResponseDTO> findById(Long id) {
        return adresseRepository.findById(id)
                .map(AdresseMapper::toResponseDTO)
                .or(() -> {
                    throw new EntityNotFoundException("Adresse with id " + id + " not found");
                });
    }
 

    @Override
    public AdresseResponseDTO save(AdresseRequestDTO adresseDTO) {
        Adresse adresse = AdresseMapper.toEntity(adresseDTO);
        Adresse savedAdresse = adresseRepository.save(adresse);
        return AdresseMapper.toResponseDTO(savedAdresse);
    }


    @Override
    public boolean deleteById(Long id) {
        if (adresseRepository.existsById(id)) {
            adresseRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Adresse addKundeToAdresse(Long adresseId, Long kundeId) {
        Adresse adresse = adresseRepository.findById(adresseId)
                .orElseThrow(() -> new EntityNotFoundException("Adresse with id " + adresseId + " not found"));
        Kunde kunde = kundeRepository.findById(kundeId)
                .orElseThrow(() -> new EntityNotFoundException("Kunde with id " + kundeId + " not found"));
        kunde.setAdresse(adresse);
        adresse.getKunden().add(kunde);

        kundeRepository.save(kunde);
        return adresseRepository.save(adresse);
    }

    @Override
    public Adresse removeKundeFromAdresse(Long adresseId, Long kundeId) {
        Adresse adresse = adresseRepository.findById(adresseId)
                .orElseThrow(() -> new EntityNotFoundException("Adresse with id " + adresseId + " not found"));
        Kunde kunde = kundeRepository.findById(kundeId)
                .orElseThrow(() -> new EntityNotFoundException("Kunde with id " + kundeId + " not found"));
        kunde.setAdresse(null);
        adresse.getKunden().remove(kunde);
        kundeRepository.save(kunde);
        return adresseRepository.save(adresse);
    }
}