package de.sp.Datenbankmodellierung_Shop.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class BestellungDTO {
    // Getters and Setters
    private Long id;
    private String orderDatum;
    private String status;
    private Double gesamtpreis;
    private KundeDTO kunde;
    private Set<ArtikelDTO> artikel;

}