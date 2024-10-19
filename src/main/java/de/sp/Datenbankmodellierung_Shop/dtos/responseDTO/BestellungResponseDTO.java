package de.sp.Datenbankmodellierung_Shop.dtos.responseDTO;

public record BestellungResponseDTO(
        Long id,
        String orderDatum,
        String status,
        Double gesamtpreis,
        Long kundeId) {
}
