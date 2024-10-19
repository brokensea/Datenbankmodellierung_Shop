package de.sp.Datenbankmodellierung_Shop.repositories;

import de.sp.Datenbankmodellierung_Shop.entities.BestellungArtikel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BestellungArtikelRepository extends JpaRepository<BestellungArtikel, Long> {
}
