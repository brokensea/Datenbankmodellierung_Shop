package de.sp.Datenbankmodellierung_Shop.controller;

import de.sp.Datenbankmodellierung_Shop.dtos.KundeDTO;
import de.sp.Datenbankmodellierung_Shop.dtos.requestDTO.KundeRequestDTO;
import de.sp.Datenbankmodellierung_Shop.dtos.responseDTO.KundeResponseDTO;
import de.sp.Datenbankmodellierung_Shop.entities.Kunde;
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

    public KundeController(KundeService kundeService, KundeMapper kundeMapper) {
        this.kundeService = kundeService;
    }

    @PostMapping
    public ResponseEntity<KundeResponseDTO> createKunde(@RequestBody KundeRequestDTO kundeDTO) {
        KundeResponseDTO savedKundeDTO = kundeService.save(kundeDTO);
        return ResponseEntity.ok(savedKundeDTO);  // 返回 200 OK 和保存的 Kunde DTO
    }


    @GetMapping
    public ResponseEntity<List<KundeResponseDTO>> getAllKunden() {
        // Get all Kunden and return the list of DTOs
        List<KundeResponseDTO> kundeDTOS = kundeService.findAll().stream()
                .map(KundeMapper::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(kundeDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<KundeResponseDTO> getKundeById(@PathVariable Long id) {
        // Find Kunde by ID and return the DTO representation if found
        return kundeService.findById(id)
                .map(KundeMapper::toResponseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<KundeResponseDTO> updateKunde(@PathVariable Long id, @RequestBody KundeRequestDTO kundeDTO) {
        // 更新 Kunde 并返回更新后的 DTO 表示
        KundeResponseDTO updatedKundeDTO = kundeService.update(id, kundeDTO);  // 直接调用服务方法
        return ResponseEntity.ok(updatedKundeDTO);  // 返回 200 OK 和更新后的 Kunde DTO
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKunde(@PathVariable Long id) {
        // Delete the Kunde by ID
        kundeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}