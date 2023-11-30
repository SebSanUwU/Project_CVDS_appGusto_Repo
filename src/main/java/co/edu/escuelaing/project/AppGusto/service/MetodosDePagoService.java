package co.edu.escuelaing.project.AppGusto.service;

import co.edu.escuelaing.project.AppGusto.model.MetodoDePago;
import co.edu.escuelaing.project.AppGusto.model.Pedido;
import co.edu.escuelaing.project.AppGusto.repository.MetodosDePagoRepository;
import co.edu.escuelaing.project.AppGusto.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetodosDePagoService {
    private final MetodosDePagoRepository metodoDePagoRepository;
    @Autowired
    public MetodosDePagoService(MetodosDePagoRepository metodoDePagoRepository) {
        this.metodoDePagoRepository = metodoDePagoRepository;
    }

    //Add
    public MetodoDePago addMetodoDePago(MetodoDePago metodoDePago){
        return metodoDePagoRepository.save(metodoDePago);
    }
    //Get for ID
    public Pedido getPedido(Long pedidoID){
        return pedidoRepository.getReferenceById(pedidoID);
    }
    //Get All
    public List<MetodoDePago> getAllMetodosDePagos(){
        return metodoDePagoRepository.findAll();
    }
    //Update
    public MetodoDePago updateMetodoDePago(MetodoDePago metodoDePago){
        return metodoDePagoRepository.save(metodoDePago);
    }
    //Delete
    public void deleteMetodoDePago(Long metodoDePagoId) {
        metodoDePagoRepository.deleteById(Long.valueOf(metodoDePagoId));
    }
    //fill
}