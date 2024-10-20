package de.sp.Datenbankmodellierung_Shop.services.impl;


import de.sp.Datenbankmodellierung_Shop.dtos.AddArticleToKundenBestellungDto;
import de.sp.Datenbankmodellierung_Shop.dtos.responseDTO.BestellungResponseDTO;
import de.sp.Datenbankmodellierung_Shop.entities.Artikel;
import de.sp.Datenbankmodellierung_Shop.entities.Bestellung;
import de.sp.Datenbankmodellierung_Shop.entities.BestellungArtikel;
import de.sp.Datenbankmodellierung_Shop.entities.Kunde;
import de.sp.Datenbankmodellierung_Shop.mapper.BestellungMapper;
import de.sp.Datenbankmodellierung_Shop.repositories.ArtikelRepository;
import de.sp.Datenbankmodellierung_Shop.repositories.BestellungArtikelRepository;
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
    private final BestellungArtikelRepository bestellungArtikelRepository;

    @Autowired
    public BestellungServiceImpl(KundeRepository kundeRepository, ArtikelRepository artikelRepository, BestellungRepository bestellungRepository
            , BestellungArtikelRepository bestellungArtikelRepository) {
        this.kundeRepository = kundeRepository;
        this.artikelRepository = artikelRepository;
        this.bestellungRepository = bestellungRepository;
        this.bestellungArtikelRepository = bestellungArtikelRepository;
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
        Kunde kunde = kundeRepository.findById(dto.kundeId())
                .orElseThrow(() -> new EntityNotFoundException("Kunde not found"));

        // 尝试查找 Bestellung，如果没有找到则为 null
        Bestellung bestellung = bestellungRepository.findById(dto.bestellungId()).orElse(null);

        // 查找 Artikel
        Artikel artikel = artikelRepository.findById(dto.artikelId())
                .orElseThrow(() -> new EntityNotFoundException("Artikel not found"));

        // 创建 BestellungArtikel 对象
        BestellungArtikel bestellungArtikel = new BestellungArtikel();
        bestellungArtikel.setArtikel(artikel);
        bestellungArtikel.setAnzahl(1);

        if (bestellung != null && kunde.getBestellungen().contains(bestellung)) {
            // 如果 Bestellung 存在且属于该 Kunde，添加 Artikel
            bestellungArtikel.setBestellung(bestellung);
            bestellung.getBestellungArtikels().add(bestellungArtikel);
            bestellung.setGesamtpreis(bestellung.getGesamtpreis() + artikel.getPreis());
        } else {
            // 创建新的 Bestellung
            bestellung = createNewBestellung(dto.kundeId());
            bestellungArtikel.setBestellung(bestellung);
            bestellung.getBestellungArtikels().add(bestellungArtikel);
            bestellung.setGesamtpreis(artikel.getPreis()); // 如果是新订单，设置初始总价

            kunde.getBestellungen().add(bestellung);
        }

        // 保存 Bestellung 和 Kunde
        // bestellungRepository.save(bestellung); // 确保保存 bestellung
        kundeRepository.save(kunde);

        return BestellungMapper.toResponseDTO(bestellung);
    }

    // 私有方法用于创建新的 Bestellung
    private Bestellung createNewBestellung(Long kundeId) {
        Bestellung bestellung = new Bestellung();

        // 设置 Kunde 信息
        Kunde kunde = kundeRepository.findById(kundeId)
                .orElseThrow(() -> new EntityNotFoundException("Kunde not found with id: " + kundeId));
        bestellung.setKunde(kunde);
        bestellung.setStatus("NEU");
        bestellung.setGesamtpreis(0.0); // 初始化总价为 0
        // 设置其他需要的属性...

        // 保存新订单到数据库
        return bestellungRepository.save(bestellung);
    }
}

