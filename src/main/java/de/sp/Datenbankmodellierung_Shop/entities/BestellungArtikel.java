package de.sp.Datenbankmodellierung_Shop.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BestellungArtikel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bestellung_id")
    private Bestellung bestellung;

    @ManyToOne
    @JoinColumn(name = "artikel_id")
    private Artikel artikel;

    private Integer anzahl; // 存储订单中每个商品的数量


}
