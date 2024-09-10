package de.sp.Datenbankmodellierung_Shop.repositories;

import de.sp.Datenbankmodellierung_Shop.entities.Kunde;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KundeRepository extends JpaRepository<Kunde,Long> {
}
