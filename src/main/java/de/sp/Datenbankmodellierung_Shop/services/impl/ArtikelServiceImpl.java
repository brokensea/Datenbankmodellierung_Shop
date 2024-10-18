package de.sp.Datenbankmodellierung_Shop.services.impl;

import de.sp.Datenbankmodellierung_Shop.entities.Artikel;
import de.sp.Datenbankmodellierung_Shop.mapper.ArtikelMapper;
import de.sp.Datenbankmodellierung_Shop.repositories.ArtikelRepository;
import de.sp.Datenbankmodellierung_Shop.services.ArtikelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtikelServiceImpl implements ArtikelService {

    private final ArtikelRepository artikelRepository;
    private final ArtikelMapper artikelMapper;


    public ArtikelServiceImpl(ArtikelRepository artikelRepository, ArtikelMapper artikelMapper) {
        this.artikelRepository = artikelRepository;
        this.artikelMapper = artikelMapper;
    }

    @Override
    public List<Artikel> findAll() {
        return artikelRepository.findAll();
    }

    @Override
    public Optional<Artikel> findById(Long id) {
        return artikelRepository.findById(id);
    }

    @Override
    public Artikel save(Artikel artikel) {
        return artikelRepository.save(artikel);
    }

    @Override
    public void deleteById(Long id) {
        artikelRepository.deleteById(id);
    }
}