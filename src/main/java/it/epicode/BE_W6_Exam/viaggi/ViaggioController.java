package it.epicode.BE_W6_Exam.viaggi;

import it.epicode.BE_W6_Exam.responses.CreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/viaggi")
@RequiredArgsConstructor
public class ViaggioController {
	private final ViaggioService viaggioService;

	//GET
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ViaggioResponse> findAll() {
		return viaggioService.findAll();
	}

	//GET con Id
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Viaggio findById(@PathVariable Long id) {
		return viaggioService.findById(id);
	}

	//POST
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CreateResponse save(@RequestBody ViaggioRequest request) {
		return viaggioService.save(request);
	}

	//PATCH STATUS
	@PatchMapping("/{id}/{stato}")
	@ResponseStatus(HttpStatus.OK)
	public Viaggio updateStato(@PathVariable Long id, @PathVariable StatoViaggio stato) {
		return viaggioService.updateStato(id, stato);
	}

	//PUT
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Viaggio modify(@PathVariable Long id, @RequestBody ViaggioRequest request) {
		return viaggioService.modify(id, request);
	}

	//DELETE
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable Long id) {
		viaggioService.delete(id);
	}

}
