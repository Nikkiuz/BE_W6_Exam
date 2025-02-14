package it.epicode.BE_W6_Exam.prenotazioni;

import it.epicode.BE_W6_Exam.dipendenti.Dipendente;
import it.epicode.BE_W6_Exam.viaggi.Viaggio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "prenotazioni")
public class Prenotazione {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "dipendente_id", nullable = false)
	private Dipendente dipendente;

	@ManyToOne
	@JoinColumn(name = "viaggio_id", nullable = false)
	private Viaggio viaggio;

	@Column(nullable = false)
	private LocalDate dataRichiesta;

	private String note;
	private String preferenze;
}