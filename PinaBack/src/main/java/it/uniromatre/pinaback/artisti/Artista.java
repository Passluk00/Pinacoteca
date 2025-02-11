package it.uniromatre.pinaback.artisti;

import it.uniromatre.pinaback.opere.Opera;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@Table(name = "artisti")
@NoArgsConstructor
@AllArgsConstructor
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String cognome;

    private LocalDate dataDiNascita;

    private String luogoDiNascita;

    private LocalDate dataDiMorte;

    @OneToMany
    private List<Opera> opere;

    private String immagine;

}
