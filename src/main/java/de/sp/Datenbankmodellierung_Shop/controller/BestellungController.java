package de.sp.Datenbankmodellierung_Shop.controller;

import de.sp.Datenbankmodellierung_Shop.dtos.AddArticleToKundenBestellungDto;
import de.sp.Datenbankmodellierung_Shop.dtos.BestellungDTO;
import de.sp.Datenbankmodellierung_Shop.dtos.responseDTO.BestellungResponseDTO;
import de.sp.Datenbankmodellierung_Shop.entities.Bestellung;
import de.sp.Datenbankmodellierung_Shop.services.BestellungService;
import de.sp.Datenbankmodellierung_Shop.mapper.BestellungMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/bestellungen")
public class BestellungController {

    private final BestellungService bestellungService;

    public BestellungController(BestellungService bestellungService) {
        this.bestellungService = bestellungService;
    }


    @GetMapping
    public ResponseEntity<List<BestellungResponseDTO>> getAllBestellungen() {
        List<BestellungResponseDTO> bestellungDTOs = bestellungService.findAll().stream()
                .map(BestellungMapper::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(bestellungDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BestellungResponseDTO> getBestellungById(@PathVariable Long id) {
        return bestellungService.findById(id)
                .map(BestellungMapper::toResponseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBestellung(@PathVariable Long id) {
        if (bestellungService.findById(id).isPresent()) {
            bestellungService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/addArticleToOrder")
    public ResponseEntity<BestellungResponseDTO> addArticleToKundenBestellung(@RequestBody AddArticleToKundenBestellungDto dto) {
        try {
            BestellungResponseDTO updatedBestellungDTO = bestellungService.addArticleToKundenBestellung(dto);
            return ResponseEntity.ok(updatedBestellungDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}