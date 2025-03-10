package it.epicode.BE_W6_Exam.viaggi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.epicode.BE_W6_Exam.prenotazioni.Prenotazione;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "viaggi")
public class Viaggio {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@NotBlank
	private String destinazione;

	private LocalDate data;

	@Enumerated(EnumType.STRING)
	private StatoViaggio stato;

	@ToString.Exclude
	@JsonIgnoreProperties("viaggio")
	@OneToMany(mappedBy = "viaggio")
	private Set<Prenotazione> prenotazioni = new HashSet<>();
}

enum StatoViaggio {
	IN_PROGRAMMA, DISPONIBILE, COMPLETATO
}