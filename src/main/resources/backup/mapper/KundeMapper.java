package de.sp.Datenbankmodellierung_Shop.mapper;

import de.sp.Datenbankmodellierung_Shop.dtos.KundeDTO;
import de.sp.Datenbankmodellierung_Shop.entities.Kunde;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface KundeMapper {
    KundeMapper INSTANCE = Mappers.getMapper(KundeMapper.class);

    @Mapping(target = "adresse", ignore = true) // 如果地址可能会导致循环引用
    @Mapping(target = "bestellungen", ignore = true) // 如果订单可能会导致循环引用
    @Mapping(target = "warenkorb", ignore = true)
        // 如果购物车可能会导致循环引用
    KundeDTO toDto(Kunde kunde);

    @Mapping(target = "adresse", ignore = true)
    @Mapping(target = "bestellungen", ignore = true)
    @Mapping(target = "warenkorb", ignore = true)
    Kunde toEntity(KundeDTO kundeDTO);
}