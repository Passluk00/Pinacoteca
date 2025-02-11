package it.uniromatre.pinaback.opere;


import it.uniromatre.pinaback.artisti.Artista;
import it.uniromatre.pinaback.artisti.ArtistaRepository;
import it.uniromatre.pinaback.file.FileStorageService;
import it.uniromatre.pinaback.user.User;
import it.uniromatre.pinaback.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class OperaService {

    private final OperaRepository operaRepository;
    private final UserRepository userRepository;
    private final OperaMapper operaMapper;
    private final ArtistaRepository artistaRepository;
    private final FileStorageService fileStorageService;

    public void creaOpera(Authentication connectedUser, OperaRequest req, MultipartFile file) {

        if(req == null){
            throw new RuntimeException("request is null");
        }

        User user = (User) connectedUser.getPrincipal();
        User toCheck = userRepository.findUserById(user.getId());
        if(toCheck == null){
            throw new RuntimeException("User not found");
        }

        Opera opera = operaMapper.toNewOpera(req);
        if(file != null){
            opera.setImmagine(fileStorageService.saveFile(file, toCheck.getId()));
        }
        operaRepository.save(opera);
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

    public void modOpera(Authentication connectedUser, OperaRequest req, Integer idOpera, MultipartFile file) {

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
        if(file != null){
            op.setImmagine(fileStorageService.saveFile(file, toCheck.getId()));
        }
        operaRepository.save(op);

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
}
