package it.epicode.BE_W6_Exam.prenotazioni;

import it.epicode.BE_W6_Exam.dipendenti.Dipendente;
import it.epicode.BE_W6_Exam.dipendenti.DipendenteRepository;
import it.epicode.BE_W6_Exam.responses.CreateResponse;
import it.epicode.BE_W6_Exam.viaggi.Viaggio;
import it.epicode.BE_W6_Exam.viaggi.ViaggioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Validated
class PrenotazioneService {
	private final PrenotazioneRepository prenotazioneRepository;
	private final ViaggioRepository viaggioRepository;
	private final DipendenteRepository dipendenteRepository;


	public List<PrenotazioneResponse> findAll() {
		List<PrenotazioneResponse> response = prenotazioneResponseListFromEntityList(prenotazioneRepository.findAll());
		return response;
	}

	public Prenotazione modify(Long id, PrenotazioneRequest request) {
		Prenotazione prenotazione = findById(id);
		BeanUtils.copyProperties(request, prenotazione);
		prenotazioneRepository.save(prenotazione);
		return prenotazione;
	}

	public CreateResponse save(@Valid PrenotazioneRequest request) {
		if(prenotazioneRepository.existsByDipendenteIdAndViaggioId(request.getDipendenteId(), request.getViaggioId())) {
			throw new RuntimeException("Prenotazione già esistente");
		}

		Prenotazione prenotazione = prenotazioneFromRequest(request);
		Dipendente dipendente = dipendenteRepository.findById(request.getDipendenteId()).get();
		Viaggio viaggio = viaggioRepository.findById(request.getViaggioId()).get();
		prenotazione.setDipendente(dipendente);
		prenotazione.setViaggio(viaggio);
		CreateResponse response = new CreateResponse();
		prenotazioneRepository.save(prenotazione);
		BeanUtils.copyProperties(prenotazione, response);

		return response;

	}

	public void assegnaDipendenteAViaggio(Long viaggioId, Long dipendenteId) {
		// Verifica se il dipendente esiste
		Dipendente dipendente = dipendenteRepository.findById(dipendenteId)
			.orElseThrow(() -> new RuntimeException("Dipendente non trovato"));

		// Verifica se il viaggio esiste
		Viaggio viaggio = viaggioRepository.findById(viaggioId)
			.orElseThrow(() -> new RuntimeException("Viaggio non trovato"));

		// Verifica se il dipendente è già assegnato a un altro viaggio nella stessa data
		boolean esistePrenotazione = prenotazioneRepository.existsByDipendenteIdAndViaggioId(dipendenteId, viaggioId);
		if (esistePrenotazione) {
			throw new RuntimeException("Il dipendente è già assegnato a questo viaggio.");
		}

		// Crea la prenotazione
		Prenotazione prenotazione = new Prenotazione();
		prenotazione.setViaggio(viaggio);
		prenotazione.setDipendente(dipendente);
		prenotazioneRepository.save(prenotazione);

	}

	public Prenotazione findById(Long id) {

		if (!prenotazioneRepository.existsById(id)) {
			throw new EntityNotFoundException("Prenotazione non trovata");
		}
		return prenotazioneRepository.findById(id).get();
	}

	@Transactional
	public PrenotazioneResponse findPrenotazioneResponseById(Long id) {
		if(!prenotazioneRepository.existsById(id)) {
			throw new EntityNotFoundException("Prenotazione non trovata");
		}
		Prenotazione prenotazione = prenotazioneRepository.findById(id).get();
		PrenotazioneResponse response = new PrenotazioneResponse();
		BeanUtils.copyProperties(prenotazione, response);
		return response;
	}

	public void delete(Long id) {
		if(!prenotazioneRepository.existsById(id)) {
			throw new EntityNotFoundException("Prenotazione non trovata");
		}
		prenotazioneRepository.deleteById(id);
	}

	public PrenotazioneResponse prenotazioneResponseFromEntity(Prenotazione prenotazione) {
		PrenotazioneResponse response = new PrenotazioneResponse();
		BeanUtils.copyProperties(prenotazione, response);
		return response;
	}

	public List<PrenotazioneResponse> prenotazioneResponseListFromEntityList(List<Prenotazione> prenotazioni) {
		return prenotazioni.stream().map(this::prenotazioneResponseFromEntity).toList();
	}


	public Prenotazione prenotazioneFromRequest(PrenotazioneRequest request) {
		Prenotazione prenotazione = new Prenotazione();
		BeanUtils.copyProperties(request, prenotazione);
		return prenotazione;
	}


}