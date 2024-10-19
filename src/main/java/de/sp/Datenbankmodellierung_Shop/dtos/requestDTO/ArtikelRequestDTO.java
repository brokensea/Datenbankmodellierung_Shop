package de.sp.Datenbankmodellierung_Shop.dtos.requestDTO;

public record ArtikelRequestDTO(
        String bezeichnung,
        String beschreibung,
        Double preis,
        String status,
        Boolean bestand) {
}
