package it.uniromatre.pinaback.opere;

import org.springframework.stereotype.Service;

@Service
public class OperaMapper {


    public Opera toNewOpera(OperaRequest req) {

        return Opera.builder()
                .title(req.getTitle())
                .anno(req.getAnno())
                .tecnica(req.getTecnica())
                .artista(req.getArtista())
                .build();
    }


}
