package com.springcloud.controllers;

import com.springcloud.models.Car;
import com.springcloud.models.Moto;
import com.springcloud.models.Users;
import com.springcloud.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Users>> users() {
        List<Users> users = userService.users();
        if (users.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> show(@PathVariable int id) {
        Users user = userService.show(id);
        if (user == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<Users> save(@RequestBody Users user) {
        Users newUser = userService.save(user);
        return ResponseEntity.ok(newUser);
    }

    // RestTemplate. Operaciones multitabla
    @GetMapping("/car/{userId}")
    public ResponseEntity<List<Car>> getCarsByUserId(@PathVariable long userId) {
        Users user = userService.show(userId);
        if (user == null)
            return ResponseEntity.notFound().build();
        List<Car> cars = userService.getCarsByUserId(userId);
        if (cars.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/moto/{userId}")
    public ResponseEntity<List<Moto>> getMotosByUserId(@PathVariable long userId) {
        Users user = userService.show(userId);
        if (user == null)
            return ResponseEntity.notFound().build();
        List<Moto> motos = userService.getMotosByUserId(userId);
        if (motos.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(motos);
    }

}
