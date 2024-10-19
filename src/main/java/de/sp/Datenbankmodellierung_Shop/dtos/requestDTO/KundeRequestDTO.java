package de.sp.Datenbankmodellierung_Shop.dtos.requestDTO;

public record KundeRequestDTO(
        String nachname,
        String vorname,
        String email,
        Long adresseId) {
}
