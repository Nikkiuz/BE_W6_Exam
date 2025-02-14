package it.epicode.BE_W6_Exam.dipendenti;

import it.epicode.BE_W6_Exam.prenotazioni.Prenotazione;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DipendenteDettaglioResponse {

	private Long id;
	private String username;
	private String email;
	private String nome;
	private String cognome;
	private String imageUrl;
	private Set<Prenotazione> prenotazioni;

}
