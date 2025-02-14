package it.epicode.BE_W6_Exam.viaggi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViaggioRepository extends JpaRepository<Viaggio, Long> {
	public Viaggio findByDestinazione(String destinazione);
	public boolean existsByDestinazione(String destinazione);
}
