package de.sp.Datenbankmodellierung_Shop.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Bestellung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include // 将 id 包含在 equals 和 hashCode 中
    private Long id;

    private String orderDatum;
    private String status;
    private Double gesamtpreis;


    @ManyToOne()
    @JoinColumn(name = "kunde_id")
    private Kunde kunde;


    /* @OneToMany(mappedBy = "bestellung")*/
    @OneToMany(mappedBy = "bestellung", cascade = CascadeType.ALL)
    private Set<BestellungArtikel> bestellungArtikels = new HashSet<>();

    public Bestellung() {
    }

}
