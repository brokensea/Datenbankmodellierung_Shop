package de.sp.Datenbankmodellierung_Shop.dtos.responseDTO;

public record ArtikelResponseDTO(Long id,
                                 String bezeichnung,
                                 String beschreibung,
                                 Double preis,
                                 String status,
                                 Boolean bestand) {
}