package com.ciro.service.impl;


import com.ciro.domain.Proyecto;
import com.ciro.repository.ProyectoRepository;
import com.ciro.service.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProyectoServiceImpl implements ProyectoService{

    @Override
    public Proyecto findById(Long id) {
        return proyectoRepository.findOne(id);
    }

    @Override
    public Proyecto findByNombre(String nombre) {
        return proyectoRepository.findByNombre(nombre);
    }

    @Override
    public List<Proyecto> findAll() {
        return proyectoRepository.findAll();
    }

    @Override
    public Proyecto save(Proyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }

    @Override
    public void delete(Long id) {
        proyectoRepository.delete(id);
    }

    //Injections
    private ProyectoRepository proyectoRepository;
    @Autowired
    public void setProyectoRepository(ProyectoRepository proyectoRepository) {
        this.proyectoRepository = proyectoRepository;
    }
}
