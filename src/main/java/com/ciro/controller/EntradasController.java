package com.ciro.controller;

import com.ciro.EjemploApplication;
import com.ciro.domain.Entrada;
import com.ciro.domain.Proyecto;
import com.ciro.service.ProyectoService;
import com.ciro.util.Utilitario;
import com.ciro.view.ConfiguracionView;
import com.ciro.view.CrearEntradaView;
import com.ciro.view.CrearProyectoView;
import com.ciro.view.ProyectoView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.format.DateTimeFormatter;

@FXMLController
public class EntradasController {
    private Proyecto proyecto;
    @FXML
    private Label labelProyectoId;
    @FXML
    private Label labelFechaCreacion;
    @FXML
    private Label labelNombre;
    @FXML
    private Label labelValorA;
    @FXML
    private Label labelValorB;
    @FXML
    private Label labelFechaActualizacion;
    @FXML
    private TableView<Entrada> tableViewEntradas;
    @FXML
    private TableColumn<Entrada, Double> divisaAColumn;
    @FXML
    private TableColumn<Entrada, String> fechaColumn;
    @FXML
    private TableColumn<Entrada, Double> tipoCambioColumn;
    @FXML
    private TableColumn<Entrada, Double> divisaBColumn;
    @FXML
    private TableColumn<Entrada, Double> divisaBPorValorAColumn;
    @FXML
    private TableColumn<Entrada, Double> divisaBPorValorBColumn;
    @FXML
    private TableColumn<Entrada, Double> acumuladoColumn;
    private ObservableList<Entrada> entradaObservableList = FXCollections.observableArrayList();
    //Inyecciones
    private ProyectoController proyectoController;
    private ConfiguracionController configuracionController;
    private CrearEntradaController crearEntradaController;
    private ProyectoService proyectoService;

    public void initialize() {
        showTableProyectos();
//        actualizaTabla();
    }

    private void showTableProyectos() {
        divisaAColumn.setCellValueFactory(new PropertyValueFactory<>("divisaA"));
        fechaColumn.setCellValueFactory(
                entra -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    property.setValue(dateFormat.format(entra.getValue().getFechaCreacion()));
                    return property;
                });
        tipoCambioColumn.setCellValueFactory(new PropertyValueFactory<>("tipoCambio"));
        divisaBColumn.setCellValueFactory(new PropertyValueFactory<>("divisaB"));
        divisaBPorValorAColumn.setCellValueFactory(new PropertyValueFactory<>("divisaBPorValorA"));
        divisaBPorValorBColumn.setCellValueFactory(new PropertyValueFactory<>("divisaBPorValorB"));
        acumuladoColumn.setCellValueFactory(new PropertyValueFactory<>("acumulado"));
    }

    private void actualizaTabla() {
        addData();
        tableViewEntradas.refresh();
    }

    private void addData() {
        entradaObservableList.clear();
        entradaObservableList.addAll(proyectoService.findById(proyecto.getId()).getConfiguracion().getEntradas());
        tableViewEntradas.setItems(entradaObservableList);
    }

    @FXML
    public void abrirProyectos(){
        EjemploApplication.getStage().setHeight(Utilitario.ALTURA_400);
        EjemploApplication.getStage().setWidth(Utilitario.ANCHO_600);
        EjemploApplication.showView(ProyectoView.class);
        proyectoController.actualizaTabla();
    }

    @FXML
    private void crearEntrada() {
        EjemploApplication.getStage().setHeight(Utilitario.ALTURA_200);
        EjemploApplication.getStage().setWidth(Utilitario.ANCHO_250);
        EjemploApplication.showView(CrearEntradaView.class);
        crearEntradaController.establecerProyecto(proyecto);
    }

    @FXML
    private void nuevoProyecto(){
        EjemploApplication.getStage().setHeight(Utilitario.ALTURA_250);
        EjemploApplication.getStage().setWidth(Utilitario.ANCHO_250);
        EjemploApplication.showView(CrearProyectoView.class);
    }

    @FXML
    private void actualizarConfiguracion() {
        EjemploApplication.getStage().setHeight(Utilitario.ALTURA_250);
        EjemploApplication.getStage().setWidth(Utilitario.ANCHO_250);
        EjemploApplication.showView(ConfiguracionView.class);
        configuracionController.establecerProyecto(proyecto);
    }

    void actualizarDatosProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
        datosProyecto();
    }

    private void datosProyecto(){
        labelProyectoId.setText(proyecto.getId().toString());
        labelFechaCreacion.setText(proyecto.getFechaCreacion().format(DateTimeFormatter.ISO_DATE));
        labelNombre.setText(proyecto.getNombre());
        labelValorA.setText(Double.toString(proyecto.getConfiguracion().getValorA()));
        labelValorB.setText(Double.toString(proyecto.getConfiguracion().getValorB()));
        labelFechaActualizacion.setText(proyecto.getConfiguracion().getFechaActualizacion().format(DateTimeFormatter.ISO_DATE));
        actualizaTabla();
    }

    @Autowired
    public void setProyectoController(ProyectoController proyectoController) {
        this.proyectoController = proyectoController;
    }

    @Autowired
    public void setConfiguracionController(ConfiguracionController configuracionController) {
        this.configuracionController = configuracionController;
    }

    @Autowired
    public void setCrearEntradaController(CrearEntradaController crearEntradaController) {
        this.crearEntradaController = crearEntradaController;
    }

    @Autowired
    public void setProyectoService(ProyectoService proyectoService) {
        this.proyectoService = proyectoService;
    }
}
