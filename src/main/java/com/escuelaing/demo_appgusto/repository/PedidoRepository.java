package com.escuelaing.demo_appgusto.repository;


import com.escuelaing.demo_appgusto.model.Administrador;
import com.escuelaing.demo_appgusto.model.Comensal;
import com.escuelaing.demo_appgusto.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
public interface PedidoRepository extends JpaRepository<Pedido,Long> {

}