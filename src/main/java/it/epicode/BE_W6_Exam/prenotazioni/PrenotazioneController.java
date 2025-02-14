package it.epicode.BE_W6_Exam.prenotazioni;

import it.epicode.BE_W6_Exam.responses.CreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prenotazioni")
@RequiredArgsConstructor
public class PrenotazioneController {
	private final PrenotazioneService prenotazioneService;

	//GET
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<PrenotazioneResponse> findAll() {
		return prenotazioneService.findAll();
	}

	//GET con Id
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public PrenotazioneResponse findById(@PathVariable Long id) {
		return prenotazioneService.findById(id);
	}

	//POST
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CreateResponse save (@RequestBody PrenotazioneRequest request){
		return prenotazioneService.save(request);
	}

	//DELETE
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete (@PathVariable Long id){
		prenotazioneService.delete(id);
	}
}

