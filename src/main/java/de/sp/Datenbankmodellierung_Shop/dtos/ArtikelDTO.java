package de.sp.Datenbankmodellierung_Shop.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class ArtikelDTO {
    // Getters and Setters
    private Long id;
    private String bezeichnung;
    private String beschreibung;
    private Double preis;
    private String status;
    private Boolean bestand;
    private Set<BestellungDTO> bestellungen;
    private Set<WarenkorbDTO> warenkoerbe;
}