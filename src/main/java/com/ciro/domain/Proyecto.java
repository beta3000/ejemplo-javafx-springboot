package com.ciro.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "proyecto")
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    //Relaciones
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "configuracion_id")
    private Configuracion configuracion;


    public Proyecto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Configuracion getConfiguracion() {
        return configuracion;
    }

    public void setConfiguracion(Configuracion configuracion) {
        this.configuracion = configuracion;
    }

    @PrePersist
    public void fechaCreacion(){
        if(fechaCreacion == null){
            fechaCreacion = LocalDateTime.now();
        }
    }

    @Override
    public String toString() {
        return "Proyecto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", configuracion=" + configuracion +
                '}';
    }
}
