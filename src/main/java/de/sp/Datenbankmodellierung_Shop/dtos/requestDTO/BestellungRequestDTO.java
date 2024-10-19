package de.sp.Datenbankmodellierung_Shop.dtos.requestDTO;

public record BestellungRequestDTO(String orderDatum,
                                   String status,
                                   Double gesamtpreis,
                                   Long kundeId) {
}
