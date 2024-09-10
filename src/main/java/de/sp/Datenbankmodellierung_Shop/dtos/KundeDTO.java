package de.sp.Datenbankmodellierung_Shop.dtos;

import de.sp.Datenbankmodellierung_Shop.dtos.helpDtos.NurAdresseDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class KundeDTO {
    // Getters and Setters
    private Long kundeId;
    private String nachname;
    private String vorname;
    private String email;
    private NurAdresseDTO adresse;
    private Set<BestellungDTO> bestellungen;
    private WarenkorbDTO warenkorb;

}