package com.ciro.service.impl;

import com.ciro.domain.Entrada;
import com.ciro.repository.EntradaRepository;
import com.ciro.service.EntradaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntradaServiceimpl implements EntradaService {

    private EntradaRepository entradaRepository;

    @Override
    public Entrada calcularValoresPrimeraEntrada(Entrada entrada, double valorA, double valorB) {
        double divisaA = entrada.getDivisaA();
        double tipoCambio = entrada.getTipoCambio();

        entrada.setDivisaB(divisaA * tipoCambio);
        entrada.setDivisaBPorValorA(entrada.getDivisaB() * valorA);
        entrada.setDivisaBPorValorB(entrada.getDivisaB() * valorB);
        entrada.setAcumulado(entrada.getDivisaBPorValorA() + entrada.getDivisaBPorValorB());
        return entrada;
    }

    @Override
    public Entrada calcularValoresEntradaNoPrimera(Entrada entradaAnterior, Entrada entradaNueva, double valorA, double valorB) {
        double divisaA = entradaNueva.getDivisaA();
        double tipoCambio = entradaNueva.getTipoCambio();

        entradaNueva.setDivisaB(divisaA * tipoCambio);
        entradaNueva.setDivisaBPorValorA(entradaNueva.getDivisaB() * valorA);
        entradaNueva.setDivisaBPorValorB(entradaNueva.getDivisaB() * valorB);
        entradaNueva.setAcumulado(entradaNueva.getDivisaBPorValorA() + entradaNueva.getDivisaBPorValorB() + entradaAnterior.getAcumulado());
        return entradaNueva;
    }

    @Override
    public Entrada save(Entrada entrada) {
        return entradaRepository.save(entrada);
    }

    //Inyecciones
    @Autowired
    public void setEntradaRepository(EntradaRepository entradaRepository) {
        this.entradaRepository = entradaRepository;
    }
}
