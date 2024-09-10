package de.sp.Datenbankmodellierung_Shop.services.impl;

import de.sp.Datenbankmodellierung_Shop.entities.Kunde;
import de.sp.Datenbankmodellierung_Shop.repositories.KundeRepository;
import de.sp.Datenbankmodellierung_Shop.services.KundeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KundeServiceImpl implements KundeService {

    private final KundeRepository kundeRepository;

    @Autowired
    public KundeServiceImpl(KundeRepository kundeRepository) {
        this.kundeRepository = kundeRepository;
    }

    @Override
    public List<Kunde> findAll() {
        return kundeRepository.findAll();
    }

    @Override
    public Optional<Kunde> findById(Long id) {
        return kundeRepository.findById(id);
    }

    @Override
    public Kunde save(Kunde kunde) {
        return kundeRepository.save(kunde);
    }

    @Override
    public Kunde update(Long id, Kunde kunde) {
        if (kundeRepository.existsById(id)) {
            kunde.setId(id);
            return kundeRepository.save(kunde);
        } else {
            throw new EntityNotFoundException("Kunde with id " + id + " not found");
        }
    }


    @Override
    public void deleteById(Long id) {
        kundeRepository.deleteById(id);
    }
}