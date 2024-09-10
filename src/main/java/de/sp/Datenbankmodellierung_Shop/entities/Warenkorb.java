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
public class Warenkorb {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @OneToOne
    /* @JoinColumn(name = "kunde_id", nullable = false)*/
    @JoinColumn(name = "kunde_id")
    private Kunde kunde;
    @ManyToMany
    @JoinTable(
            name = "warenkorb_artikel",
            joinColumns = @JoinColumn(name = "warenkorb_id"),
            inverseJoinColumns = @JoinColumn(name = "artikel_id")
    )
    private Set<Artikel> artikel;

    public Warenkorb() {
        this.artikel = new HashSet<>();
    }
}
