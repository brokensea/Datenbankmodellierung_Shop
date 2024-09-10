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
    BestellungDTO toDto(Bestellung bestellung);
    @Mapping(source = "orderDatum", target = "order_datum")
    Bestellung toEntity(BestellungDTO bestellungDTO);
}