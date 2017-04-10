package com.ciro.controller;

import com.ciro.EjemploApplication;
import com.ciro.domain.Entrada;
import com.ciro.domain.Proyecto;
import com.ciro.service.ConfiguracionService;
import com.ciro.service.EntradaService;
import com.ciro.service.ProyectoService;
import com.ciro.util.Utilitario;
import com.ciro.view.EntradasView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;

@FXMLController
public class CrearEntradaController {
    private Proyecto proyecto;
    @FXML
    private TextField txtDivisaA;
    @FXML
    private TextField txtTipoCambio;
    //Inyecciones
    private ProyectoService proyectoService;
    private EntradaService entradaService;
    private EntradasController entradasController;
    private ConfiguracionService configuracionService;

    @FXML
    private void crearEntrada() {
        String divisaA = txtDivisaA.getText();
        String tipoCambio = txtTipoCambio.getText();

        if (divisaA.isEmpty() || tipoCambio.isEmpty()) {
            Utilitario.showMessageAlert("Campos vacios", "Ingrese valores para Divisa A y Tipo de Cambio");
        } else {
            Entrada entrada = new Entrada();
            entrada.setDivisaA(Double.parseDouble(divisaA));
            entrada.setTipoCambio(Double.parseDouble(tipoCambio));


            if (proyecto.getConfiguracion().getEntradas().size() == 0) {
                entrada = entradaService.calcularValoresPrimeraEntrada(entrada, proyecto.getConfiguracion().getValorA(), proyecto.getConfiguracion().getValorB());
            } else {
                entrada = entradaService.calcularValoresEntradaNoPrimera(proyecto.getConfiguracion().getEntradas().get(proyecto.getConfiguracion().getEntradas().size() - 1),
                        entrada, proyecto.getConfiguracion().getValorA(), proyecto.getConfiguracion().getValorB());
            }
            entrada.setConfiguracion(proyecto.getConfiguracion());
            entradaService.save(entrada);
            proyecto = proyectoService.findById(proyecto.getId());
            //Cambiar a Entradas
            cambiarView();
        }
    }

    private void cambiarView() {
        EjemploApplication.getStage().setHeight(Utilitario.ALTURA_600);
        EjemploApplication.getStage().setWidth(Utilitario.ANCHO_1100);
        EjemploApplication.showView(EntradasView.class);
        entradasController.actualizarDatosProyecto(proyecto);
    }

    void establecerProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    @Autowired
    public void setProyectoService(ProyectoService proyectoService) {
        this.proyectoService = proyectoService;
    }

    @Autowired
    public void setEntradaService(EntradaService entradaService) {
        this.entradaService = entradaService;
    }

    @Autowired
    public void setEntradasController(EntradasController entradasController) {
        this.entradasController = entradasController;
    }

    @Autowired
    public void setConfiguracionService(ConfiguracionService configuracionService) {
        this.configuracionService = configuracionService;
    }
}
