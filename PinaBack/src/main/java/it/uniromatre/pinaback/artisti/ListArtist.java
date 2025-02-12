package it.uniromatre.pinaback.artisti;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class ListArtist {

    private List<ArtistaFront> lista;

}
