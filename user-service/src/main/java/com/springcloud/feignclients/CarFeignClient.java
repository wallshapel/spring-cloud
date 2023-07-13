package com.springcloud.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springcloud.models.Car;

@FeignClient(name = "car-service", url = "http://localhost:8081")
@RequestMapping("/car")
public interface CarFeignClient {
	
	@PostMapping
	public Car save(@RequestBody Car car);
	
	@GetMapping("/user/{userId}")
	public List<Car> getCars(@PathVariable long userId);

}