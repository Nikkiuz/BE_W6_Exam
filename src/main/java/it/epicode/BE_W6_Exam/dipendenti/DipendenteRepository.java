package it.epicode.BE_W6_Exam.dipendenti;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DipendenteRepository extends JpaRepository<Dipendente, Long> {
	public Dipendente findByEmail(String email);
	public boolean existsByEmail(String email);
	public Dipendente findByUsername(String username);
	public boolean existsByUsername(String username);
	public boolean existByNomeAndCognome(String nome, String cognome);
}
