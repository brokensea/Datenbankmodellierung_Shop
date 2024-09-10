package de.sp.Datenbankmodellierung_Shop.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class WarenkorbDTO {
    // Getters and Setters
    private Long id;
    private KundeDTO kunde;
    private Set<ArtikelDTO> artikel;
}