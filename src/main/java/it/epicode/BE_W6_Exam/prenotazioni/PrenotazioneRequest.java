package it.epicode.BE_W6_Exam.prenotazioni;

import it.epicode.BE_W6_Exam.dipendenti.Dipendente;
import it.epicode.BE_W6_Exam.viaggi.Viaggio;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrenotazioneRequest {

	private LocalDate dataRichiesta;

	@NotNull(message = "Id Dipendente obbligatorio")
	public Long dipendenteId;

	@NotNull(message = "Id Viaggio obbligatorio")
	public Long viaggioId;

	public String note;

	public String preferenze;
}
