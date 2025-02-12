package it.uniromatre.pinaback.controller;

import it.uniromatre.pinaback.area.AreaFront;
import it.uniromatre.pinaback.area.AreaService;
import it.uniromatre.pinaback.artisti.ArtistaCut;
import it.uniromatre.pinaback.artisti.ArtistaFront;
import it.uniromatre.pinaback.artisti.ArtistaFrontImg;
import it.uniromatre.pinaback.artisti.ArtistaService;
import it.uniromatre.pinaback.opere.OperaFront;
import it.uniromatre.pinaback.opere.OperaService;
import it.uniromatre.pinaback.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("frontEnd")
@AllArgsConstructor
public class FrontEndController {

    private final AreaService areaService;
    private final UserService userService;
    private final OperaService operaService;
    private final ArtistaService artistaService;


    @GetMapping("/getAree")
    public ResponseEntity<List<AreaFront>> getAree(){
        return ResponseEntity.ok(this.areaService.getAree());
    }

    @GetMapping("/isAdmin")
    public Boolean isAdmin(
            Authentication connectedUser
    ){
        return userService.isAdmin(connectedUser);
    }

    @GetMapping("/checkIfCurator")
    public ResponseEntity<Boolean> checkIfOwner(
            Authentication connectedUser
    ){
        return ResponseEntity.ok(this.userService.checkIfCurator(connectedUser));
    }

    @GetMapping("/getOpera")
    public ResponseEntity<OperaFront> getOpera(
            Authentication connectedUser,
            @RequestParam(name = "idOpera") Integer idOpera
    ){
        return ResponseEntity.ok(this.operaService.getOpera(connectedUser,idOpera));
    }

    @GetMapping("/getArtista")
    public ResponseEntity<ArtistaCut> getArtista(
            Authentication connectedUser,
            @RequestParam(name ="idArt") Integer idArt
    ){
        return ResponseEntity.ok(this.artistaService.getArtista(connectedUser, idArt));
    }


}
