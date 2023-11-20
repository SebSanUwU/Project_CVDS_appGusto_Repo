package com.escuelaing.demo_appgusto.repository;



import com.escuelaing.demo_appgusto.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
public interface RestauranteRepository extends JpaRepository<Restaurante,Long> {

}