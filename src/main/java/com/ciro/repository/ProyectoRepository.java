package com.ciro.repository;


import com.ciro.domain.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProyectoRepository extends JpaRepository<Proyecto,Long>{
    Proyecto findByNombre(String nombre);
}
