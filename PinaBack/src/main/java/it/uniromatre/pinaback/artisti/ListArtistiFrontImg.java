package it.uniromatre.pinaback.artisti;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ListArtistiFrontImg {

    private List<ArtistaFrontImg> items;

}
