package de.sp.Datenbankmodellierung_Shop.dtos.responseDTO;

public record KundeResponseDTO(
        Long id,
        String nachname,
        String vorname,
        String email,
        Long adresseId) {
}
