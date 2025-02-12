package it.uniromatre.pinaback.area;

import it.uniromatre.pinaback.opere.Opera;
import it.uniromatre.pinaback.opere.OperaMapper;
import it.uniromatre.pinaback.opere.OperaRepository;
import it.uniromatre.pinaback.user.User;
import it.uniromatre.pinaback.user.UserMapper;
import it.uniromatre.pinaback.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AreaService {


    private final UserRepository userRepository;
    private final AreaMapper areaMapper;
    private final AreaRepository areaRepository;
    private final OperaRepository operaRepository;
    private final OperaMapper operaMapper;
    private final UserMapper userMapper;


    public void creaArea(Authentication connectedUser, AreaRequest req, Integer idCur){

        if(req == null){
            throw new RuntimeException("request is null");
        }

        User user = (User) connectedUser.getPrincipal();
        User toCheck = userRepository.findUserById(user.getId());
        if(toCheck == null){
            throw new RuntimeException("User not found");
        }

        User us = userRepository.findUserById(idCur);
        if(us == null){
            throw new RuntimeException("user non trovato");
        }

        Area nuova = Area.builder()
                .name(req.getName())
                .curatore(us)
                .build();
        areaRepository.save(nuova);

        us.setArea(nuova);
        userRepository.save(us);
    }


    public void assegnaAreaACuratore(Authentication connectedUser, Integer idArea, Integer idCur) {

        if(idArea == null || idCur == null){
            throw new RuntimeException("request is not valid");
        }

        User user = (User) connectedUser.getPrincipal();
        User toCheck = userRepository.findUserById(user.getId());
        if(toCheck == null){
            throw new RuntimeException("User not found");
        }

        Area area = areaRepository.findById(idArea).orElseThrow(()-> new RuntimeException("Area not found"));
        if(area.getCuratore() != null){
            throw new RuntimeException("Questa area ha gia un curatore");
        }

        User cur = userRepository.findUserById(idCur);
        if(cur == null){
            throw new RuntimeException("User not found");
        }
        area.setCuratore(cur);
        cur.setArea(area);
        areaRepository.save(area);
        userRepository.save(cur);

    }


    public void removeAreaFromCurator(Authentication connectedUser, Integer idCur) {

        if(idCur == null){
            throw new RuntimeException("request is not valid");
        }

        User user = (User) connectedUser.getPrincipal();
        User toCheck = userRepository.findUserById(user.getId());
        if(toCheck == null){
            throw new RuntimeException("User not found");
        }

        User cur = userRepository.findUserById(idCur);
        if(cur == null){
            throw new RuntimeException("User not found");
        }

        Area area = areaRepository.findById(cur.getArea().getId()).orElseThrow(()-> new RuntimeException("Area not found"));

        area.setCuratore(null);
        cur.setArea(null);
        areaRepository.save(area);
        userRepository.save(cur);



    }

    @Transactional
    public void delArea(Authentication connectedUser, Integer idArea) {

        if(idArea == null){
            throw new RuntimeException("request is not valid");
        }

        User user = (User) connectedUser.getPrincipal();
        User toCheck = userRepository.findUserById(user.getId());
        if(toCheck == null){
            throw new RuntimeException("User not found");
        }

        Area area = areaRepository.findById(idArea).orElseThrow(()-> new RuntimeException("Area not found"));

        List<Opera> lista = area.getOpere();

        for(Opera opera : lista){
            opera.setPosizione(null);
        }
        operaRepository.saveAll(lista);

        if(area.getCuratore() != null) {
            User cur = area.getCuratore();
            cur.setArea(null);
            userRepository.save(cur);
        }
        area.setCuratore(null);
        areaRepository.delete(area);

    }


    public void changeCuratorToArea(Authentication connectedUser, Integer idArea, Integer idCur) {

        if(idArea == null || idCur == null || idCur == 0 || idArea == 0){
            throw new RuntimeException("request is not valid");
        }

        User user = (User) connectedUser.getPrincipal();
        User toCheck = userRepository.findUserById(user.getId());
        if(toCheck == null){
            throw new RuntimeException("User not found");
        }

        Area area = areaRepository.findById(idArea).orElseThrow(()-> new RuntimeException("Area not found"));
        User cur = userRepository.findUserById(idCur);

        if(cur == null){
            throw new RuntimeException("User not found");
        }

        if(area.getCuratore() != null){
            User del = area.getCuratore();
            del.setArea(null);
            userRepository.save(del);
        }

        area.setCuratore(cur);
        cur.setArea(area);
        areaRepository.save(area);
        userRepository.save(cur);

    }

    public List<AreaFront> getAree() {

        List<Area> aree = areaRepository.findAll();

        return aree.stream().map(area -> AreaFront.builder()
                        .id(area.getId())
                        .name(area.getName())
                        .curatore(userMapper.toUserFront(area.getCuratore()))
                        .items(operaMapper.toOperaFront(area.getOpere()))
                        .build())
                .collect(Collectors.toList());


    }

    public void addOperaAdArea(Authentication connectedUser, Integer idArea, Integer idOpera) {

        if(idOpera == 0 || idArea == 0){
            throw new RuntimeException("request is not valid");
        }

        User user = (User) connectedUser.getPrincipal();
        User toCheck = userRepository.findUserById(user.getId());
        if(toCheck == null){
            throw new RuntimeException("User not found");
        }

        Opera opera = operaRepository.findById(idOpera).orElseThrow(() -> new RuntimeException("opera non trovata"));

        Area area = areaRepository.findById(idArea).orElseThrow(() -> new RuntimeException("area non trovata"));

        opera.setPosizione(area.getId());
        operaRepository.save(opera);

        area.getOpere().add(opera);
        areaRepository.save(area);

    }
}
