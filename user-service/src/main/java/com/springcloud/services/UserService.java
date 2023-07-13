package com.springcloud.services;

import com.springcloud.feignclients.CarFeignClient;
import com.springcloud.feignclients.MotoFeignClient;
import com.springcloud.models.Car;
import com.springcloud.models.Moto;
import com.springcloud.models.Users;
import com.springcloud.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CarFeignClient carFeignClient;
    
    @Autowired
    private MotoFeignClient motoFeignClient;

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
    
    // FeignClient
    public Car saveCar(long userId, Car car) {
    	car.setUserId(userId);
    	Car newCar = carFeignClient.save(car);
    	return newCar;
    }
    public Moto saveMoto(long userId, Moto moto) {
    	moto.setUserId(userId);
    	Moto newMoto = motoFeignClient.save(moto);
    	return newMoto;
    }
    public Map<String, Object> getUserAndVehicles(long userId) { // Devuelve un json con los datos del usuario y sus carros y motos
    	Map<String, Object> res = new HashMap<>();
    	Users user = userRepository.findById(userId).orElse(null);
    	if (user == null )
    		return res; 
    	res.put("User", user);
    	List<Car> cars = carFeignClient.getCars(userId);
    	if (cars.isEmpty())
    		res.put("Cars", "This user has not any cars");
    	else
    		res.put("Cars", cars);
    	
    	List<Moto> motos = motoFeignClient.getMotos(userId);
    	if (motos.isEmpty())
    		res.put("Motos", "This user has not any motos");
    	else
    		res.put("Motos", motos);
    	return res;
    	
    }
}
