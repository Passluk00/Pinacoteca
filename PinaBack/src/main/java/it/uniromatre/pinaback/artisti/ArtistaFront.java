package it.uniromatre.pinaback.artisti;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ArtistaFront {

    private Integer id;

    private String nome;

    private String cognome;

}
