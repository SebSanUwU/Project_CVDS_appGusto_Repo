package com.escuelaing.demo_appgusto.repository;


import com.escuelaing.demo_appgusto.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
public interface AdministradorRepository extends JpaRepository<Administrador,Long> {

}