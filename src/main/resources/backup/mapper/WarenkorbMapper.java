package de.sp.Datenbankmodellierung_Shop.mapper;

import de.sp.Datenbankmodellierung_Shop.dtos.WarenkorbDTO;
import de.sp.Datenbankmodellierung_Shop.entities.Warenkorb;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface WarenkorbMapper {
    WarenkorbMapper INSTANCE = Mappers.getMapper(WarenkorbMapper.class);

    WarenkorbDTO toDto(Warenkorb warenkorb);
    Warenkorb toEntity(WarenkorbDTO warenkorbDTO);
}
