package moto.service.controllers;

import moto.service.models.Moto;
import moto.service.services.MotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/moto")
public class MotoController {
    @Autowired
    private MotoService motoService;

    @GetMapping
    public ResponseEntity<List<Moto>> motos() {
        List<Moto> motos = motoService.motos();
        if (motos.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(motos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Moto> show(@PathVariable long id) {
        Moto moto = motoService.show(id);
        if (moto == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(moto);
    }

    @PostMapping
    public ResponseEntity<Moto> save(@RequestBody Moto moto) {
        Moto newMoto = motoService.save(moto);
        return ResponseEntity.ok(newMoto);
    }

    @GetMapping("user/{id}")
    public ResponseEntity<List<Moto>> getMotosByUserId(@PathVariable long id) {
        List<Moto> motos = motoService.getMotosByUserId(id);
        if (motos.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(motos);
    }

}
