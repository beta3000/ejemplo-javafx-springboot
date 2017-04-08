package com.ciro.domain;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "entrada")
public class Entrada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;
    @Column(name = "divisa_a")
    private double divisaA;
    @Column(name = "tipo_cambio")
    private double tipoCambio;
    @Column(name = "divisa_b")
    private double divisaB;
    @Column(name = "divisa_b_por_valor_a")
    private double divisaBPorValorA;
    @Column(name = "divisa_b_por_valor_b")
    private double divisaBPorValorB;
    @Column(name = "acumulado")
    private double acumulado;

    //Relaciones
    @ManyToOne
    @JoinColumn(name = "configuracion_id")
    @Cascade(CascadeType.SAVE_UPDATE)
    private Configuracion configuracion;

    public Entrada() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public double getDivisaA() {
        return divisaA;
    }

    public void setDivisaA(double divisaA) {
        this.divisaA = divisaA;
    }

    public double getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(double tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public double getDivisaB() {
        return divisaB;
    }

    public void setDivisaB(double divisaB) {
        this.divisaB = divisaB;
    }

    public double getDivisaBPorValorA() {
        return divisaBPorValorA;
    }

    public void setDivisaBPorValorA(double divisaBPorValorA) {
        this.divisaBPorValorA = divisaBPorValorA;
    }

    public double getDivisaBPorValorB() {
        return divisaBPorValorB;
    }

    public void setDivisaBPorValorB(double divisaBPorValorB) {
        this.divisaBPorValorB = divisaBPorValorB;
    }

    public double getAcumulado() {
        return acumulado;
    }

    public void setAcumulado(double acumulado) {
        this.acumulado = acumulado;
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
            fechaCreacion = LocalDate.now();
        }
    }
}
