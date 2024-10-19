package de.sp.Datenbankmodellierung_Shop.dtos;

public record AddArticleToKundenBestellungDto(Long kundeId,
                                              Long artikelId,
                                              Long bestellungId) {
}
