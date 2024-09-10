package de.sp.Datenbankmodellierung_Shop.controller;

import de.sp.Datenbankmodellierung_Shop.dtos.ArtikelDTO;
import de.sp.Datenbankmodellierung_Shop.services.ArtikelService;
import de.sp.Datenbankmodellierung_Shop.mapper.ArtikelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/artikel")
public class ArtikelController {

    private final ArtikelService artikelService;
    private final ArtikelMapper artikelMapper;

    public ArtikelController(ArtikelService artikelService, ArtikelMapper artikelMapper) {
        this.artikelService = artikelService;
        this.artikelMapper = artikelMapper;
    }

    @PostMapping
    public ResponseEntity<ArtikelDTO> createArtikel(@RequestBody ArtikelDTO artikelDTO) {
        // Convert DTO to entity and save it
        ArtikelDTO savedArtikelDTO = artikelMapper.toDto(
                artikelService.save(artikelMapper.toEntity(artikelDTO))
        );
        return ResponseEntity.ok(savedArtikelDTO);
    }

    @GetMapping
    public ResponseEntity<List<ArtikelDTO>> getAllArtikel() {
        List<ArtikelDTO> artikelDTOs = artikelService.findAll().stream()
                .map(artikelMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(artikelDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtikelDTO> getArtikelById(@PathVariable Long id) {
        return artikelService.findById(id)
                .map(artikelMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtikel(@PathVariable Long id) {
        if (artikelService.findById(id).isPresent()) {
            artikelService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}