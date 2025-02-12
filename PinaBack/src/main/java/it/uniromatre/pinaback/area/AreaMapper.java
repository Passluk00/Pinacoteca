package it.uniromatre.pinaback.area;

import it.uniromatre.pinaback.opere.OperaMapper;
import it.uniromatre.pinaback.user.User;
import it.uniromatre.pinaback.user.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class AreaMapper {

    private final UserMapper userMapper;
    private final OperaMapper operaMapper;




    public Area toNewArea(AreaRequest req, User us){
        return Area.builder()
                .name(req.getName())
                .curatore(us)
                .opere(new ArrayList<>())
                .build();
    }



    public AreaFront toAreaFront(Area area){
        return AreaFront.builder()
                .id(area.getId())
                .name(area.getName())
                .curatore(userMapper.toUserFront(area.getCuratore()))
                .items(operaMapper.toOperaFront(area.getOpere()))
                .build();
    }

}
