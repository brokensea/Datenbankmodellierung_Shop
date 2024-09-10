package de.sp.Datenbankmodellierung_Shop.mapper;

import de.sp.Datenbankmodellierung_Shop.dtos.ArtikelDTO;
import de.sp.Datenbankmodellierung_Shop.entities.Artikel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ArtikelMapper {
    ArtikelMapper INSTANCE = Mappers.getMapper(ArtikelMapper.class);

    @Mapping(target = "warenkoerbe", ignore = true)
        // 忽略可能引发循环引用的字段
    ArtikelDTO toDto(Artikel artikel);

    @Mapping(target = "warenkoerbe", ignore = true)
    Artikel toEntity(ArtikelDTO artikelDTO);
}
