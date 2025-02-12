package it.uniromatre.pinaback;

import it.uniromatre.pinaback.auth.AuthService;
import it.uniromatre.pinaback.auth.RegistrationRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

import java.time.LocalDate;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableAsync
public class PinaBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(PinaBackApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(
            AuthService service
    ) {
        return args -> {

            // Admin user

            LocalDate data = LocalDate.now();

            var admin = RegistrationRequest.builder()

                    .nome("admin")
                    .cognome("Gianfranco")
                    .codiceFiscale("1111111111111111")
                    .dataNascita(data)
                    .luogoDiNascita("Firenze")
                    .email("admin@mail.com")
                    .password("password")
                    .build();
            service.registerAdmin(admin);


            LocalDate data1 = LocalDate.now();

            var test = RegistrationRequest.builder()

                    .nome("user")
                    .cognome("user")
                    .codiceFiscale("2222222222222222")
                    .dataNascita(data1)
                    .luogoDiNascita("pisa")
                    .email("user@mail.com")
                    .password("password")
                    .build();
            service.registerAdmin(test);




        };
    }

}
