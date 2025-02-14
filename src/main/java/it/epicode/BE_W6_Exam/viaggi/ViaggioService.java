package it.epicode.BE_W6_Exam.viaggi;

import it.epicode.BE_W6_Exam.responses.CreateResponse;
import jakarta.persistence.EntityExistsException;
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
@Validated
class ViaggioService {
	private final ViaggioRepository viaggioRepository;

	public List<ViaggioResponse> findAll() {
		List<ViaggioResponse> response = viaggioResponseListFromEntityList(viaggioRepository.findAll());
		return response;
	}

	public Viaggio modify(Long id, ViaggioRequest request) {
		Viaggio viaggio = findById(id);
		BeanUtils.copyProperties(request, viaggio);
		viaggioRepository.save(viaggio);
		return viaggio;
	}

	public CreateResponse save(@Valid ViaggioRequest request) {
		if(viaggioRepository.existsByDestinazione(request.getDestinazione())) {
			throw new EntityExistsException("Viaggio già esistente");
		}

		Viaggio viaggio = viaggioFromRequest(request);

		CreateResponse response = new CreateResponse();
		viaggioRepository.save(viaggio);
		BeanUtils.copyProperties(viaggio, response);

		return response;
	}

	public Viaggio findById(Long id) {
		if(!viaggioRepository.existsById(id)) {
			throw new EntityNotFoundException("Viaggio non trovato");
	}
		return viaggioRepository.findById(id).get();
	}

	@Transactional
	public ViaggioDettaglioResponse findViaggioDettaglioResponseById(Long id) {
		if(!viaggioRepository.existsById(id)) {
			throw new EntityNotFoundException("Viaggio non trovato");
		}
		Viaggio viaggio = viaggioRepository.findById(id).get();
		ViaggioDettaglioResponse response = new ViaggioDettaglioResponse();
		BeanUtils.copyProperties(viaggio, response);
		response.setPrenotazioni(viaggio.getPrenotazioni());
		return response;
	}

	public void delete(Long id) {
		Viaggio viaggio = findById(id);
		viaggioRepository.delete(viaggio);
	}

	public ViaggioResponse viaggioResponseFromEntity(Viaggio viaggio) {
		ViaggioResponse response = new ViaggioResponse();
		BeanUtils.copyProperties(viaggio, response);
		return response;
	}

	public List<ViaggioResponse> viaggioResponseListFromEntityList(List<Viaggio> viaggi) {
		return viaggi.stream().map(this::viaggioResponseFromEntity).toList();
	}

	public Viaggio viaggioFromRequest(ViaggioRequest request) {
		Viaggio viaggio = new Viaggio();
		BeanUtils.copyProperties(request, viaggio);
		return viaggio;
	}

}
