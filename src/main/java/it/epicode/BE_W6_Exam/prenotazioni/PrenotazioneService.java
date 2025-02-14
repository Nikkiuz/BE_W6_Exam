package it.epicode.BE_W6_Exam.prenotazioni;

import it.epicode.BE_W6_Exam.dipendenti.Dipendente;
import it.epicode.BE_W6_Exam.dipendenti.DipendenteRepository;
import it.epicode.BE_W6_Exam.viaggi.Viaggio;
import it.epicode.BE_W6_Exam.viaggi.ViaggioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional
class PrenotazioneService {
	private final PrenotazioneRepository prenotazioneRepository;
	private final DipendenteRepository dipendenteRepository;
	private final ViaggioRepository viaggioRepository;

	public Prenotazione savePrenotazione(Long dipendenteId, Long viaggioId, LocalDate dataRichiesta, String note, String preferenze) {
		Dipendente dipendente = dipendenteRepository.findById(dipendenteId)
			.orElseThrow(() -> new EntityNotFoundException("Dipendente non trovato"));
		Viaggio viaggio = viaggioRepository.findById(viaggioId)
			.orElseThrow(() -> new EntityNotFoundException("Viaggio non trovato"));

		if (prenotazioneRepository.existsByDipendenteAndViaggio(dipendente, viaggio)) {
			throw new IllegalStateException("Il dipendente ha già una prenotazione per questo viaggio");
		}

		Prenotazione prenotazione = new Prenotazione();
		prenotazione.setDipendente(dipendente);
		prenotazione.setViaggio(viaggio);
		prenotazione.setDataRichiesta(dataRichiesta);
		prenotazione.setNote(note);
		prenotazione.setPreferenze(preferenze);

		return prenotazioneRepository.save(prenotazione);
	}
}