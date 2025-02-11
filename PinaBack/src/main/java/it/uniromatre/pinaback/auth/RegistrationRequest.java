package it.uniromatre.pinaback.auth;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class RegistrationRequest {


    @NotEmpty(message = "Nome è Obbligatorio")
    @NotBlank(message = "Nome è Obbligatorio")
    private String nome;

    @NotEmpty(message = "Cognome è Obbligatorio")
    @NotBlank(message = "Cognome è Obbligatorio")
    private String cognome;

    @NotEmpty(message = "Codice Fiscale è Obbligatorio")
    @NotBlank(message = "Codice Fiscale è Obbligatorio")
    @Size(min = 16, message = "Il Codice fiscale é di 16 Caratteri")
    private String codiceFiscale;



    @NotEmpty(message = "La data di nascita è Obbligatoria")
    @NotBlank(message = "La data di nascita è Obbligatoria")
    private LocalDate dataNascita;



    @NotEmpty(message = "Il luogo di Nascita è Obbligatorio")
    @NotBlank(message = "Il luogo di Nascita è Obbligatorio")
    private String luogoDiNascita;


    @Email(message = "Email formattata male")
    @NotEmpty(message = "Email è Obbligatoria")
    @NotBlank(message = "Email è Obbligatoria")
    private String email;

    @NotEmpty(message = "Password è Obbligatoria")
    @NotBlank(message = "Password è Obbligatoria")
    @Size(min = 8, message = "Passwod deve essere di Almeno 8 Caratteri")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "La password deve contenere almeno una lettera maiuscola, un numero e un carattere speciale"
    )
    private String password;


}
