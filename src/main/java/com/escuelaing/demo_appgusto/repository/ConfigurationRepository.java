package com.escuelaing.demo_appgusto.repository;

import com.escuelaing.demo_appgusto.model.Configuration;
import com.escuelaing.demo_appgusto.repository.ConfigurationRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfigurationRepository extends JpaRepository<Configuration, Long> {
    public List<Configuration> findByPropiedad(String propiedad);

}