package it.uniromatre.pinaback.opere;

import it.uniromatre.pinaback.artisti.ArtistaMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OperaMapper {


    private final ArtistaMapper artistaMapper;


    public Opera toNewOpera(OperaRequest req) {

        return Opera.builder()
                .title(req.getTitle())
                .anno(req.getAnno())
                .tecnica(req.getTecnica())
                .build();
    }

    public OperaFront toOperaFront(Opera req) {
        return OperaFront.builder()
                .id(req.getId())
                .title(req.getTitle())
                .img(req.getImmagine())
                .tecnica(req.getTecnica())
                .posizione(req.getPosizione())
                .artista(artistaMapper.toArtistFrontImg(req.getArtista()))
                .anno(req.getAnno())
                .build();
    }


    public List<OperaFront> toOperaFront(List<Opera> list) {
        return list.stream().map(this::toOperaFront)
                .collect(Collectors.toList());
    }

}
