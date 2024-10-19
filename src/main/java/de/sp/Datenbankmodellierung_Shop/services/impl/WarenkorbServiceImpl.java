package de.sp.Datenbankmodellierung_Shop.services.impl;

import de.sp.Datenbankmodellierung_Shop.dtos.AddArticleToWarenkorbDto;
import de.sp.Datenbankmodellierung_Shop.dtos.responseDTO.WarenkorbResponseDTO;
import de.sp.Datenbankmodellierung_Shop.entities.Artikel;
import de.sp.Datenbankmodellierung_Shop.entities.Warenkorb;
import de.sp.Datenbankmodellierung_Shop.mapper.WarenkorbMapper;
import de.sp.Datenbankmodellierung_Shop.repositories.ArtikelRepository;
import de.sp.Datenbankmodellierung_Shop.repositories.KundeRepository;
import de.sp.Datenbankmodellierung_Shop.repositories.WarenkorbRepository;
import de.sp.Datenbankmodellierung_Shop.services.WarenkorbService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.sp.Datenbankmodellierung_Shop.entities.Kunde;

import java.util.List;
import java.util.Optional;

@Service
public class WarenkorbServiceImpl implements WarenkorbService {
    private final KundeRepository kundeRepository;
    private final ArtikelRepository artikelRepository;
    private final WarenkorbRepository warenkorbRepository;

    @Autowired
    public WarenkorbServiceImpl(KundeRepository kundeRepository, ArtikelRepository artikelRepository, WarenkorbRepository warenkorbRepository) {
        this.kundeRepository = kundeRepository;
        this.artikelRepository = artikelRepository;
        this.warenkorbRepository = warenkorbRepository;
    }

    @Override
    public List<Warenkorb> findAll() {
        return warenkorbRepository.findAll();
    }

    @Override
    public Optional<Warenkorb> findById(Long id) {
        return warenkorbRepository.findById(id);
    }


    @Override
    public void deleteById(Long id) {
        warenkorbRepository.deleteById(id);
    }

    @Override
    public WarenkorbResponseDTO addArticleToKundenWarenkorb(AddArticleToWarenkorbDto dto) {
        Kunde kunde = kundeRepository.findById(dto.getKundeId())
                .orElseThrow(() -> new EntityNotFoundException("Kunde not found"));
        Artikel artikel = artikelRepository.findById(dto.getArtikelId())
                .orElseThrow(() -> new EntityNotFoundException("Artikel not found"));
        Warenkorb warenkorb = kunde.getWarenkorb();
        if (warenkorb == null) {
            warenkorb = new Warenkorb();
            warenkorb.setKunde(kunde);
            kunde.setWarenkorb(warenkorb);
        }
        warenkorb.getArtikel().add(artikel);
        kundeRepository.save(kunde); // 保存用户（可能会级联保存购物车）

        // 返回更新后的 WarenkorbResponseDTO
        return WarenkorbMapper.toResponseDTO(warenkorb);
    }
}