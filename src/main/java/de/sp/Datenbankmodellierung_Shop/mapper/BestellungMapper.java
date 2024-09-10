package de.sp.Datenbankmodellierung_Shop.mapper;


import de.sp.Datenbankmodellierung_Shop.dtos.BestellungDTO;
import de.sp.Datenbankmodellierung_Shop.entities.Bestellung;
import org.springframework.stereotype.Component;

@Component
public class BestellungMapper {

    public BestellungDTO toDto(Bestellung bestellung) {
        if (bestellung == null) {
            return null;
        }

        BestellungDTO bestellungDTO = new BestellungDTO();
        bestellungDTO.setId(bestellung.getId());
        bestellungDTO.setOrderDatum(bestellung.getOrder_datum());
        bestellungDTO.setStatus(bestellung.getStatus());
        bestellungDTO.setGesamtpreis(bestellung.getGesamtpreis());
        // 可选的映射集合
        // bestellungDTO.setArtikel(bestellung.getArtikels().stream().map(this::toArtikelDTO).collect(Collectors.toSet()));
        // bestellungDTO.setKunde(toKundeDTO(bestellung.getKunde()));

        return bestellungDTO;
    }

    public Bestellung toEntity(BestellungDTO bestellungDTO) {
        if (bestellungDTO == null) {
            return null;
        }

        Bestellung bestellung = new Bestellung();
        bestellung.setId(bestellungDTO.getId());
        bestellung.setOrder_datum(bestellungDTO.getOrderDatum());
        bestellung.setStatus(bestellungDTO.getStatus());
        bestellung.setGesamtpreis(bestellungDTO.getGesamtpreis());
        // 可选的映射集合
        // bestellung.setArtikels(bestellungDTO.getArtikel().stream().map(this::toArtikel).collect(Collectors.toSet()));
        // bestellung.setKunde(toKunde(bestellungDTO.getKunde()));

        return bestellung;
    }

    // 可选的转换方法
    // private ArtikelDTO toArtikelDTO(Artikel artikel) { ... }
    // private Artikel toArtikel(ArtikelDTO artikelDTO) { ... }
    // private KundeDTO toKundeDTO(Kunde kunde) { ... }
    // private Kunde toKunde(KundeDTO kundeDTO) { ... }
}