package it.epicode.BE_W6_Exam.dipendenti;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DipendenteRunner implements CommandLineRunner {
	private final DipendenteRepository dipendenteRepository;
	private final Faker faker;
	@Override
	public void run(String... args) throws Exception {
		for (int i = 0; i < 10; i++) {
			Dipendente dipendente = new Dipendente();
			dipendente.setUsername(faker.name().username());
			dipendente.setNome(faker.name().firstName());
			dipendente.setCognome(faker.name().lastName());
			dipendente.setEmail(faker.internet().emailAddress());
			dipendenteRepository.save(dipendente);
		}
	}

}
