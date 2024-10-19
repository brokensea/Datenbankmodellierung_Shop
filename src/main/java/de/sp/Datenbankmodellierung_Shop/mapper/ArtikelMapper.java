package de.sp.Datenbankmodellierung_Shop.mapper;

import de.sp.Datenbankmodellierung_Shop.dtos.requestDTO.ArtikelRequestDTO;
import de.sp.Datenbankmodellierung_Shop.dtos.responseDTO.ArtikelResponseDTO;
import de.sp.Datenbankmodellierung_Shop.entities.Artikel;
import org.springframework.stereotype.Component;

@Component
public class ArtikelMapper {
    public static ArtikelResponseDTO toResponseDTO(Artikel artikel) {
        return new ArtikelResponseDTO(
                artikel.getId(),
                artikel.getBezeichnung(),
                artikel.getBeschreibung(),
                artikel.getPreis(),
                artikel.getStatus(),
                artikel.getBestand()
        );
    }

    public static Artikel toEntity(ArtikelRequestDTO artikelRequestDTO) {
        Artikel artikel = new Artikel();
        artikel.setBezeichnung(artikelRequestDTO.bezeichnung());
        artikel.setBeschreibung(artikelRequestDTO.beschreibung());
        artikel.setPreis(artikelRequestDTO.preis());
        artikel.setStatus(artikelRequestDTO.status());
        artikel.setBestand(artikelRequestDTO.bestand());
        return artikel;
    }
}