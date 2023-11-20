package com.escuelaing.demo_appgusto.repository;



import com.escuelaing.demo_appgusto.model.Platillo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
public interface PlatilloRepository extends JpaRepository<Platillo,Long> {

}