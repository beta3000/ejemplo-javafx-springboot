package com.ciro.controller;

import com.ciro.EjemploApplication;
import com.ciro.domain.Proyecto;
import com.ciro.util.Utilitario;
import com.ciro.view.CrearProyectoView;
import com.ciro.view.ProyectoView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.format.DateTimeFormatter;

@FXMLController
public class EntradasController {

    private Proyecto proyecto;

    @FXML
    Label labelProyectoId;
    @FXML
    Label labelFechaCreacion;
    @FXML
    Label labelNombre;

    @FXML
    public void abrirProyectos(){
        EjemploApplication.getStage().setHeight(Utilitario.ALTURA_PANTALLA_PEQUENIA);
        EjemploApplication.getStage().setWidth(Utilitario.ANCHO_PANTALLA_PEQUENIA);
        EjemploApplication.showView(ProyectoView.class);
        proyectoController.actualizaTabla();
    }

    @FXML
    private void nuevoProyecto(){
        EjemploApplication.getStage().setHeight(Utilitario.ALTURA_CREAR_PROYECTO);
        EjemploApplication.getStage().setWidth(Utilitario.ANCHO_CREAR_PROYECTO);
        EjemploApplication.showView(CrearProyectoView.class);
    }

    public void actualizarDatosProyecto(Proyecto proyecto){
        this.proyecto = proyecto;
        datosProyecto();
    }

    private void datosProyecto(){
        labelProyectoId.setText(proyecto.getId().toString());
        labelFechaCreacion.setText(proyecto.getFechaCreacion().format(DateTimeFormatter.ISO_DATE));
        labelNombre.setText(proyecto.getNombre());
    }

    //Inyecciones
    private ProyectoController proyectoController;
    @Autowired
    public void setProyectoController(ProyectoController proyectoController) {
        this.proyectoController = proyectoController;
    }
}
