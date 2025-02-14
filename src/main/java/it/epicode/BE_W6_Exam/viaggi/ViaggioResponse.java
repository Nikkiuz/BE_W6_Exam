package it.epicode.BE_W6_Exam.viaggi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViaggioResponse {

	private Long id;
	private String destinazione;
	private LocalDate data;
	private StatoViaggio stato;
}
