package de.sp.Datenbankmodellierung_Shop.services.impl;


import de.sp.Datenbankmodellierung_Shop.dtos.AddArticleToKundenBestellungDto;
import de.sp.Datenbankmodellierung_Shop.entities.Artikel;
import de.sp.Datenbankmodellierung_Shop.entities.Bestellung;
import de.sp.Datenbankmodellierung_Shop.entities.Kunde;
import de.sp.Datenbankmodellierung_Shop.entities.Warenkorb;
import de.sp.Datenbankmodellierung_Shop.repositories.ArtikelRepository;
import de.sp.Datenbankmodellierung_Shop.repositories.BestellungRepository;
import de.sp.Datenbankmodellierung_Shop.repositories.KundeRepository;
import de.sp.Datenbankmodellierung_Shop.services.BestellungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BestellungServiceImpl implements BestellungService {

    private final KundeRepository kundeRepository;
    private final ArtikelRepository artikelRepository;
    private final BestellungRepository bestellungRepository;

    @Autowired
    public BestellungServiceImpl(KundeRepository kundeRepository, ArtikelRepository artikelRepository, BestellungRepository bestellungRepository) {
        this.kundeRepository = kundeRepository;
        this.artikelRepository = artikelRepository;
        this.bestellungRepository = bestellungRepository;
    }

    @Override
    public List<Bestellung> findAll() {
        return bestellungRepository.findAll();
    }

    @Override
    public Optional<Bestellung> findById(Long id) {
        return bestellungRepository.findById(id);
    }

    @Override
    public Bestellung save(Bestellung bestellung) {
        return bestellungRepository.save(bestellung);
    }

    @Override
    public void deleteById(Long id) {
        bestellungRepository.deleteById(id);
    }


       /* public void addArticleToKundenBestellung(AddArticleToKundenBestellungDto dto) {
        // Step 1: Fetch Artikel and Kunde entities
        Artikel artikel = artikelRepository.findById(dto.getArtikelId())
                .orElseThrow(() -> new RuntimeException("Artikel not found"));
        Kunde kunde = kundeRepository.findById(dto.getKundeId())
                .orElseThrow(() -> new RuntimeException("Kunde not found"));
        // Step 2: Find the Bestellung associated with the Kunde
        Bestellung bestellung = bestellungRepository.findById(dto.getBestellungId())
                .orElseThrow(() -> new RuntimeException("Bestellung not found"));

        // Step 3: Check if the Bestellung belongs to the Kunde
        if (bestellung.getKunde().equals(kunde)) {
            // Step 4: Add Artikel to the Bestellung
            bestellung.getArtikels().add(artikel);
            bestellungRepository.save(bestellung); // Save the updated Bestellung

            // If needed, also save the Kunde to ensure the relationship is updated
            kundeRepository.save(kunde);
        } else {
            throw new RuntimeException("Bestellung does not belong to the specified Kunde");
        }
    }*/

    @Override
    @Transactional
    public void addArticleToKundenBestellung(AddArticleToKundenBestellungDto dto) {
        System.out.println("Received DTO: " + dto);
        try {
            // Fetch entities
            Artikel artikel = artikelRepository.findById(dto.getArtikelId())
                    .orElseThrow(() -> new RuntimeException("Artikel not found"));
            Kunde kunde = kundeRepository.findById(dto.getKundeId())
                    .orElseThrow(() -> new RuntimeException("Kunde not found"));

            Bestellung bestellung;

            if (dto.getBestellungId() != null) {
                bestellung = bestellungRepository.findById(Long.parseLong(dto.getBestellungId().toString()))
                        .orElseThrow(() -> new RuntimeException("Bestellung not found"));

                if (!bestellung.getKunde().equals(kunde)) {
                    throw new RuntimeException("Bestellung does not belong to the specified Kunde");
                }
            } else {
                bestellung = new Bestellung();
                bestellung.setOrder_datum("day");
                bestellung.setGesamtpreis(0.0);
                bestellung.setKunde(kunde);
                bestellung = bestellungRepository.save(bestellung);
                kunde.getBestellungen().add(bestellung);
            }

            bestellung.getArtikels().add(artikel);
            bestellungRepository.save(bestellung);

            kundeRepository.save(kunde);
        } catch (Exception e) {
            // Log the error and rethrow it to ensure the transaction rolls back
            System.err.println("Error in addArticleToKundenBestellung: " + e.getMessage());
            throw e;
        }
    }


}