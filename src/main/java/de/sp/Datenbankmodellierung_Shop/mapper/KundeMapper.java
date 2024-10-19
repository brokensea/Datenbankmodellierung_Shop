package de.sp.Datenbankmodellierung_Shop.mapper;

import de.sp.Datenbankmodellierung_Shop.dtos.requestDTO.KundeRequestDTO;
import de.sp.Datenbankmodellierung_Shop.dtos.responseDTO.KundeResponseDTO;
import de.sp.Datenbankmodellierung_Shop.entities.Adresse;
import de.sp.Datenbankmodellierung_Shop.entities.Kunde;

public class KundeMapper {

    public static KundeResponseDTO toResponseDTO(Kunde kunde) {
        return new KundeResponseDTO(
                kunde.getId(),
                kunde.getNachname(),
                kunde.getVorname(),
                kunde.getEmail(),
                kunde.getAdresse().getId() // 获取 Adresse 的 ID
        );
    }

    public static Kunde toEntity(KundeRequestDTO kundeRequestDTO, Adresse adresse) {
        Kunde kunde = new Kunde();  // 使用无参构造器
        kunde.setNachname(kundeRequestDTO.nachname());
        kunde.setVorname(kundeRequestDTO.vorname());
        kunde.setEmail(kundeRequestDTO.email());
        // 设置查找到的 Adresse 实体
        kunde.setAdresse(adresse);
        return kunde;
    }
}