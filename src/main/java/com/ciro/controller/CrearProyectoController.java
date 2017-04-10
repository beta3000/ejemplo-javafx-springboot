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
    //Injecciones
    private ProyectoService proyectoService;
    private ConfiguracionController configuracionController;

    @FXML
    private void crearProyecto(){
        if (txtNombre.getText().isEmpty()) {
            Utilitario.showMessageAlert("Igrese nombre","Ingrese un nombre para su nuevo proyecto");
        }else {
            //Creando el proyecto
            Proyecto proyecto = new Proyecto();
            proyecto.setFechaCreacion(LocalDateTime.now());
            proyecto.setNombre(txtNombre.getText());

            //Guardando el proyecto
            this.proyecto = proyectoService.save(proyecto);

            if(this.proyecto != null){
                cambiarView();
            }
        }
    }

    private void cambiarView(){
        EjemploApplication.getStage().setHeight(Utilitario.ALTURA_250);
        EjemploApplication.getStage().setWidth(Utilitario.ANCHO_250);
        EjemploApplication.showView(ConfiguracionView.class);
        configuracionController.establecerProyecto(proyecto);
    }

    @Autowired
    public void setProyectoService(ProyectoService proyectoService) {
        this.proyectoService = proyectoService;
    }
    @Autowired
    public void setConfiguracionController(ConfiguracionController configuracionController) {
        this.configuracionController = configuracionController;
    }
}
