package it.uniromatre.pinaback.artisti;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class ArtistaRequest {


    @NotEmpty(message = "il Nome Dell Artista non Puo' essere Vuoto")
    @NotBlank(message = "il Nome Dell Artista non Puo' essere Vuoto")
    private String name;

    @NotEmpty(message = "il Cognome Dell Artista non Puo' essere Vuoto")
    @NotBlank(message = "il Cognome Dell Artista non Puo' essere Vuoto")
    private String cognome;

    @NotEmpty(message = "La Data di Nascita Dell Artista non Puo' essere Vuota")
    @NotBlank(message = "La Data di Nascita Dell Artista non Puo' essere Vuota")
    private LocalDate dataDiNascita;

    @NotEmpty(message = "Il Luogo di Nascita Dell Artista non Puo' essere Vuota")
    @NotBlank(message = "Il Luogo di Nascita Dell Artista non Puo' essere Vuota")
    private String luogoDiNascita;


    private LocalDate dataDiMorte;





}
