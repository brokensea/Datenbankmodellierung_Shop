package de.sp.Datenbankmodellierung_Shop.mapper;

import de.sp.Datenbankmodellierung_Shop.dtos.ArtikelDTO;
import de.sp.Datenbankmodellierung_Shop.entities.Artikel;
import org.springframework.stereotype.Component;

@Component
public class ArtikelMapper {

    public ArtikelDTO toDto(Artikel artikel) {
        if (artikel == null) {
            return null;
        }

        ArtikelDTO artikelDTO = new ArtikelDTO();
        artikelDTO.setId(artikel.getId());
        artikelDTO.setBezeichnung(artikel.getBezeichnung());
        artikelDTO.setBeschreibung(artikel.getBeschreibung());
        artikelDTO.setPreis(artikel.getPreis());
        artikelDTO.setStatus(artikel.getStatus());
        artikelDTO.setBestand(artikel.getBestand());
        // 可选的映射集合
        // artikelDTO.setBestellungen(artikel.getBestellungen().stream().map(this::toBestellungDTO).collect(Collectors.toSet()));
        // artikelDTO.setWarenkoerbe(artikel.getWarenkoerbe().stream().map(this::toWarenkorbDTO).collect(Collectors.toSet()));

        return artikelDTO;
    }

    public Artikel toEntity(ArtikelDTO artikelDTO) {
        if (artikelDTO == null) {
            return null;
        }

        Artikel artikel = new Artikel();
        artikel.setId(artikelDTO.getId());
        artikel.setBezeichnung(artikelDTO.getBezeichnung());
        artikel.setBeschreibung(artikelDTO.getBeschreibung());
        artikel.setPreis(artikelDTO.getPreis());
        artikel.setStatus(artikelDTO.getStatus());
        artikel.setBestand(artikelDTO.getBestand());
        // 可选的映射集合
        // artikel.setBestellungen(artikelDTO.getBestellungen().stream().map(this::toBestellung).collect(Collectors.toSet()));
        // artikel.setWarenkoerbe(artikelDTO.getWarenkoerbe().stream().map(this::toWarenkorb).collect(Collectors.toSet()));

        return artikel;
    }

    // 可选的转换方法
    // private BestellungDTO toBestellungDTO(Bestellung bestellung) { ... }
    // private Bestellung toBestellung(BestellungDTO bestellungDTO) { ... }
    // private WarenkorbDTO toWarenkorbDTO(Warenkorb warenkorb) { ... }
    // private Warenkorb toWarenkorb(WarenkorbDTO warenkorbDTO) { ... }
}