package com.ciro.controller;

import com.ciro.EjemploApplication;
import com.ciro.domain.Configuracion;
import com.ciro.domain.Proyecto;
import com.ciro.service.ProyectoService;
import com.ciro.util.Utilitario;
import com.ciro.view.EntradasView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;

@FXMLController
public class ConfiguracionController {
    @FXML
    private TextField txtValorA;
    @FXML
    private TextField txtValorB;
    private Proyecto proyecto;
    //Inyecciones
    private ProyectoService proyectoService;
    private EntradasController entradasController;

    @FXML
    private void configuracionProyecto(){
        String valorA = txtValorA.getText();
        String valorB = txtValorB.getText();

        if(valorA.isEmpty() || valorB.isEmpty()){
            Utilitario.showMessageAlert("Valores no definido","Ingresa valores para A y B");
        }else {
            if (proyecto.getConfiguracion() == null) {
                Configuracion configuracion = new Configuracion();
                configuracion.setValorA(Double.parseDouble(valorA));
                configuracion.setValorB(Double.parseDouble(valorB));
                proyecto.setConfiguracion(configuracion);
                proyecto = proyectoService.save(proyecto);
                cambiarView();
            } else {
                Configuracion configuracion = proyecto.getConfiguracion();
                configuracion.setValorA(Double.parseDouble(valorA));
                configuracion.setValorB(Double.parseDouble(valorB));
                proyecto.setConfiguracion(configuracion);
                proyecto = proyectoService.save(proyecto);
                cambiarView();
            }

        }
    }

    void establecerProyecto(Proyecto proyecto) {
        if (proyecto.getConfiguracion() != null) {
            txtValorA.setText(Double.toString(proyecto.getConfiguracion().getValorA()));
            txtValorB.setText(Double.toString(proyecto.getConfiguracion().getValorB()));
        }
        this.proyecto = proyecto;
    }

    private void cambiarView(){
        EjemploApplication.getStage().setHeight(Utilitario.ALTURA_600);
        EjemploApplication.getStage().setWidth(Utilitario.ANCHO_1100);
        EjemploApplication.showView(EntradasView.class);
        entradasController.actualizarDatosProyecto(proyecto);
    }

    @Autowired
    public void setProyectoService(ProyectoService proyectoService) {
        this.proyectoService = proyectoService;
    }
    @Autowired
    public void setEntradasController(EntradasController entradasController) {
        this.entradasController = entradasController;
    }
}
