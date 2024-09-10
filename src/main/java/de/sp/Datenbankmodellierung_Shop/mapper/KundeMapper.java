package de.sp.Datenbankmodellierung_Shop.mapper;

import de.sp.Datenbankmodellierung_Shop.dtos.AdresseDTO;
import de.sp.Datenbankmodellierung_Shop.dtos.KundeDTO;
import de.sp.Datenbankmodellierung_Shop.dtos.helpDtos.KundeSummaryDTO;
import de.sp.Datenbankmodellierung_Shop.entities.Kunde;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class KundeMapper {

    //new
    private final AdresseMapper adresseMapper;

    public KundeMapper(AdresseMapper adresseMapper) {
        this.adresseMapper = adresseMapper;
    }

    public KundeSummaryDTO toSummaryDto(Kunde kunde) {
        if (kunde == null) {
            return null;
        }

        KundeSummaryDTO kundeSummaryDTO = new KundeSummaryDTO();
        kundeSummaryDTO.setId(kunde.getId());
        kundeSummaryDTO.setVollName(kunde.getNachname() + " " + kunde.getVorname());

        return kundeSummaryDTO;
    }


    /*public KundeDTO toDto(Kunde kunde) {
        if (kunde == null) {
            return null;
        }
        KundeDTO kundeDTO = new KundeDTO();
        kundeDTO.setKundeId(kunde.getId());
        kundeDTO.setNachname(kunde.getNachname());
        kundeDTO.setVorname(kunde.getVorname());
        kundeDTO.setEmail(kunde.getEmail());

        //new
        if (kunde.getAdresse() != null) {
            AdresseDTO adresseDTO = adresseMapper.toDto(kunde.getAdresse());
            kundeDTO.setAdresse(adresseDTO);
        }
        return kundeDTO;
    }*/

    public KundeDTO toDto(Kunde kunde) {
        if (kunde == null) {
            return null;
        }
        KundeDTO kundeDTO = new KundeDTO();
        kundeDTO.setKundeId(kunde.getId());
        kundeDTO.setNachname(kunde.getNachname());
        kundeDTO.setVorname(kunde.getVorname());
        kundeDTO.setEmail(kunde.getEmail());

        // 使用 Optional 处理 adresse 映射
        kundeDTO.setAdresse(
                Optional.ofNullable(kunde.getAdresse())
                        .map(adresseMapper::toNurAdresseDTO)
                        .orElse(null)
        );
        // 如果有其他相关的映射需求（例如 Bestellungen 或 Warenkorb），可以在此处添加
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

        // Optional mappings
        // kunde.setAdresse(toAdresse(kundeDTO.getAdresse()));
        // kunde.setBestellungen(kundeDTO.getBestellungen().stream().map(this::toBestellung).collect(Collectors.toSet()));
        // kunde.setWarenkorb(toWarenkorb(kundeDTO.getWarenkorb()));

        return kunde;
    }

    // Optional conversion methods
    // private BestellungDTO toBestellungDTO(Bestellung bestellung) { ... }
    // private Bestellung toBestellung(BestellungDTO bestellungDTO) { ... }
    // private WarenkorbDTO toWarenkorbDTO(Warenkorb warenkorb) { ... }
    // private Warenkorb toWarenkorb(WarenkorbDTO warenkorbDTO) { ... }
}