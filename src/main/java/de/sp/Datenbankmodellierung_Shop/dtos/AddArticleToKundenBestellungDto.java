package de.sp.Datenbankmodellierung_Shop.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public record AddArticleToKundenBestellungDto(Long kundeId,
                                              Long artikelId,
                                              Long bestellungId) {
}
