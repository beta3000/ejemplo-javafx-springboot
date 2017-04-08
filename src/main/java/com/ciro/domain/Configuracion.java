package com.ciro.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "configuracion")
public class Configuracion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "valor_a")
    private double valorA;
    @Column(name = "valor_b")
    private double valorB;

    //Relaciones
    @OneToMany(mappedBy = "configuracion")
    private List<Entrada> entradas;

    public Configuracion() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getValorA() {
        return valorA;
    }

    public void setValorA(double valorA) {
        this.valorA = valorA;
    }

    public double getValorB() {
        return valorB;
    }

    public void setValorB(double valorB) {
        this.valorB = valorB;
    }

    public List<Entrada> getEntradas() {
        return entradas;
    }

    public void setEntradas(List<Entrada> entradas) {
        this.entradas = entradas;
    }
}
