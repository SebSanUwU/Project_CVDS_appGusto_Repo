package co.edu.escuelaing.project.AppGusto.service;

import co.edu.escuelaing.project.AppGusto.model.Gerente;
import co.edu.escuelaing.project.AppGusto.model.Usuario;
import co.edu.escuelaing.project.AppGusto.repository.GerenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GerenteService {
    private final GerenteRepository gerenteRepository;

    @Autowired
    public  GerenteService(GerenteRepository gerenteRepository){
        this.gerenteRepository = gerenteRepository;
    }

    public Gerente addGerente(Gerente gerente){
        return gerenteRepository.save(gerente);
    }

    public Gerente getGerente(Long id){
        return gerenteRepository.findById(id).get();
    }

}
