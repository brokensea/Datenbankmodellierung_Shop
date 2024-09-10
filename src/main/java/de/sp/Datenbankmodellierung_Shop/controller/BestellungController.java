package de.sp.Datenbankmodellierung_Shop.controller;

import de.sp.Datenbankmodellierung_Shop.dtos.AddArticleToKundenBestellungDto;
import de.sp.Datenbankmodellierung_Shop.dtos.BestellungDTO;
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
    private final BestellungMapper bestellungMapper;

    public BestellungController(BestellungService bestellungService, BestellungMapper bestellungMapper) {
        this.bestellungService = bestellungService;
        this.bestellungMapper = bestellungMapper;
    }

    @PostMapping
    public ResponseEntity<BestellungDTO> createBestellung(@RequestBody BestellungDTO bestellungDTO) {
        try {
            Bestellung bestellung = bestellungMapper.toEntity(bestellungDTO);
            Bestellung savedBestellung = bestellungService.save(bestellung);
            return ResponseEntity.ok(bestellungMapper.toDto(savedBestellung));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<BestellungDTO>> getAllBestellungen() {
        List<BestellungDTO> bestellungDTOs = bestellungService.findAll().stream()
                .map(bestellungMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(bestellungDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BestellungDTO> getBestellungById(@PathVariable Long id) {
        return bestellungService.findById(id)
                .map(bestellungMapper::toDto)
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

    @PostMapping("/addArticle")
    public ResponseEntity<Void> addArticleToKundenBestellung(@RequestBody AddArticleToKundenBestellungDto dto) {
        try {
            bestellungService.addArticleToKundenBestellung(dto);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}