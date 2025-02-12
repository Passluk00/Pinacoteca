package it.uniromatre.pinaback.artisti;

import it.uniromatre.pinaback.file.FileStorageService;
import it.uniromatre.pinaback.user.User;
import it.uniromatre.pinaback.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@AllArgsConstructor
public class ArtistaService {


    private final UserRepository userRepository;
    private final ArtistaRepository artistaRepository;
    private final ArtistaMapper artistaMapper;
    private final FileStorageService fileStorageService;


    public void creaArtista(Authentication connectedUser, ArtistaRequest req, MultipartFile file) {

        if(req == null){
            throw new RuntimeException("request is null");
        }

        User user = (User) connectedUser.getPrincipal();
        User toCheck = userRepository.findUserById(user.getId());
        if(toCheck == null){
            throw new RuntimeException("User not found");
        }

        Artista art =artistaMapper.toNewArtista(req);
        if(file != null) {
            art.setImmagine(fileStorageService.saveFile(file, toCheck.getId()));
        }
        artistaRepository.save(art);
    }

    public void deleteArtista(Authentication connectedUser, Integer idArt) {

        if(idArt == null){
            throw new RuntimeException("request is null");
        }

        User user = (User) connectedUser.getPrincipal();
        User toCheck = userRepository.findUserById(user.getId());
        if(toCheck == null){
            throw new RuntimeException("User not found");
        }

        Artista art = artistaRepository.findById(idArt).orElseThrow(() -> new RuntimeException("Artista not found"));
        artistaRepository.delete(art);

    }

    public void modArtista(Authentication connectedUser, Integer idArt, Artista req) {

        if(idArt == null || idArt == 0 || req == null){
            throw new RuntimeException("request is invalid");
        }

        User user = (User) connectedUser.getPrincipal();
        User toCheck = userRepository.findUserById(user.getId());
        if(toCheck == null){
            throw new RuntimeException("User not found");
        }

        Artista art= artistaRepository.findById(idArt).orElseThrow(() -> new RuntimeException("Artista not found"));

        art.setName(req.getName());
        art.setCognome(req.getCognome());
        art.setDataDiNascita(req.getDataDiNascita());
        art.setLuogoDiNascita(req.getLuogoDiNascita());
        art.setDataDiMorte(req.getDataDiMorte());
        artistaRepository.save(art);
    }


    public ListArtist getAllArtistFotMenu(Authentication connectedUser) {

        User user = (User) connectedUser.getPrincipal();
        User toCheck = userRepository.findUserById(user.getId());
        if(toCheck == null){
            throw new RuntimeException("User not found");
        }

        List<Artista> lista = artistaRepository.getAll();
        List<ArtistaFront> arts = artistaMapper.toListArtistFront(lista);

        return new ListArtist(arts);


    }

    public List<ArtistaFrontImg> getAllArtistForAdmin(Authentication connectedUser) {

        User user = (User) connectedUser.getPrincipal();
        User toCheck = userRepository.findUserById(user.getId());
        if(toCheck == null){
            throw new RuntimeException("User not found");
        }

        List<Artista> lista = artistaRepository.getAllOrdered();
        List<ArtistaFrontImg> arts = artistaMapper.toListArtistFrontImg(lista);

        return arts;
    }

    public Artista getArtistForMod(Authentication connectedUser, Integer idArtista) {

        User user = (User) connectedUser.getPrincipal();
        User toCheck = userRepository.findUserById(user.getId());
        if(toCheck == null){
            throw new RuntimeException("User not found");
        }

        if(idArtista == null) {
            throw new RuntimeException("id Artista is null");
        }

        Artista art = artistaRepository.findById(idArtista).orElseThrow(() -> new RuntimeException("artista non trovato"));

        return art;
    }
}
