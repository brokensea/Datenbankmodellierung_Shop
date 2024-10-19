package de.sp.Datenbankmodellierung_Shop.controller;

import de.sp.Datenbankmodellierung_Shop.dtos.requestDTO.AdresseRequestDTO;
import de.sp.Datenbankmodellierung_Shop.dtos.responseDTO.AdresseResponseDTO;
import de.sp.Datenbankmodellierung_Shop.mapper.AdresseMapper;
import de.sp.Datenbankmodellierung_Shop.dtos.AdresseDTO;
import de.sp.Datenbankmodellierung_Shop.entities.Adresse;
import de.sp.Datenbankmodellierung_Shop.services.AdresseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/adressen")
public class AdresseController {

    private final AdresseService adresseService;

    public AdresseController(AdresseService adresseService) {
        this.adresseService = adresseService;
    }

    @GetMapping
    public ResponseEntity<List<AdresseResponseDTO>> getAllAdressen() {
        List<AdresseResponseDTO> adresseDTOs = adresseService.findAllAddresses();
        return adresseDTOs.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(adresseDTOs);
    }


    @GetMapping("/{id}")
    public ResponseEntity<AdresseResponseDTO> getAdresseById(@PathVariable Long id) {
        return adresseService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<AdresseResponseDTO> createAdresse(@RequestBody AdresseRequestDTO adresseDTO) {
        AdresseResponseDTO savedAdresseDTO = adresseService.save(adresseDTO);
        return ResponseEntity.ok(savedAdresseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdresse(@PathVariable Long id) {
        return adresseService.deleteById(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }


    @PutMapping("/{adresseId}/kunden/{kundeId}")
    public ResponseEntity<AdresseResponseDTO> addKundeToAdresse(@PathVariable Long adresseId, @PathVariable Long kundeId) {
        AdresseResponseDTO updatedAdresseDTO = AdresseMapper.toResponseDTO(adresseService.addKundeToAdresse(adresseId, kundeId)); // 使用类名调用静态方法
        return ResponseEntity.ok(updatedAdresseDTO);
    }

    @DeleteMapping("/{adresseId}/kunden/{kundeId}")
    public ResponseEntity<AdresseResponseDTO> removeKundeFromAdresse(@PathVariable Long adresseId, @PathVariable Long kundeId) {
        AdresseResponseDTO updatedAdresseDTO = AdresseMapper.toResponseDTO(adresseService.removeKundeFromAdresse(adresseId, kundeId)); // 使用类名调用静态方法
        return ResponseEntity.ok(updatedAdresseDTO);
    }
}