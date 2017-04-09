package com.ciro.controller;

import com.ciro.EjemploApplication;
import com.ciro.domain.Proyecto;
import com.ciro.service.ProyectoService;
import com.ciro.util.Utilitario;
import com.ciro.view.ConfiguracionView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@FXMLController
public class CrearProyectoController {
    private Proyecto proyecto;
    @FXML
    private TextField txtNombre;

    @FXML
    private void crearProyecto(){
        if(txtNombre.getText().toString().isEmpty()){
            Utilitario.showMessageAlert("Igrese nombre","Ingrese un nombre para su nuevo proyecto");
        }else {
            //Creando el proyecto
            Proyecto proyecto = new Proyecto();
            proyecto.setFechaCreacion(LocalDateTime.now());
            proyecto.setNombre(txtNombre.getText().toString());

            //Guardando el proyecto
            this.proyecto = proyectoService.save(proyecto);

            if(this.proyecto != null){
                cambiarView();
            }
        }
    }

    private void cambiarView(){
        EjemploApplication.getStage().setHeight(Utilitario.ALTURA_PANTALLA_PEQUENIA);
        EjemploApplication.getStage().setWidth(Utilitario.ANCHO_PANTALLA_PEQUENIA);
        EjemploApplication.showView(ConfiguracionView.class);
        configuracionController.establecerProyecto(proyecto);
    }

    //Injecciones
    private ProyectoService proyectoService;
    private ConfiguracionController configuracionController;
    @Autowired
    public void setProyectoService(ProyectoService proyectoService) {
        this.proyectoService = proyectoService;
    }
    @Autowired
    public void setConfiguracionController(ConfiguracionController configuracionController) {
        this.configuracionController = configuracionController;
    }
}
