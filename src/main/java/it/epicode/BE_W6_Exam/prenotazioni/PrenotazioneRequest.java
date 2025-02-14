package it.epicode.BE_W6_Exam.prenotazioni;

import it.epicode.BE_W6_Exam.dipendenti.Dipendente;
import it.epicode.BE_W6_Exam.viaggi.Viaggio;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrenotazioneRequest {
	@NotBlank
	private LocalDate dataRichiesta;

	public Dipendente dipendente;

	public Viaggio viaggio;
}
