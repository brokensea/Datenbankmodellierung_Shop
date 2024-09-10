package de.sp.Datenbankmodellierung_Shop.mapper;

import de.sp.Datenbankmodellierung_Shop.dtos.WarenkorbDTO;
import de.sp.Datenbankmodellierung_Shop.entities.Warenkorb;
import org.springframework.stereotype.Component;

@Component
public class WarenkorbMapper {

    public WarenkorbDTO toDto(Warenkorb warenkorb) {
        if (warenkorb == null) {
            return null;
        }

        WarenkorbDTO warenkorbDTO = new WarenkorbDTO();
        warenkorbDTO.setId(warenkorb.getId());
        // 可选的映射集合
        // warenkorbDTO.setKunde(toKundeDTO(warenkorb.getKunde()));
        // warenkorbDTO.setArtikel(warenkorb.getArtikel().stream().map(this::toArtikelDTO).collect(Collectors.toSet()));

        return warenkorbDTO;
    }

    public Warenkorb toEntity(WarenkorbDTO warenkorbDTO) {
        if (warenkorbDTO == null) {
            return null;
        }

        Warenkorb warenkorb = new Warenkorb();
        warenkorb.setId(warenkorbDTO.getId());
        // 可选的映射集合
        // warenkorb.setKunde(toKunde(warenkorbDTO.getKunde()));
        // warenkorb.setArtikel(warenkorbDTO.getArtikel().stream().map(this::toArtikel).collect(Collectors.toSet()));

        return warenkorb;
    }

    // 可选的转换方法
    // private KundeDTO toKundeDTO(Kunde kunde) { ... }
    // private Kunde toKunde(KundeDTO kundeDTO) { ... }
    // private ArtikelDTO toArtikelDTO(Artikel artikel) { ... }
    // private Artikel toArtikel(ArtikelDTO artikelDTO) { ... }
}