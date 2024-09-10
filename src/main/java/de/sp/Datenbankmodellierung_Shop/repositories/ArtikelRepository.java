package de.sp.Datenbankmodellierung_Shop.repositories;

import de.sp.Datenbankmodellierung_Shop.entities.Artikel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtikelRepository extends JpaRepository<Artikel,Long> {
}
