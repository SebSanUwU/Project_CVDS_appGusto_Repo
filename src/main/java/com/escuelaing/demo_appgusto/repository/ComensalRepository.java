package com.escuelaing.demo_appgusto.repository;


import com.escuelaing.demo_appgusto.model.Administrador;
import com.escuelaing.demo_appgusto.model.Comensal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
public interface ComensalRepository extends JpaRepository<Comensal,Long> {

}