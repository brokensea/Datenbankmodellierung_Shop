package de.sp.Datenbankmodellierung_Shop.mapper;

import de.sp.Datenbankmodellierung_Shop.dtos.AdresseDTO;
import de.sp.Datenbankmodellierung_Shop.entities.Adresse;
import org.springframework.stereotype.Component;

@Component
public class AdresseMapper {
    public AdresseDTO toDto(Adresse adresse) {
        if (adresse == null) {
            return null;
        }

        AdresseDTO adresseDTO = new AdresseDTO();
        adresseDTO.setId(adresse.getId());
        adresseDTO.setAdresseInformation(adresse.getAdresseInformation());
        // 地址中的客户集合可以选择性地忽略
        // adresseDTO.setKunden(adresse.getKunden().stream().map(this::toKundeDTO).collect(Collectors.toSet()));

        return adresseDTO;
    }

    public Adresse toEntity(AdresseDTO adresseDTO) {
        if (adresseDTO == null) {
            return null;
        }

        Adresse adresse = new Adresse();
        adresse.setId(adresseDTO.getId());
        adresse.setAdresseInformation(adresseDTO.getAdresseInformation());
        // 如果需要，可以将 DTO 中的客户集合转换为实体集合
        // adresse.setKunden(adresseDTO.getKunden().stream().map(this::toKunde).collect(Collectors.toSet()));

        return adresse;
    }

    // 可选的转换方法
    // private KundeDTO toKundeDTO(Kunde kunde) { ... }
    // private Kunde toKunde(KundeDTO kundeDTO) { ... }
}