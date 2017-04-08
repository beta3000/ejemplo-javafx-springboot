package com.ciro.service;


import com.ciro.domain.Proyecto;

import java.util.List;

public interface ProyectoService {

    Proyecto findById(Long id);
    Proyecto findByNombre(String nombre);
    List<Proyecto> findAll();
    Proyecto save(Proyecto proyecto);
    void delete(Long id);
}
