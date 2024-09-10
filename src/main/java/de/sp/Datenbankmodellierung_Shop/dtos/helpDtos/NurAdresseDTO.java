package de.sp.Datenbankmodellierung_Shop.dtos.helpDtos;

import lombok.Data;

@Data
public class NurAdresseDTO {
    private Long id;
    private String adresseInformation;
    // 去掉 Kunden 属性，或者在特定场景不使用
    // private Set<KundeSummaryDTO> kunden;
}