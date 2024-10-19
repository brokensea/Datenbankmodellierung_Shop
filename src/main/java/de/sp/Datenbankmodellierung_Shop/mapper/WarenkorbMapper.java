package de.sp.Datenbankmodellierung_Shop.mapper;

import de.sp.Datenbankmodellierung_Shop.dtos.requestDTO.WarenkorbRequestDTO;
import de.sp.Datenbankmodellierung_Shop.dtos.responseDTO.WarenkorbResponseDTO;
import de.sp.Datenbankmodellierung_Shop.entities.Warenkorb;
import de.sp.Datenbankmodellierung_Shop.entities.Kunde;
import org.springframework.stereotype.Component;

@Component
public class WarenkorbMapper {
    public static WarenkorbResponseDTO toResponseDTO(Warenkorb warenkorb) {
        return new WarenkorbResponseDTO(
                warenkorb.getId(),
                warenkorb.getKunde().getId()
        );
    }

    public static Warenkorb toEntity(WarenkorbRequestDTO warenkorbRequestDTO, Kunde kunde) {
        Warenkorb warenkorb = new Warenkorb();
        warenkorb.setKunde(kunde);
        return warenkorb;
    }
}