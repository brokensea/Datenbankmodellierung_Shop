package de.sp.Datenbankmodellierung_Shop.mapper;

import de.sp.Datenbankmodellierung_Shop.dtos.ArtikelDTO;
import de.sp.Datenbankmodellierung_Shop.entities.Artikel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ArtikelMapper {

    @Mapping(source = "warenkoerbe", target = "warenkoerbe")
    ArtikelDTO toDto(Artikel artikel);

    @Mapping(source = "warenkoerbe", target = "warenkoerbe")
    Artikel toEntity(ArtikelDTO artikelDTO);
}