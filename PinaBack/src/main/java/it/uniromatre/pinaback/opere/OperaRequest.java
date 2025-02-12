package it.uniromatre.pinaback.opere;

import it.uniromatre.pinaback.artisti.Artista;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class OperaRequest {


    @NotEmpty(message = "Il Titolo è Obbligatorio")
    @NotBlank(message = "Il Titolo è Obbligatorio")
    private String title;

    @NotEmpty(message = "L'anno è Obbligatorio")
    @NotBlank(message = "L'anno è Obbligatorio")
    private Integer anno;

    @NotEmpty(message = "La Tecnica é Obbligatoria")
    @NotBlank(message = "La Tecnica é Obbligatoria")
    private String tecnica;

}
