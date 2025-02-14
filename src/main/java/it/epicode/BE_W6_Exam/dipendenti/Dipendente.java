package it.epicode.BE_W6_Exam.dipendenti;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.epicode.BE_W6_Exam.prenotazioni.Prenotazione;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "dipendenti")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dipendente {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@NotBlank
	@Column(unique = true, nullable = false)
	private String username;

	@NotBlank
	private String nome;

	@NotBlank
	private String cognome;

	@Email
	@NotBlank
	@Column(unique = true, nullable = false)
	private String email;

	private String imageUrl;

	@ToString.Exclude
	@JsonIgnoreProperties("dipendente")
	@OneToMany(mappedBy = "dipendente")
	private Set<Prenotazione> prenotazioni = new HashSet<>();
}
