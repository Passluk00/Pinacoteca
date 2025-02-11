package it.uniromatre.pinaback.area;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AreaMapper {

    public Area toNewArea(AreaRequest req){
        return Area.builder()
                .name(req.getName())
                .curatore(req.getCuratore())
                .opere(new ArrayList<>())
                .build();
    }

}
