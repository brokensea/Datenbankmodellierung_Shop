package de.sp.Datenbankmodellierung_Shop.controller;

import de.sp.Datenbankmodellierung_Shop.dtos.WarenkorbDTO;
import de.sp.Datenbankmodellierung_Shop.dtos.AddArticleToWarenkorbDto;
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
    private final WarenkorbMapper warenkorbMapper;

    public WarenkorbController(WarenkorbService warenkorbService, WarenkorbMapper warenkorbMapper) {
        this.warenkorbService = warenkorbService;
        this.warenkorbMapper = warenkorbMapper;
    }

    @PostMapping
    public ResponseEntity<WarenkorbDTO> createWarenkorb(@RequestBody WarenkorbDTO warenkorbDTO) {
        try {
            WarenkorbDTO savedWarenkorbDTO = warenkorbMapper.toDto(
                    warenkorbService.save(warenkorbMapper.toEntity(warenkorbDTO))
            );
            return ResponseEntity.ok(savedWarenkorbDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<WarenkorbDTO>> getAllWarenkoerbe() {
        List<WarenkorbDTO> warenkorbDTOs = warenkorbService.findAll().stream()
                .map(warenkorbMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(warenkorbDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WarenkorbDTO> getWarenkorbById(@PathVariable Long id) {
        return warenkorbService.findById(id)
                .map(warenkorbMapper::toDto)
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
    public ResponseEntity<Void> addArticleToKundenWarenkorb(@RequestBody AddArticleToWarenkorbDto dto) {
        try {
            warenkorbService.addArticleToKundenWarenkorb(dto);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}