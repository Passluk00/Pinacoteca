package it.uniromatre.pinaback.artisti;


import it.uniromatre.pinaback.opere.Opera;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class ArtistaCut {

    private Integer id;

    private String name;

    private String cognome;

    private LocalDate dataDiNascita;

    private String luogoDiNascita;

    private LocalDate dataDiMorte;

    private String immagine;

}
