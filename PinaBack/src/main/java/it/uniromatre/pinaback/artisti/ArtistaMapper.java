package it.uniromatre.pinaback.artisti;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtistaMapper {

    public Artista toNewArtista(ArtistaRequest request) {
        return Artista.builder()
                .name(request.getName())
                .cognome(request.getCognome())
                .dataDiNascita(request.getDataDiNascita())
                .luogoDiNascita(request.getLuogoDiNascita())
                .dataDiMorte(request.getDataDiMorte())
                .opere(new ArrayList<>())
                .build();
    }


    public ArtistaFront toArtistFront(Artista art){
        return ArtistaFront.builder()
                .id(art.getId())
                .nome(art.getName())
                .cognome(art.getCognome())
                .build();
    }


    public List<ArtistaFront> toListArtistFront(List<Artista> arts){
        return arts.stream().map(this::toArtistFront)
                .collect(Collectors.toList());
    }


    public ArtistaFrontImg toArtistFrontImg(Artista art){
        return ArtistaFrontImg.builder()
                .id(art.getId())
                .nome(art.getName())
                .cognome(art.getCognome())
                .img(art.getImmagine())
                .build();
    }


    public List<ArtistaFrontImg> toListArtistFrontImg(List<Artista> arts) {
        return arts.stream().map(this::toArtistFrontImg)
                .collect(Collectors.toList());
    }
}
