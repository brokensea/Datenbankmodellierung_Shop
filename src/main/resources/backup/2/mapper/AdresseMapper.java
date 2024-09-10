package de.sp.Datenbankmodellierung_Shop.mapper;

import de.sp.Datenbankmodellierung_Shop.dtos.AdresseDTO;
import de.sp.Datenbankmodellierung_Shop.entities.Adresse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AdresseMapper {
    AdresseMapper INSTANCE = Mappers.getMapper(AdresseMapper.class);

    @Mapping(target = "kunden", ignore = true)
    AdresseDTO toDto(Adresse adresse);

    @Mapping(target = "kunden", ignore = true)
    Adresse toEntity(AdresseDTO adresseDTO);
}