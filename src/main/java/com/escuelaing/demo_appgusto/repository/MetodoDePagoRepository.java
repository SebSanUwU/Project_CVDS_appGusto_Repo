package com.escuelaing.demo_appgusto.repository;



import com.escuelaing.demo_appgusto.model.MetodoDePago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
public interface MetodoDePagoRepository extends JpaRepository<MetodoDePago,Long> {

}