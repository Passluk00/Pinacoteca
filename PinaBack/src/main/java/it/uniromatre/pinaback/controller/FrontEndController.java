package it.uniromatre.pinaback.controller;

import it.uniromatre.pinaback.area.AreaFront;
import it.uniromatre.pinaback.area.AreaService;
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


}
