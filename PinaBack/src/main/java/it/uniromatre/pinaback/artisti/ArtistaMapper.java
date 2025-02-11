package it.uniromatre.pinaback.artisti;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ArtistaMapper {

    public Artista toNewArtista(ArtistaRequest request) {
        return Artista.builder()
                .name(request.getName())
                .cognome(request.getCognome())
                .dataDiNascita(request.getDataDiNascita())
                .dataDiMorte(request.getDataDiMorte())
                .opere(new ArrayList<>())
                .build();
    }

}
