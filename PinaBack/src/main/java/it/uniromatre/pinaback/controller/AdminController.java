package it.uniromatre.pinaback.controller;

import it.uniromatre.pinaback.area.AreaRequest;
import it.uniromatre.pinaback.area.AreaService;
import it.uniromatre.pinaback.artisti.*;
import it.uniromatre.pinaback.opere.*;
import it.uniromatre.pinaback.user.UserFront;
import it.uniromatre.pinaback.user.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("admin")
@PreAuthorize("hasRole('ADMIN')")
@AllArgsConstructor
public class AdminController {

    private final UserService userService;
    private final OperaService operaService;
    private final ArtistaService artistaService;
    private final AreaService areaService;



    @GetMapping("/testController")
    public ResponseEntity<?> testController() {
        return ResponseEntity.ok().build();
    }


    /*

            AREA

     */



    // crea Area
    @PostMapping("/creaArea")
    public ResponseEntity<?> creaArea(
            @RequestParam(name = "idCur") Integer idcur,
            @RequestBody AreaRequest req,
            Authentication connectedUser
    ){
        areaService.creaArea(connectedUser, req, idcur);
        return ResponseEntity.accepted().build();
    }


    // Del Area

    @DeleteMapping("/delArea")
    public ResponseEntity<?> delArea(
            @RequestParam(name = "idArea") Integer idArea,
            Authentication connectedUser
    ){
        this.areaService.delArea(connectedUser,idArea);
        return ResponseEntity.accepted().build();
    }


    // cambia Curatore ad un area

    @PatchMapping("/changeCurtorTOArea")
    public ResponseEntity<?> changeCurtorTOArea(
            @RequestParam(name = "idArea") Integer idArea,
            @RequestParam(name = "idcur") Integer idCur,
            Authentication connectedUser
            ){
        this.areaService.changeCuratorToArea(connectedUser, idArea, idCur);
        return ResponseEntity.accepted().build();
    }


    // aggiungi opera ad area

    @PostMapping("/addOperaAdArea")
    public ResponseEntity<?> addOperaAdarea(
            @RequestParam(name = "idOpera") Integer idOpera,
            @RequestParam(name = "idArea") Integer idArea,
            Authentication connectedUser
            ){
        this.areaService.addOperaAdArea(connectedUser, idArea,idOpera);
        return ResponseEntity.accepted().build();
    }





    /*

            ARTISTA

     */




    // CreaArtista   TODO implementare gestione file

    @PostMapping(value="/creaArtista", consumes = "multipart/form-data")
    public ResponseEntity<?> creaArtista(
            @RequestPart(name = "file",required = false) MultipartFile file,
            @RequestPart(name = "req") ArtistaRequest req,
            Authentication connectedUser
    ){
        this.artistaService.creaArtista(connectedUser, req, file);
        return ResponseEntity.accepted().build();
    }



    // Del Artista

    @DeleteMapping("/deleteArtista")
    public ResponseEntity<?> deleteArtista(
            @RequestParam(name = "idArt") Integer idArt,
            Authentication connectedUser
    ){
        this.artistaService.deleteArtista(connectedUser, idArt);
        return ResponseEntity.accepted().build();
    }


    // mod Artista    TODO implementare gestione file

    @PatchMapping("/modArtista")
    public ResponseEntity<?> modArtista(
            @RequestParam(name = "idArt") Integer idArt,
            @RequestBody Artista req,
            Authentication connectedUser
    ){
        this.artistaService.modArtista(connectedUser,idArt, req);
        return ResponseEntity.accepted().build();
    }


    // get all artisti utilizati per menu a tendina ce soltanto id e nome , cognome

    @GetMapping("/getAllArtistForMenu")
    public ResponseEntity<ListArtist> getAllArtistForMenu(
            Authentication connectedUser
    ){
        return ResponseEntity.ok(this.artistaService.getAllArtistFotMenu(connectedUser));
    }


    // get all Artist for display in admin panel
    @GetMapping("/getAllArtistForDisplay")
    public ResponseEntity<List<ArtistaFrontImg>> getAllArtistForDisplay(
            Authentication connectedUser
    ){
        return ResponseEntity.ok(this.artistaService.getAllArtistForAdmin(connectedUser));
    }


    @GetMapping("/getArtistaForMod")
    public ResponseEntity<Artista> getArtistaDaMod(
            @RequestParam(name = "idArt") Integer idArtista,
            Authentication connectedUser
    ){
        return ResponseEntity.ok(this.artistaService.getArtistForMod(connectedUser, idArtista));
    }




    /*

            OPERE

     ***/



    // crea opera    TODO implementare gestione file

    @PostMapping(value = "/creaOpera", consumes = "multipart/form-data")
    public ResponseEntity<?> creaOpera(
            @RequestParam(name = "idArtista") Integer idArtista,
            @RequestPart(name = "file") MultipartFile file,
            @RequestPart OperaRequest req,
            Authentication connectedUser
    ){
        this.operaService.creaOpera(connectedUser, req, file, idArtista);
        return ResponseEntity.accepted().build();
    }



    // del opera
    @DeleteMapping("/deleteOpera")
    public ResponseEntity<?> deleteOpera(
            @RequestParam(name = "idOpera") Integer idOpera,
            Authentication connectedUser
    ){
        operaService.deleteOpera(connectedUser, idOpera);
        return ResponseEntity.accepted().build();
    }




    // mod opera    TODO implementare gestione file

    @PatchMapping("/modOpera")
    public ResponseEntity<?> modOpera(
            @RequestParam(name = "idOpera") Integer idOpe,
            @RequestParam(name = "idArt") Integer idArt,
            @RequestBody Opera req,
            Authentication connectedUser
    ){
        this.operaService.modOpera(connectedUser, req, idOpe, idArt);
        return ResponseEntity.accepted().build();
    }



    // Cambia propietario ad un opera

    @PatchMapping("/changeOwnerToOpera")
    public ResponseEntity<?> changeOwnerToOpera(
            @RequestParam(name = "idOpera") Integer idOpera,
            @RequestParam(name = "idArtista") Integer idArtista,
            Authentication connectedUser
            ){
        this.operaService.changeOwnerToOpera(connectedUser, idOpera, idArtista);
        return ResponseEntity.accepted().build();
    }


    // get all Artist for display in admin panel
    @GetMapping("/getAllOpereForDisplay")
    public ResponseEntity<List<OperaFront>> getAllOpereForDisplay(
            Authentication connectedUser
    ){
        return ResponseEntity.ok(this.operaService.getAllOpereForAdmin(connectedUser));
    }


    @GetMapping("/getOperaDaMod")
    public ResponseEntity<OperaFront> getOperaDaMod(
            Authentication connectedUser,
            @RequestParam(name ="idOpera") Integer idOpera
    ){
        return ResponseEntity.ok(this.operaService.getOperaToMod(connectedUser, idOpera));
    }



    @GetMapping("/getAllOpereLibere")
    public ResponseEntity<List<OperaFront>> getAllFree(
    ){
        return ResponseEntity.ok(this.operaService.getAllFree());
    }


    @GetMapping("/getCuraDisponibili")
    public ResponseEntity<List<UserFront>> getAllCuraFree(){
        return ResponseEntity.ok(this.userService.getAllFree());
    }













    /*

            GESTIONE UTENTI

     */




    // dai area ad un curatore

    @PostMapping("/assegnaAreaACuratore")
    public ResponseEntity<?> assegnaAreaACuratore(
            @RequestParam(name = "idArea") Integer idArea,
            @RequestParam(name = "idCur") Integer idCur,
            Authentication connectedUser
    ){
        this.areaService.assegnaAreaACuratore(connectedUser, idArea, idCur);
        return ResponseEntity.accepted().build();
    }


    // rimuovi area ad un curatore

    @DeleteMapping("/removeAreaFromCurator")
    public ResponseEntity<?> removeAreaToCurator(
            @RequestParam(name = "idCur") Integer idCur,
            Authentication connectedUser
    ){
        this.areaService.removeAreaFromCurator(connectedUser,idCur);
        return ResponseEntity.accepted().build();
    }
















}
