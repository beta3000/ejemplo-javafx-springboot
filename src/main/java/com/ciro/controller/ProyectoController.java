package com.ciro.controller;

import com.ciro.EjemploApplication;
import com.ciro.domain.Proyecto;
import com.ciro.service.ProyectoService;
import com.ciro.util.Utilitario;
import com.ciro.view.CrearProyectoView;
import com.ciro.view.EntradasView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.format.DateTimeFormatter;

@FXMLController
public class ProyectoController {

    @FXML
    TableView<Proyecto> tableViewProyectos;
    @FXML
    TableColumn<Proyecto,Long> idColumn;
    @FXML
    TableColumn<Proyecto,String> fechaCreacionColumn;
    @FXML
    TableColumn<Proyecto,String> nombreColumn;
    private ObservableList<Proyecto> proyectoObservableList = FXCollections.observableArrayList();

    @FXML
    private void abrirProyecto(){
        Proyecto proyecto = tableViewProyectos.getSelectionModel().getSelectedItem();
        if(proyecto != null){
            EjemploApplication.getStage().setHeight(Utilitario.ALTURA_PANTALLA_GRANDE);
            EjemploApplication.getStage().setWidth(Utilitario.ANCHO_PANTALLA_GRANDE);
            EjemploApplication.showView(EntradasView.class);
            entradasController.actualizarDatosProyecto(proyecto);
        }else {
            Utilitario.showMessageAlert("Aviso","No has seleccionado proyecto.");
        }
        System.out.println(proyecto);
    }

    @FXML
    private void nuevoProyecto(){
        EjemploApplication.getStage().setHeight(Utilitario.ALTURA_CREAR_PROYECTO);
        EjemploApplication.getStage().setWidth(Utilitario.ANCHO_CREAR_PROYECTO);
        EjemploApplication.showView(CrearProyectoView.class);
    }

    public void initialize(){
        showTableProyectos();
        actualizaTabla();
    }

    private void showTableProyectos(){

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        fechaCreacionColumn.setCellValueFactory(
                proy -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    property.setValue(dateFormat.format(proy.getValue().getFechaCreacion()));
                    return property;
                });
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    }

    public void actualizaTabla(){
        addData();
        tableViewProyectos.refresh();
    }

    private void addData(){
        proyectoObservableList.clear();
        proyectoObservableList.addAll(proyectoService.findAll());
        tableViewProyectos.setItems(proyectoObservableList);
    }



    private ProyectoService proyectoService;
    @Autowired
    public void setProyectoService(ProyectoService proyectoService) {
        this.proyectoService = proyectoService;
    }
    @Autowired
    private EntradasController entradasController;

}
