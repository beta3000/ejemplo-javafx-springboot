package com.ciro.service.impl;

import com.ciro.domain.Configuracion;
import com.ciro.repository.ConfiguracionRepository;
import com.ciro.service.ConfiguracionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfiguracionServiceImpl implements ConfiguracionService {

    private ConfiguracionRepository configuracionRepository;

    @Override
    public Configuracion save(Configuracion configuracion) {
        return configuracionRepository.save(configuracion);
    }

    //Inyecciones
    @Autowired
    public void setConfiguracionRepository(ConfiguracionRepository configuracionRepository) {
        this.configuracionRepository = configuracionRepository;
    }
}
