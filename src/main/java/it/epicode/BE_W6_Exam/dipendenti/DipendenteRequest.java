package it.epicode.BE_W6_Exam.dipendenti;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DipendenteRequest {
	@NotBlank(message = "Username obbligatorio")
	private String username;

	@NotBlank(message = "Nome obbligatorio")
	private String nome;

	@NotBlank(message = "Cognome obbligatorio")
	private String cognome;

	@NotBlank(message = "Email obbligatorio")
	private String email;

	private String imageUrl;
}
