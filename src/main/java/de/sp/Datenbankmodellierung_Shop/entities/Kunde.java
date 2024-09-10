package de.sp.Datenbankmodellierung_Shop.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Kunde {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include // 将 id 包含在 equals 和 hashCode 中
    private Long id;
    private String nachname;
    private String vorname;
    private String email;

    @ManyToOne
    /*@JoinColumn(name = "adresse_id", nullable = false)*/
    @JoinColumn(name = "adresse_id")
    /* @JsonIgnore*/
    private Adresse adresse;

    @OneToMany(mappedBy = "kunde", cascade = CascadeType.ALL)
    private Set<Bestellung> bestellungen = new HashSet<>();

    @OneToOne(mappedBy = "kunde", cascade = CascadeType.ALL)
    private Warenkorb warenkorb;


    public Kunde() {
        this.bestellungen = new HashSet<>();
    }


}
