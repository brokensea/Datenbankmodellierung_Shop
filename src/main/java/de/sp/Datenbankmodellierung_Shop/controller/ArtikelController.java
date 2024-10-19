package de.sp.Datenbankmodellierung_Shop.controller;

import de.sp.Datenbankmodellierung_Shop.dtos.ArtikelDTO;
import de.sp.Datenbankmodellierung_Shop.dtos.requestDTO.ArtikelRequestDTO;
import de.sp.Datenbankmodellierung_Shop.dtos.responseDTO.ArtikelResponseDTO;
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


    public ArtikelController(ArtikelService artikelService, ArtikelMapper artikelMapper) {
        this.artikelService = artikelService;
    }

    @PostMapping
    public ResponseEntity<ArtikelResponseDTO> createArtikel(@RequestBody ArtikelRequestDTO artikelDTO) {
        ArtikelResponseDTO savedArtikelDTO = ArtikelMapper.toResponseDTO(
                artikelService.save(ArtikelMapper.toEntity(artikelDTO))
        );
        return ResponseEntity.ok(savedArtikelDTO);
    }

    @GetMapping
    public ResponseEntity<List<ArtikelResponseDTO>> getAllArtikel() {
        List<ArtikelResponseDTO> artikelDTOs = artikelService.findAll().stream()
                .map(ArtikelMapper::toResponseDTO) // 使用 artikelMapper
                .collect(Collectors.toList());
        return ResponseEntity.ok(artikelDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtikelResponseDTO> getArtikelById(@PathVariable Long id) {
        return artikelService.findById(id)
                .map(ArtikelMapper::toResponseDTO) // 使用 artikelMapper
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