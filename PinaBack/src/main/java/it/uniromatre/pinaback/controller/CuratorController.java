package it.uniromatre.pinaback.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("curator")
@PreAuthorize("hasRole('CURATOR')")
@AllArgsConstructor
public class CuratorController {

    // il curatore puo soltanto aggiungere opere alla sua area che gli è stata data da un admin



    // aggiungi opera in stanza

    // rimuovi opera in stanza


}
