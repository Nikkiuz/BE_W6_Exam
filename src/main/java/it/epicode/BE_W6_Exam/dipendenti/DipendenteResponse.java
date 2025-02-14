package it.epicode.BE_W6_Exam.dipendenti;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DipendenteResponse {
	private Long id;
	private String username;
	private String nome;
	private String cognome;
	private String email;
	private String imageUrl;
}
