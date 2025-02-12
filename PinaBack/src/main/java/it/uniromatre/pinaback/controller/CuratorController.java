package it.uniromatre.pinaback.controller;

import it.uniromatre.pinaback.user.UserResponse;
import it.uniromatre.pinaback.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("curator")
@AllArgsConstructor
public class CuratorController {


    private final UserService userService;


    // il curatore puo soltanto aggiungere opere alla sua area che gli è stata data da un admin



    // aggiungi opera in stanza

    // rimuovi opera in stanza



    @GetMapping("/getCuratorData")
    @PreAuthorize("hasAuthority('curator:read')")
    public ResponseEntity<UserResponse> getCuratorData(
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(this.userService.getCuratorData(connectedUser));
    }

    @GetMapping("/test")
    @PreAuthorize("hasAuthority('curator:read')")
    public ResponseEntity<?> testSicurezza(){
        return ResponseEntity.ok("Hai permessi user");
    }




}
