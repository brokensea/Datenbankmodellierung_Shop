package de.sp.Datenbankmodellierung_Shop.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Artikel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String bezeichnung;
    private String beschreibung;
    private Double preis;
    private String status;
    private Boolean bestand;

    @ManyToMany(mappedBy = "artikels")
    private Set<Bestellung> bestellungen;

    @ManyToMany(mappedBy = "artikel")
    private Set<Warenkorb> warenkoerbe;

    public Artikel() {
        this.bestellungen = new HashSet<>();
        this.warenkoerbe = new HashSet<>();
    }

    @Override
    public String toString() {
        return "Artikel{" +
                "id=" + id +
                ", bezeichnung='" + bezeichnung + '\'' +
                '}';
    }
}
