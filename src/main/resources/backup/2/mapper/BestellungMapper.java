package de.sp.Datenbankmodellierung_Shop.mapper;


import de.sp.Datenbankmodellierung_Shop.dtos.BestellungDTO;
import de.sp.Datenbankmodellierung_Shop.entities.Bestellung;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BestellungMapper {
    BestellungMapper INSTANCE = Mappers.getMapper(BestellungMapper.class);

    @Mapping(source = "order_datum", target = "orderDatum")
    @Mapping(target = "artikel", ignore = true)
        // 忽略可能引发循环的字段
    BestellungDTO toDto(Bestellung bestellung);

    @Mapping(source = "orderDatum", target = "order_datum")
    @Mapping(target = "artikel", ignore = true)
    Bestellung toEntity(BestellungDTO bestellungDTO);
}