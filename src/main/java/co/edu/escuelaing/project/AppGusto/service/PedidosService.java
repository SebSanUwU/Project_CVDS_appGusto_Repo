package co.edu.escuelaing.project.AppGusto.service;

import co.edu.escuelaing.project.AppGusto.model.Administrador;
import co.edu.escuelaing.project.AppGusto.model.Pedido;
import co.edu.escuelaing.project.AppGusto.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PedidosService {
    private final PedidoRepository pedidoRepository;
    @Autowired
    public PedidosService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }
    //Add
    public Pedido addPedido(Pedido pedido){
        return pedidoRepository.save(pedido);
    }
    //Get for ID
    public Pedido getPedido(String pedidoID){
        return pedidoRepository.getReferenceById(Long.valueOf(pedidoID));
    }
    //Get All
    public List<Pedido> getAllPedidos(){
        return pedidoRepository.findAll();
    }
    //Update
    public Pedido updateAdministrador(Pedido pedido){
        return pedidoRepository.save(pedido);
    }
    //Delete
    public void deletePedido(String pedidoId) {
        pedidoRepository.deleteById(Long.valueOf(pedidoId));
    }
    //fill
}