package co.edu.escuelaing.project.AppGusto.service;

import co.edu.escuelaing.project.AppGusto.repository.PedidoRepository;
import org.springframework.stereotype.Service;



@Service
public class PedidosService {
    private final PedidoRepository pedidoRepository;

    public PedidosService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }
    //Add
    //Get for ID
    //Get All
    //Update
    //Delete
    //fill
}