package it.epicode.BE_W6_Exam.viaggi;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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


	private LocalDate data;

	private StatoViaggio stato;
}
