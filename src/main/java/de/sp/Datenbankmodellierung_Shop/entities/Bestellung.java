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

    private String order_datum;
    private String status;
    private Double gesamtpreis;

    /*@ManyToOne*/
    @ManyToOne(cascade = CascadeType.PERSIST)
    /* @JoinColumn(name = "kunde_id", nullable = false)*/
    @JoinColumn(name = "kunde_id")
    private Kunde kunde;

    @ManyToMany
    @JoinTable(
            name = "bestellung_artikel",
            joinColumns = @JoinColumn(name = "bestellung_id"),
            inverseJoinColumns = @JoinColumn(name = "artikel_id")
    )
    private Set<Artikel> artikels;

    public Bestellung() {
        artikels = new HashSet<>();
    }

}
