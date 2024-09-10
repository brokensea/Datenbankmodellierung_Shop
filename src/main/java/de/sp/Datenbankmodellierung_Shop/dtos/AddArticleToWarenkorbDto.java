package de.sp.Datenbankmodellierung_Shop.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddArticleToWarenkorbDto {
    private Long kundeId;
    private Long artikelId;
}
