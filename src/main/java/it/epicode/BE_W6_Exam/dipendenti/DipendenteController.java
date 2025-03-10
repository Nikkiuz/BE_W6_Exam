package it.epicode.BE_W6_Exam.dipendenti;

import it.epicode.BE_W6_Exam.responses.CreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dipendenti")
@RequiredArgsConstructor
public class DipendenteController {
	private final DipendenteService dipendenteService;

	//GET
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<DipendenteResponse> findAll(){
		return dipendenteService.findAll();
	}

	//GET con Id
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Dipendente findById(@PathVariable Long id) {
		return dipendenteService.findById(id);
	}

	//POST
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasRole('USER')")
	public CreateResponse save(@RequestBody DipendenteRequest request) {
		return dipendenteService.save(request);
	}

	//PUT
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Dipendente modify(@PathVariable Long id, @RequestBody DipendenteRequest request) {
		return dipendenteService.modify(id, request);
	}

	//DELETE
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable Long id) {
		dipendenteService.delete(id);
	}

}
