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
    @OneToMany(mappedBy = "adresse", cascade = CascadeType.ALL)
    /* @JsonManagedReference*/
    private Set<Kunde> kunden = new HashSet<>();

    public Adresse() {
        this.kunden = new HashSet<>();
    }
}

/*
orphanRemoval = true：orphanRemoval 是一个布尔值选项。当设置为 true 时，如果将某个 Kunde 从 Adresse 的 kunden 集合中移除（例如通过 kunden.remove(kunde)），那么该 Kunde 实体将自动从数据库中删除。也就是说，当一个 Kunde 实体不再属于任何 Adresse，它将被视为"孤儿"，并会被删除。
*/
