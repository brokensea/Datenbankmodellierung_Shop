package de.sp.Datenbankmodellierung_Shop.services.impl;

import de.sp.Datenbankmodellierung_Shop.dtos.requestDTO.AdresseRequestDTO;
import de.sp.Datenbankmodellierung_Shop.dtos.requestDTO.KundeRequestDTO;
import de.sp.Datenbankmodellierung_Shop.dtos.responseDTO.AdresseResponseDTO;
import de.sp.Datenbankmodellierung_Shop.dtos.responseDTO.KundeResponseDTO;
import de.sp.Datenbankmodellierung_Shop.entities.Adresse;
import de.sp.Datenbankmodellierung_Shop.entities.Kunde;
import de.sp.Datenbankmodellierung_Shop.mapper.AdresseMapper;
import de.sp.Datenbankmodellierung_Shop.mapper.KundeMapper;
import de.sp.Datenbankmodellierung_Shop.repositories.AdresseRepository;
import de.sp.Datenbankmodellierung_Shop.repositories.KundeRepository;
import de.sp.Datenbankmodellierung_Shop.services.AdresseService;
import de.sp.Datenbankmodellierung_Shop.services.KundeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KundeServiceImpl implements KundeService {

    private final KundeRepository kundeRepository;
    private final AdresseRepository adresseRepository;
    private final AdresseService adresseService;

    @Autowired
    public KundeServiceImpl(KundeRepository kundeRepository, AdresseService adresseService, AdresseRepository adresseRepository) {
        this.kundeRepository = kundeRepository;
        this.adresseService = adresseService;
        this.adresseRepository = adresseRepository;
    }

    @Override
    public List<Kunde> findAll() {
        return kundeRepository.findAll();
    }

    @Override
    public Optional<Kunde> findById(Long id) {
        return kundeRepository.findById(id);
    }

   /* @Override
    public KundeResponseDTO save(KundeRequestDTO kundeRequestDTO) {
        // 查找 Adresse，如果不存在则抛出异常
        AdresseResponseDTO adresseResponseDTO = adresseService.findById(kundeRequestDTO.adresseId())
                .orElseThrow(() -> new EntityNotFoundException("Adresse not found"));
        AdresseRequestDTO adresseRequestDTO = new AdresseRequestDTO(
                adresseResponseDTO.adresseInformation()
        );
        Adresse adresse = AdresseMapper.toEntity(adresseRequestDTO);
        // 使用 KundeMapper 将 KundeRequestDTO 转换为 Kunde 实体并设置 Adresse
        Kunde kunde = KundeMapper.toEntity(kundeRequestDTO, adresse);
        Kunde savedKunde = kundeRepository.save(kunde);
        return KundeMapper.toResponseDTO(savedKunde);
    }*/

    @Override
    public KundeResponseDTO save(KundeRequestDTO kundeRequestDTO) {
        // 查找 Adresse，如果不存在则抛出异常
        Adresse adresse = adresseRepository.findById(kundeRequestDTO.adresseId())
                .orElseThrow(() -> new EntityNotFoundException("Adresse not found"));

        // 使用 KundeMapper 将 KundeRequestDTO 转换为 Kunde 实体并设置 Adresse
        Kunde kunde = KundeMapper.toEntity(kundeRequestDTO, adresse); // 直接使用查找到的 adresse
        Kunde savedKunde = kundeRepository.save(kunde);
        return KundeMapper.toResponseDTO(savedKunde);
    }

    @Override
    public KundeResponseDTO update(Long id, KundeRequestDTO kundeRequestDTO) {
        // 查找现有的 Kunde 实体
        Kunde existingKunde = kundeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Kunde not found"));
        // 更新现有 Kunde 的属性
        existingKunde.setNachname(kundeRequestDTO.nachname());
        existingKunde.setVorname(kundeRequestDTO.vorname());
        existingKunde.setEmail(kundeRequestDTO.email());

        // 查找 Adresse
        AdresseResponseDTO adresseResponseDTO = adresseService.findById(kundeRequestDTO.adresseId())
                .orElseThrow(() -> new EntityNotFoundException("Adresse not found"));
        AdresseRequestDTO adresseRequestDTO = new AdresseRequestDTO(
                adresseResponseDTO.adresseInformation()
        );
        Adresse adresse = AdresseMapper.toEntity(adresseRequestDTO);

        // 设置 Adresse
        existingKunde.setAdresse(adresse);

        // 保存并返回更新后的 Kunde
        Kunde savedKunde = kundeRepository.save(existingKunde);
        return KundeMapper.toResponseDTO(savedKunde);  // 返回 KundeResponseDTO
    }

    @Override
    public void deleteById(Long id) {
        kundeRepository.deleteById(id);
    }
}