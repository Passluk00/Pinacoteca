package it.uniromatre.pinaback.area;

import it.uniromatre.pinaback.opere.Opera;
import it.uniromatre.pinaback.opere.OperaRepository;
import it.uniromatre.pinaback.user.User;
import it.uniromatre.pinaback.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AreaService {


    private final UserRepository userRepository;
    private final AreaMapper areaMapper;
    private final AreaRepository areaRepository;
    private final OperaRepository operaRepository;


    public void creaArea(Authentication connectedUser, AreaRequest req){

        if(req == null){
            throw new RuntimeException("request is null");
        }

        User user = (User) connectedUser.getPrincipal();
        User toCheck = userRepository.findUserById(user.getId());
        if(toCheck == null){
            throw new RuntimeException("User not found");
        }

        Area nuova = areaMapper.toNewArea(req);
        areaRepository.save(nuova);

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
}
