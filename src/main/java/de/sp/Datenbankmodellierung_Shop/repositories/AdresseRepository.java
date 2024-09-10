package de.sp.Datenbankmodellierung_Shop.repositories;

import de.sp.Datenbankmodellierung_Shop.entities.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdresseRepository extends JpaRepository<Adresse,Long> {
}
