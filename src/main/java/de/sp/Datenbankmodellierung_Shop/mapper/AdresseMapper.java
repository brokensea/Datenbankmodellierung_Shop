package de.sp.Datenbankmodellierung_Shop.mapper;


import de.sp.Datenbankmodellierung_Shop.dtos.requestDTO.AdresseRequestDTO;
import de.sp.Datenbankmodellierung_Shop.dtos.responseDTO.AdresseResponseDTO;
import de.sp.Datenbankmodellierung_Shop.entities.Adresse;
import org.springframework.stereotype.Component;

@Component
public class AdresseMapper {
    public static AdresseResponseDTO toResponseDTO(Adresse adresse) {
        return new AdresseResponseDTO(adresse.getId(), adresse.getAdresseInformation());
    }

    public static AdresseRequestDTO toRequestDTO(Adresse adresse) {
        return new AdresseRequestDTO(adresse.getAdresseInformation());
    }

    public static Adresse toEntity(AdresseRequestDTO adresseRequestDTO) {
        Adresse adresse = new Adresse();
        adresse.setAdresseInformation(adresseRequestDTO.adresseInformation());
        return adresse;
    }
}