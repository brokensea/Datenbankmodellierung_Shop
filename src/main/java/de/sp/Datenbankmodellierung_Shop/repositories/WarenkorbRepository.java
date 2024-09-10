package de.sp.Datenbankmodellierung_Shop.repositories;

import de.sp.Datenbankmodellierung_Shop.entities.Warenkorb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarenkorbRepository extends JpaRepository<Warenkorb, Long> {
}
