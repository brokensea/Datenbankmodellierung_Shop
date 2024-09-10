package de.sp.Datenbankmodellierung_Shop.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // 仅包含标注了 @Include 的字段
public class Adresse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include // 将 id 包含在 equals 和 hashCode 中
    private Long id;
    private String adresseInformation;
    @OneToMany(mappedBy = "adresse", cascade = CascadeType.ALL, orphanRemoval = true)
    /* @JsonManagedReference*/
    private Set<Kunde> kunden = new HashSet<>();

    public Adresse() {
        this.kunden = new HashSet<>();
    }
}
