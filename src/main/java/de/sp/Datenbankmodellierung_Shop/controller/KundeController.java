package de.sp.Datenbankmodellierung_Shop.controller;

import de.sp.Datenbankmodellierung_Shop.dtos.KundeDTO;
import de.sp.Datenbankmodellierung_Shop.mapper.KundeMapper;
import de.sp.Datenbankmodellierung_Shop.services.KundeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/kunden")
public class KundeController {

    private final KundeService kundeService;
    private final KundeMapper kundeMapper;

    public KundeController(KundeService kundeService, KundeMapper kundeMapper) {
        this.kundeService = kundeService;
        this.kundeMapper = kundeMapper;
    }

    @PostMapping
    public ResponseEntity<KundeDTO> createKunde(@RequestBody KundeDTO kundeDTO) {
        // Save the new Kunde and return the DTO representation
        KundeDTO savedKundeDTO = kundeMapper.toDto(
                kundeService.save(kundeMapper.toEntity(kundeDTO))
        );
        return ResponseEntity.ok(savedKundeDTO);
    }

    @GetMapping
    public ResponseEntity<List<KundeDTO>> getAllKunden() {
        // Get all Kunden and return the list of DTOs
        List<KundeDTO> kundeDTOS = kundeService.findAll().stream()
                .map(kundeMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(kundeDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<KundeDTO> getKundeById(@PathVariable Long id) {
        // Find Kunde by ID and return the DTO representation if found
        return kundeService.findById(id)
                .map(kundeMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<KundeDTO> updateKunde(@PathVariable Long id, @RequestBody KundeDTO kundeDTO) {
        // Update the Kunde and return the updated DTO representation
        KundeDTO updatedKundeDTO = kundeMapper.toDto(
                kundeService.update(id, kundeMapper.toEntity(kundeDTO))
        );
        return ResponseEntity.ok(updatedKundeDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKunde(@PathVariable Long id) {
        // Delete the Kunde by ID
        kundeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}