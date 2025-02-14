package it.epicode.BE_W6_Exam.dipendenti;

import it.epicode.BE_W6_Exam.responses.CreateResponse;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Validated
public class DipendenteService {
	private final DipendenteRepository dipendenteRepository;

	public List<DipendenteResponse> findAll() {
		List<DipendenteResponse> response = dipendenteResponseListFromEntityList(dipendenteRepository.findAll());
		return response;
	}

	public Dipendente modify(Long id, DipendenteRequest request){
		Dipendente dipendente = dipendenteRepository.findById(id).get();
		BeanUtils.copyProperties(request, dipendente);
		dipendenteRepository.save(dipendente);
		return dipendente;
	}

	public CreateResponse save(@Valid DipendenteRequest request) {
		if(dipendenteRepository.existsByEmail(request.getEmail())) {
			throw new EntityExistsException("Dipendente già esistente");
		}

		if (dipendenteRepository.existsByNomeAndCognome(request.getNome(), request.getCognome())) {
			throw new EntityExistsException("Dipendente già esistente: controllo su nome e cognome");
		}

		Dipendente dipendente = dipendenteFromRequest(request);

		CreateResponse response = new CreateResponse();
		dipendenteRepository.save(dipendente);
		BeanUtils.copyProperties(dipendente, response);

		return response;
	}

	public Dipendente findById(Long id) {
		if(!dipendenteRepository.existsById(id)) {
			throw new EntityNotFoundException("Dipendente non trovato");
		}
		return dipendenteRepository.findById(id).get();
	}

	@Transactional
	public DipendenteDettaglioResponse findDipendenteResponseById(Long id) {
		if(!dipendenteRepository.existsById(id)) {
			throw new EntityNotFoundException("Dipendente non trovato");
		}
		Dipendente dipendente = dipendenteRepository.findById(id).get();
		DipendenteDettaglioResponse response = new DipendenteDettaglioResponse();
		BeanUtils.copyProperties(dipendente, response);
		response.setPrenotazioni(dipendente.getPrenotazioni());
		return response;
	}

	public void delete(Long id) {
		if (!dipendenteRepository.existsById(id)) {
			throw new EntityNotFoundException("Dipendente non trovato");
		}
		dipendenteRepository.deleteById(id);
	}

	public DipendenteResponse dipendenteResponseFromEntity(Dipendente dipendente) {
		DipendenteResponse response = new DipendenteResponse();
		BeanUtils.copyProperties(dipendente, response);
		return response;
	}

	public List<DipendenteResponse> dipendenteResponseListFromEntityList(List<Dipendente> dipendenti) {
		return dipendenti.stream().map(this::dipendenteResponseFromEntity).toList();
	}

	public Dipendente dipendenteFromRequest(DipendenteRequest request) {
		Dipendente dipendente = new Dipendente();
		BeanUtils.copyProperties(request, dipendente);
		return dipendente;
	}

}

