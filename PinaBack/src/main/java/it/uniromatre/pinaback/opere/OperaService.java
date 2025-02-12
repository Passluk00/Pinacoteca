package it.uniromatre.pinaback.opere;


import it.uniromatre.pinaback.artisti.Artista;
import it.uniromatre.pinaback.artisti.ArtistaRepository;
import it.uniromatre.pinaback.file.FileStorageService;
import it.uniromatre.pinaback.user.User;
import it.uniromatre.pinaback.user.UserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class OperaService {

    private static final Logger log = LoggerFactory.getLogger(OperaService.class);
    private final OperaRepository operaRepository;
    private final UserRepository userRepository;
    private final OperaMapper operaMapper;
    private final ArtistaRepository artistaRepository;
    private final FileStorageService fileStorageService;

    public void creaOpera(Authentication connectedUser, OperaRequest req, MultipartFile file, Integer idArt) {

        if(req == null){
            throw new RuntimeException("request is null");
        }

        User user = (User) connectedUser.getPrincipal();
        User toCheck = userRepository.findUserById(user.getId());
        if(toCheck == null){
            throw new RuntimeException("User not found");
        }

        Artista art = artistaRepository.findById(idArt).orElseThrow(() -> new RuntimeException("Artista non trovato"));

        Opera opera = operaMapper.toNewOpera(req);
        opera.setArtista(art);

        if(file != null){
            opera.setImmagine(fileStorageService.saveFile(file, toCheck.getId()));
        }
        operaRepository.save(opera);
        art.getOpere().add(opera);
        artistaRepository.save(art);
    }


    public void deleteOpera(Authentication connectedUser, Integer idOpera) {

        if(idOpera == null){
            throw new RuntimeException("idOpera is null");
        }

        User user = (User) connectedUser.getPrincipal();
        User toCheck = userRepository.findUserById(user.getId());
        if(toCheck == null){
            throw new RuntimeException("User not found");
        }

        Opera op = operaRepository.findById(idOpera).orElseThrow(() -> new RuntimeException("Opera not found"));

        Artista art = artistaRepository.findById(op.getArtista().getId()).orElseThrow(() -> new RuntimeException("Artista not found"));

        List<Opera> list = art.getOpere();

        list.removeIf( ope ->Objects.equals(ope.getId(), op.getId()));

        art.setOpere(list);
        artistaRepository.save(art);
        operaRepository.delete(op);


    }

    public void modOpera(Authentication connectedUser, Opera req, Integer idOpera, Integer idArt) {

        if(idOpera == null || idOpera == 0 || req == null){
            throw new RuntimeException("idOpera or request is null");
        }

        User user = (User) connectedUser.getPrincipal();
        User toCheck = userRepository.findUserById(user.getId());
        if(toCheck == null){
            throw new RuntimeException("User not found");
        }

        Opera op = operaRepository.findById(idOpera).orElseThrow(() -> new RuntimeException("Opera not found"));

        op.setTitle(req.getTitle());
        op.setAnno(req.getAnno());
        op.setTecnica(req.getTecnica());
        operaRepository.save(op);

        changeOwnerToOpera(connectedUser,idOpera,idArt);


    }

    public void changeOwnerToOpera(Authentication connectedUser, Integer idOpera, Integer idArtista) {

        if(idOpera == null || idOpera == 0 || idArtista == null || idArtista == 0){
            throw new RuntimeException("idOpera or idArtist is null");
        }

        User user = (User) connectedUser.getPrincipal();
        User toCheck = userRepository.findUserById(user.getId());
        if(toCheck == null){
            throw new RuntimeException("User not found");
        }

        Opera op = operaRepository.findById(idOpera).orElseThrow(() -> new RuntimeException("Opera not found"));
        Artista art = artistaRepository.findById(idArtista).orElseThrow(() -> new RuntimeException("Artista not found"));
        op.setArtista(art);
        operaRepository.save(op);

    }

    public List<OperaFront> getAllOpereForAdmin(Authentication connectedUser) {

        User user = (User) connectedUser.getPrincipal();
        User toCheck = userRepository.findUserById(user.getId());
        if (toCheck == null) {
            throw new RuntimeException("User not found");
        }

        List<Opera> list = operaRepository.getAll();
        return operaMapper.toOperaFront(list);
    }

    public OperaFront getOperaToMod(Authentication connectedUser, Integer idOpera) {

        User user = (User) connectedUser.getPrincipal();
        User toCheck = userRepository.findUserById(user.getId());
        if (toCheck == null) {
            throw new RuntimeException("User not found");
        }

        Opera op = this.operaRepository.findById(idOpera).orElseThrow(() -> new RuntimeException("opera non trovata"));

        return operaMapper.toOperaFront(op);
    }

    public List<OperaFront> getAllFree() {

        List<Opera> opere = operaRepository.getAllByPosizione();

        return operaMapper.toOperaFront(opere);

    }

    public OperaFront getOpera(Authentication connectedUser, Integer idOpera) {

        User user = (User) connectedUser.getPrincipal();
        User toCheck = userRepository.findUserById(user.getId());
        if (toCheck == null) {
            throw new RuntimeException("User not found");
        }

        Opera op = operaRepository.findById(idOpera).orElseThrow(() -> new RuntimeException("opera not found"));
        return operaMapper.toOperaFront(op);

    }
}
