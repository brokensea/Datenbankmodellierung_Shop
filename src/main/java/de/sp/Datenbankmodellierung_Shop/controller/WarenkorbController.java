package de.sp.Datenbankmodellierung_Shop.controller;

import de.sp.Datenbankmodellierung_Shop.dtos.WarenkorbDTO;
import de.sp.Datenbankmodellierung_Shop.dtos.AddArticleToWarenkorbDto;
import de.sp.Datenbankmodellierung_Shop.dtos.requestDTO.WarenkorbRequestDTO;
import de.sp.Datenbankmodellierung_Shop.dtos.responseDTO.WarenkorbResponseDTO;
import de.sp.Datenbankmodellierung_Shop.services.WarenkorbService;
import de.sp.Datenbankmodellierung_Shop.mapper.WarenkorbMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/warenkoerbe")
public class WarenkorbController {

    private final WarenkorbService warenkorbService;

    public WarenkorbController(WarenkorbService warenkorbService, WarenkorbMapper warenkorbMapper) {
        this.warenkorbService = warenkorbService;
    }


    @GetMapping
    public ResponseEntity<List<WarenkorbResponseDTO>> getAllWarenkoerbe() {
        List<WarenkorbResponseDTO> warenkorbDTOs = warenkorbService.findAll().stream()
                .map(WarenkorbMapper::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(warenkorbDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WarenkorbResponseDTO> getWarenkorbById(@PathVariable Long id) {
        return warenkorbService.findById(id)
                .map(WarenkorbMapper::toResponseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWarenkorb(@PathVariable Long id) {
        if (warenkorbService.findById(id).isPresent()) {
            warenkorbService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/addArticle")
    public ResponseEntity<WarenkorbResponseDTO> addArticleToKundenWarenkorb(@RequestBody AddArticleToWarenkorbDto dto) {
        try {
            WarenkorbResponseDTO updatedWarenkorbDTO = warenkorbService.addArticleToKundenWarenkorb(dto);
            return ResponseEntity.ok(updatedWarenkorbDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}