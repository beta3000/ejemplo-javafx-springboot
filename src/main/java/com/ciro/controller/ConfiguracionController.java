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
    private Proyecto proyecto;
    @FXML
    TextField txtValorA;
    @FXML
    TextField txtValorB;

    @FXML
    private void configuracionProyecto(){
        String valorA = txtValorA.getText().toString();
        String valorB = txtValorB.getText().toString();

        if(valorA.isEmpty() || valorB.isEmpty()){
            Utilitario.showMessageAlert("Valores no definido","Ingresa valores para A y B");
        }else {
            Configuracion configuracion = new Configuracion();
            configuracion.setValorA(Double.parseDouble(valorA));
            configuracion.setValorB(Double.parseDouble(valorB));
            proyecto.setConfiguracion(configuracion);
            proyectoService.save(proyecto);
            cambiarView();
        }
    }


    public void establecerProyecto(Proyecto proyecto){
        this.proyecto = proyecto;
    }

    private void cambiarView(){
        EjemploApplication.getStage().setHeight(Utilitario.ALTURA_PANTALLA_GRANDE);
        EjemploApplication.getStage().setWidth(Utilitario.ANCHO_PANTALLA_GRANDE);
        EjemploApplication.showView(EntradasView.class);
        entradasController.actualizarDatosProyecto(proyecto);
    }

    //Inyecciones
    private ProyectoService proyectoService;
    private EntradasController entradasController;
    @Autowired
    public void setProyectoService(ProyectoService proyectoService) {
        this.proyectoService = proyectoService;
    }
    @Autowired
    public void setEntradasController(EntradasController entradasController) {
        this.entradasController = entradasController;
    }
}
