package com.escuelaing.demo_appgusto.repository;



import com.escuelaing.demo_appgusto.model.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
public interface IngredienteRepository extends JpaRepository<Ingrediente,Long> {

}