package de.sp.Datenbankmodellierung_Shop.mapper;

import de.sp.Datenbankmodellierung_Shop.dtos.KundeDTO;
import de.sp.Datenbankmodellierung_Shop.entities.Kunde;
import org.springframework.stereotype.Component;

@Component
public class KundeMapper {

    public KundeDTO toDto(Kunde kunde) {
        if (kunde == null) {
            return null;
        }

        KundeDTO kundeDTO = new KundeDTO();
        kundeDTO.setKundeId(kunde.getId());
        kundeDTO.setNachname(kunde.getNachname());
        kundeDTO.setVorname(kunde.getVorname());
        kundeDTO.setEmail(kunde.getEmail());
        // 可选的映射集合
        // kundeDTO.setAdresse(toAdresseDTO(kunde.getAdresse()));
        // kundeDTO.setBestellungen(kunde.getBestellungen().stream().map(this::toBestellungDTO).collect(Collectors.toSet()));
        // kundeDTO.setWarenkorb(toWarenkorbDTO(kunde.getWarenkorb()));

        return kundeDTO;
    }

    public Kunde toEntity(KundeDTO kundeDTO) {
        if (kundeDTO == null) {
            return null;
        }

        Kunde kunde = new Kunde();
        kunde.setId(kundeDTO.getKundeId());
        kunde.setNachname(kundeDTO.getNachname());
        kunde.setVorname(kundeDTO.getVorname());
        kunde.setEmail(kundeDTO.getEmail());
        // 可选的映射集合
        // kunde.setAdresse(toAdresse(kundeDTO.getAdresse()));
        // kunde.setBestellungen(kundeDTO.getBestellungen().stream().map(this::toBestellung).collect(Collectors.toSet()));
        // kunde.setWarenkorb(toWarenkorb(kundeDTO.getWarenkorb()));

        return kunde;
    }

    // 可选的转换方法
    // private AdresseDTO toAdresseDTO(Adresse adresse) { ... }
    // private Adresse toAdresse(AdresseDTO adresseDTO) { ... }
    // private BestellungDTO toBestellungDTO(Bestellung bestellung) { ... }
    // private Bestellung toBestellung(BestellungDTO bestellungDTO) { ... }
    // private WarenkorbDTO toWarenkorbDTO(Warenkorb warenkorb) { ... }
    // private Warenkorb toWarenkorb(WarenkorbDTO warenkorbDTO) { ... }
}