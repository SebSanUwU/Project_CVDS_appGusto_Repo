package com.escuelaing.demo_appgusto.service;

import com.escuelaing.demo_appgusto.model.Configuration;
import com.escuelaing.demo_appgusto.repository.ConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfigurationService {
    private final ConfigurationRepository configurationRepository;

    @Autowired
    public ConfigurationService(ConfigurationRepository configurationRepository) {
        this.configurationRepository = configurationRepository;
    }

    public Configuration addConfiguration(Configuration configuration) {
        return configurationRepository.save(configuration);
    }

    public Configuration getConfiguration(String propiedad) {
        return configurationRepository.findByPropiedad(propiedad).get(1);
    }

    public List<Configuration> getAllConfigurations() {
        return configurationRepository.findAll();
    }

    public Configuration updateConfiguration(Configuration configuration) {
        if (configurationRepository.findByPropiedad(configuration.getPropiedad()).size() == 0) {
            return configurationRepository.save(configuration);
        }

        return null;
    }

    public void deleteConfiguration(Long propiedad) {
        configurationRepository.deleteById(propiedad);
    }

    public String getPremio() {
        return configurationRepository.findByPropiedad("premio").get(0).getValor();
    }
}