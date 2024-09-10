package de.sp.Datenbankmodellierung_Shop.controller;

import de.sp.Datenbankmodellierung_Shop.mapper.AdresseMapper;
import de.sp.Datenbankmodellierung_Shop.dtos.AdresseDTO;
import de.sp.Datenbankmodellierung_Shop.entities.Adresse;
import de.sp.Datenbankmodellierung_Shop.services.AdresseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import de.sp.Datenbankmodellierung_Shop.mapper.AdresseMapper;
import de.sp.Datenbankmodellierung_Shop.dtos.AdresseDTO;
import de.sp.Datenbankmodellierung_Shop.entities.Adresse;
import de.sp.Datenbankmodellierung_Shop.services.AdresseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/adressen")
public class AdresseController {

    private final AdresseService adresseService;
    private final AdresseMapper adresseMapper;

    public AdresseController(AdresseService adresseService, AdresseMapper adresseMapper) {
        this.adresseService = adresseService;
        this.adresseMapper = adresseMapper;
    }

    @GetMapping
    public ResponseEntity<List<AdresseDTO>> getAllAdressen() {
        List<Adresse> adressen = adresseService.findAll();
        if (adressen.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<AdresseDTO> adresseDTOs = adressen.stream()
                .map(adresseMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(adresseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdresseDTO> getAdresseById(@PathVariable Long id) {
        Optional<Adresse> adresse = adresseService.findById(id);
        return adresse.map(value -> ResponseEntity.ok(adresseMapper.toDto(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AdresseDTO> createAdresse(@RequestBody AdresseDTO adresseDTO) {
        Adresse adresse = adresseMapper.toEntity(adresseDTO);
        Adresse savedAdresse = adresseService.save(adresse);
        return ResponseEntity.ok(adresseMapper.toDto(savedAdresse));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdresse(@PathVariable Long id) {
        if (adresseService.findById(id).isPresent()) {
            adresseService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{adresseId}/kunden/{kundeId}")
    public ResponseEntity<AdresseDTO> addKundeToAdresse(@PathVariable Long adresseId, @PathVariable Long kundeId) {
        Adresse adresse = adresseService.addKundeToAdresse(adresseId, kundeId);
        return ResponseEntity.ok(adresseMapper.toDto(adresse));
    }

    @DeleteMapping("/{adresseId}/kunden/{kundeId}")
    public ResponseEntity<AdresseDTO> removeKundeFromAdresse(@PathVariable Long adresseId, @PathVariable Long kundeId) {
        Adresse adresse = adresseService.removeKundeFromAdresse(adresseId, kundeId);
        return ResponseEntity.ok(adresseMapper.toDto(adresse));
    }
}