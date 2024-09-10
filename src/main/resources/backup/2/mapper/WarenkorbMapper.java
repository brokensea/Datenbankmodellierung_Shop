package de.sp.Datenbankmodellierung_Shop.mapper;

import de.sp.Datenbankmodellierung_Shop.dtos.WarenkorbDTO;
import de.sp.Datenbankmodellierung_Shop.entities.Warenkorb;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface WarenkorbMapper {
    WarenkorbMapper INSTANCE = Mappers.getMapper(WarenkorbMapper.class);


    /* @Mapping(target = "kunde", ignore = true) // 如果KundeDTO可能引发循环引用
     @Mapping(target = "artikel", ignore = true)
     // 如果ArtikelDTO可能引发循环引用*/
    @Mapping(target = "kunde", source = "kunde")
    @Mapping(target = "artikel", source = "artikel")
    WarenkorbDTO toDto(Warenkorb warenkorb);

    /*@Mapping(target = "kunde", ignore = true)
    @Mapping(target = "artikel", ignore = true)*/
    @Mapping(target = "kunde", source = "kunde")
    @Mapping(target = "artikel", source = "artikel")
    Warenkorb toEntity(WarenkorbDTO warenkorbDTO);
}
