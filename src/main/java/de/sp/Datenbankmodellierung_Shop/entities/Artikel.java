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

    @OneToMany(mappedBy = "artikel")
    private Set<BestellungArtikel> bestellungArtikels = new HashSet<>();

    @ManyToMany(mappedBy = "artikel")
    private Set<Warenkorb> warenkoerbe = new HashSet<>();
    ;

    public Artikel() {
    }

    @Override
    public String toString() {
        return "Artikel{" +
                "id=" + id +
                ", bezeichnung='" + bezeichnung + '\'' +
                '}';
    }
}
