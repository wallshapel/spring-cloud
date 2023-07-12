package com.springcloud.services;

import com.springcloud.models.Car;
import com.springcloud.models.Moto;
import com.springcloud.models.Users;
import com.springcloud.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    public List<Users> users() {
        return userRepository.findAll();
    }

    public Users show(long id) {
        return userRepository.findById(id).orElse(null);
    }

    public Users save(Users user) {
        Users newUser = userRepository.save(user);
        return newUser;
    }

    // RestTemplate. Métodos multitabla
    public List<Car> getCarsByUserId(long userId) {
        List<Car> cars = restTemplate.getForObject("http://localhost:8081/car/user/" + userId, List.class);
        return cars;
    }
    public List<Moto> getMotosByUserId(long userId) {
        List<Moto> motos = restTemplate.getForObject("http://localhost:8082/moto/user/" + userId, List.class);
        return motos;
    }
}
