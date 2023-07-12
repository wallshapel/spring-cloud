package moto.service.services;

import moto.service.models.Moto;
import moto.service.repositories.MotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoService {
    @Autowired
    private MotoRepository motoRepository;

    public List<Moto> motos() {
        return motoRepository.findAll();
    }

    public Moto show(long id) {
        return motoRepository.findById(id).orElse(null);
    }

    public Moto save(Moto moto) {
        Moto newMoto = motoRepository.save(moto);
        return newMoto;
    }

    public List<Moto> getMotosByUserId(long id) {
        return motoRepository.findByUserId(id);
    }
}