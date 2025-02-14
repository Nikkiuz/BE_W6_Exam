package it.epicode.BE_W6_Exam.viaggi;

import it.epicode.BE_W6_Exam.prenotazioni.Prenotazione;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViaggioDettaglioResponse {

	private Long id;
	private String destinazione;
	private LocalDate data;
	private StatoViaggio stato;
	private Set<Prenotazione> prenotazioni;

}
