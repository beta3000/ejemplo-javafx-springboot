package com.ciro.service;


import com.ciro.domain.Entrada;

public interface EntradaService {
    Entrada calcularValoresPrimeraEntrada(Entrada entrada, double valorA, double valorB);

    Entrada calcularValoresEntradaNoPrimera(Entrada entradaAnterior, Entrada entradaNueva, double valorA, double valorB);

    Entrada save(Entrada entrada);
}
