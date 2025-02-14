package it.epicode.BE_W6_Exam.prenotazioni;

import it.epicode.BE_W6_Exam.dipendenti.Dipendente;
import it.epicode.BE_W6_Exam.viaggi.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;


@Repository
interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
	Prenotazione findByDipendenteAndDataRichiesta(Dipendente dipendente, LocalDate dataRichiesta);
	boolean existsByDipendenteIdAndViaggioId(Long dipendenteId, Long viaggioId);
}
