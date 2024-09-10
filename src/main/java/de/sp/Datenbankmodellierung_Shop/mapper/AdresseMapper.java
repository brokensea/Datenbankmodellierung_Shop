package de.sp.Datenbankmodellierung_Shop.mapper;

import de.sp.Datenbankmodellierung_Shop.dtos.AdresseDTO;
import de.sp.Datenbankmodellierung_Shop.entities.Adresse;
import de.sp.Datenbankmodellierung_Shop.dtos.helpDtos.NurAdresseDTO;
import de.sp.Datenbankmodellierung_Shop.entities.Kunde;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AdresseMapper {

    private final KundeMapper kundeMapper;

    // 依赖注入 KundeMapper
    public AdresseMapper(@Lazy KundeMapper kundeMapper) {
        this.kundeMapper = kundeMapper;
    }

    public AdresseDTO toDto(Adresse adresse) {
        if (adresse == null) {
            return null;
        }
        AdresseDTO adresseDTO = new AdresseDTO();
        adresseDTO.setId(adresse.getId());
        adresseDTO.setAdresseInformation(adresse.getAdresseInformation());

        // 只映射客户的简要信息，避免循环引用
        if (adresse.getKunden() != null) {
            adresseDTO.setKunden(adresse.getKunden().stream()
                    .map(kundeMapper::toSummaryDto)
                    .collect(Collectors.toSet()));
        } else {
            adresseDTO.setKunden(null);
        }

        return adresseDTO;
    }

    // 添加新的 toNurAdresseDTO 方法
    public NurAdresseDTO toNurAdresseDTO(Adresse adresse) {
        if (adresse == null) {
            return null;
        }
        NurAdresseDTO nurAdresseDTO = new NurAdresseDTO();
        nurAdresseDTO.setId(adresse.getId());
        nurAdresseDTO.setAdresseInformation(adresse.getAdresseInformation());

        // 不映射 Kunden

        return nurAdresseDTO;
    }



   /* public AdresseDTO toDto(Adresse adresse) {
        if (adresse == null) {
            return null;
        }

        AdresseDTO adresseDTO = new AdresseDTO();
        adresseDTO.setId(adresse.getId());
        adresseDTO.setAdresseInformation(adresse.getAdresseInformation());

        // 映射客户信息
        if (adresse.getKunden() != null) {
            adresseDTO.setKunden(adresse.getKunden().stream()
                    .map(kundeMapper::toSummaryDto)
                    .collect(Collectors.toSet()));
        }

        return adresseDTO;
    }*/

    /*
    public AdresseDTO toDto(Adresse adresse) {
        if (adresse == null) {
            return null;
        }

        AdresseDTO adresseDTO = new AdresseDTO();
        adresseDTO.setId(adresse.getId());
        adresseDTO.setAdresseInformation(adresse.getAdresseInformation());
        // 省略其他 Kunde 信息
        adresseDTO.setKunden(null);

        return adresseDTO;
    }*/

    public Adresse toEntity(AdresseDTO adresseDTO) {
        if (adresseDTO == null) {
            return null;
        }

        Adresse adresse = new Adresse();
        adresse.setId(adresseDTO.getId());
        adresse.setAdresseInformation(adresseDTO.getAdresseInformation());

        // Optional mapping of customers
        // adresse.setKunden(adresseDTO.getKunden().stream().map(this::toKunde).collect(Collectors.toSet()));

        return adresse;
    }

    // Optional conversion methods
    // private Kunde toKunde(KundeSummaryDTO kundeSummaryDTO) { ... }
}