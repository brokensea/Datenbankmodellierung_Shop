package de.sp.Datenbankmodellierung_Shop.mapper;

import de.sp.Datenbankmodellierung_Shop.dtos.requestDTO.BestellungRequestDTO;
import de.sp.Datenbankmodellierung_Shop.dtos.responseDTO.BestellungResponseDTO;
import de.sp.Datenbankmodellierung_Shop.entities.Bestellung;
import de.sp.Datenbankmodellierung_Shop.entities.Kunde;

public class BestellungMapper {

    public static BestellungResponseDTO toResponseDTO(Bestellung bestellung) {
        return new BestellungResponseDTO(
                bestellung.getId(),
                bestellung.getOrderDatum(),
                bestellung.getStatus(),
                bestellung.getGesamtpreis(),
                bestellung.getKunde().getId()
        );
    }

    public static Bestellung toEntity(BestellungRequestDTO bestellungRequestDTO, Kunde kunde) {
        Bestellung bestellung = new Bestellung();
        bestellung.setOrderDatum(bestellungRequestDTO.orderDatum());
        bestellung.setStatus(bestellungRequestDTO.status());
        bestellung.setGesamtpreis(bestellungRequestDTO.gesamtpreis());

        bestellung.setKunde(kunde);
        return bestellung;
    }
}
