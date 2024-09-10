package de.sp.Datenbankmodellierung_Shop.services.impl;

import de.sp.Datenbankmodellierung_Shop.entities.Adresse;
import de.sp.Datenbankmodellierung_Shop.entities.Kunde;
import de.sp.Datenbankmodellierung_Shop.repositories.AdresseRepository;
import de.sp.Datenbankmodellierung_Shop.repositories.KundeRepository;
import de.sp.Datenbankmodellierung_Shop.services.AdresseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdresseServiceImpl implements AdresseService {

    private final AdresseRepository adresseRepository;
    private final KundeRepository kundeRepository;

    @Autowired
    public AdresseServiceImpl(AdresseRepository adresseRepository, KundeRepository kundeRepository) {
        this.adresseRepository = adresseRepository;
        this.kundeRepository = kundeRepository;
    }

    @Override
    public List<Adresse> findAll() {
        return adresseRepository.findAll();
    }

    @Override
    public Optional<Adresse> findById(Long id) {
        return adresseRepository.findById(id);
    }

    @Override
    public Adresse save(Adresse adresse) {
        return adresseRepository.save(adresse);
    }

    @Override
    public void deleteById(Long id) {
        adresseRepository.deleteById(id);
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