package com.springcloud.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springcloud.models.Moto;

@FeignClient(name = "moto-service", url = "http://localhost:8082")
@RequestMapping("/moto")
public interface MotoFeignClient {
	
	@PostMapping
	public Moto save(@RequestBody Moto moto);
	
	@GetMapping("/user/{userId}")
	public List<Moto> getMotos(@PathVariable long userId);

}
