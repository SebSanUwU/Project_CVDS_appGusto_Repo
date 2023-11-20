package com.escuelaing.demo_appgusto.repository;



import com.escuelaing.demo_appgusto.model.GerenteDelAdministrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
public interface GerenteRepository extends JpaRepository<GerenteDelAdministrador,Long> {

}