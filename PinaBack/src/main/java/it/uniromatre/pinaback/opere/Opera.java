package it.uniromatre.pinaback.opere;

import it.uniromatre.pinaback.artisti.Artista;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@Entity
@Table(name = "opera")
@AllArgsConstructor
@NoArgsConstructor
public class Opera {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;

    private Integer anno;

    private String tecnica;

    private Integer posizione;

    @ManyToOne
    private Artista artista;

    private String immagine;

}
