package it.uniromatre.pinaback.opere;

import it.uniromatre.pinaback.artisti.ArtistaFrontImg;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OperaFront {

    private Integer id;

    private String title;

    private Integer anno;

    private String tecnica;

    private Integer posizione;

    private ArtistaFrontImg artista;

    private String img;

}
