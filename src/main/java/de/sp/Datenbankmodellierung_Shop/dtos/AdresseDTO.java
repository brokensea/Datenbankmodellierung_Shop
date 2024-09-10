package de.sp.Datenbankmodellierung_Shop.dtos;


import de.sp.Datenbankmodellierung_Shop.dtos.helpDtos.KundeSummaryDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import lombok.Data;

@Data
public class AdresseDTO {
    private Long id;
    private String adresseInformation;
    private Set<KundeSummaryDTO> kunden;
}

