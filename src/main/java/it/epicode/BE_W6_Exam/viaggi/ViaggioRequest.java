package it.epicode.BE_W6_Exam.viaggi;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViaggioRequest {
	@NotBlank(message = "Destinazione obbligatoria")
	private String destinazione;

	@NotEmpty(message = "Data obbligatoria")
	private LocalDate data;

	@NotBlank(message = "Stato obbligatorio")
	private StatoViaggio stato;
}
