package it.uniromatre.pinaback.controller;

import it.uniromatre.pinaback.area.AreaFront;
import it.uniromatre.pinaback.area.AreaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("frontEnd")
@AllArgsConstructor
public class FrontEndController {

    private final AreaService areaService;


    @GetMapping("/getAree")
    public ResponseEntity<List<AreaFront>> getAree(){
        return ResponseEntity.ok(this.areaService.getAree());
    }


}
