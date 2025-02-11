package it.uniromatre.pinaback.controller;

import it.uniromatre.pinaback.area.AreaRequest;
import it.uniromatre.pinaback.area.AreaService;
import it.uniromatre.pinaback.artisti.ArtistaRequest;
import it.uniromatre.pinaback.artisti.ArtistaService;
import it.uniromatre.pinaback.opere.OperaRequest;
import it.uniromatre.pinaback.opere.OperaService;
import it.uniromatre.pinaback.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("admin")
@PreAuthorize("hasRole('ADMIN')")
@AllArgsConstructor
public class AdminController {

    private final UserService userService;
    private final OperaService operaService;
    private final ArtistaService artistaService;
    private final AreaService areaService;



    /*

            AREA

     */



    // crea Area
    @PostMapping("/creaArea")
    public ResponseEntity<?> creaArea(
            @RequestBody AreaRequest req,
            Authentication connectedUser
    ){
        areaService.creaArea(connectedUser, req);
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





    /*

            ARTISTA

     */




    // CreaArtista   TODO implementare gestione file

    @PostMapping(value="/creaArtista", consumes = "multipart/form-data")
    public ResponseEntity<?> creaArtista(
            @RequestPart(name = "file") MultipartFile file,
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

    @PatchMapping(value="/modArtista", consumes = "multipart/form-data")
    public ResponseEntity<?> modArtista(
            @RequestParam(name = "idArt") Integer idArt,
            @RequestPart(name = "file") MultipartFile file,
            @RequestPart ArtistaRequest req,
            Authentication connectedUser
    ){
        this.artistaService.modArtista(connectedUser,idArt, req, file);
        return ResponseEntity.accepted().build();
    }










    /*

            OPERE

     ***/



        // crea opera    TODO implementare gestione file

        @PostMapping(value = "/creaOpera", consumes = "multipart/form-data")
        public ResponseEntity<?> creaOpera(
                @RequestPart(name = "file") MultipartFile file,
                @RequestPart OperaRequest req,
                Authentication connectedUser
        ){
            this.operaService.creaOpera(connectedUser, req, file);
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

        @PatchMapping(value = "/modOpera", consumes = "multipart/form-data")
        public ResponseEntity<?> modOpera(
                @RequestParam(name = "idOpera") Integer idOpe,
                @RequestPart(name = "file") MultipartFile file,
                @RequestPart OperaRequest req,
                Authentication connectedUser
        ){
            this.operaService.modOpera(connectedUser, req, idOpe, file);
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


        // rimuovi posizione ad un opera











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
