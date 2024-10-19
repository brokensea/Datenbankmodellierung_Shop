package de.sp.Datenbankmodellierung_Shop.services.impl;


import de.sp.Datenbankmodellierung_Shop.dtos.AddArticleToKundenBestellungDto;
import de.sp.Datenbankmodellierung_Shop.dtos.responseDTO.BestellungResponseDTO;
import de.sp.Datenbankmodellierung_Shop.entities.Artikel;
import de.sp.Datenbankmodellierung_Shop.entities.Bestellung;
import de.sp.Datenbankmodellierung_Shop.entities.BestellungArtikel;
import de.sp.Datenbankmodellierung_Shop.mapper.BestellungMapper;
import de.sp.Datenbankmodellierung_Shop.repositories.ArtikelRepository;
import de.sp.Datenbankmodellierung_Shop.repositories.BestellungRepository;
import de.sp.Datenbankmodellierung_Shop.repositories.KundeRepository;
import de.sp.Datenbankmodellierung_Shop.services.BestellungService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    @Transactional
    @Override
    public BestellungResponseDTO addArticleToKundenBestellung(AddArticleToKundenBestellungDto dto) {
        Bestellung bestellung = bestellungRepository.findById(dto.bestellungId())
                .orElseThrow(() -> new EntityNotFoundException("Bestellung not found"));
        Artikel artikel = artikelRepository.findById(dto.artikelId())
                .orElseThrow(() -> new EntityNotFoundException("Artikel not found"));

        // 创建一个新的 BestellungArtikel 对象并设置其属性
        BestellungArtikel bestellungArtikel = new BestellungArtikel();
        bestellungArtikel.setBestellung(bestellung);
        bestellungArtikel.setArtikel(artikel);
        bestellungArtikel.setAnzahl(1); // 假设每次只添加一个商品，数量为1

        bestellung.getBestellungArtikels().add(bestellungArtikel);
        bestellung.setGesamtpreis(bestellung.getGesamtpreis() + artikel.getPreis());
        bestellungRepository.save(bestellung);

        return BestellungMapper.toResponseDTO(bestellung);
    }
}

