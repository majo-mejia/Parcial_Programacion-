package co.edu.uniquindio.poo.parcial.controller;

import co.edu.uniquindio.poo.parcial.model.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class PlanController implements Initializable {

    private GimnasioSmart gimnasio = GimnasioSmart.getInstancia();

    @FXML private ComboBox<TipoPlan> tipo;
    @FXML private TextField codigo;
    @FXML private TextField nombre;
    @FXML private TextField descripcion;
    @FXML private TextField duracion;
    @FXML private TextField valor;
    @FXML private ComboBox<TipoEstado> estado;
    @FXML private TextField beneficio;
    @FXML private TextField sesiones;
    @FXML private ComboBox<TipoEspecialidad> especialidad;
    @FXML private TextField objetivo;
    @FXML private TableView<PlanEntrenamiento> tabla;
    @FXML private TableColumn<PlanEntrenamiento, String> columnaCodigo;
    @FXML private TableColumn<PlanEntrenamiento, String> columnaNombre;
    @FXML private TableColumn<PlanEntrenamiento, TipoPlan> columnaTipo;
    @FXML private TableColumn<PlanEntrenamiento, Number> columnaDuracion;
    @FXML private TableColumn<PlanEntrenamiento, Number> columnaValor;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tipo.setItems(FXCollections.observableArrayList(TipoPlan.values()));
        estado.setItems(FXCollections.observableArrayList(TipoEstado.values()));
        especialidad.setItems(FXCollections.observableArrayList(TipoEspecialidad.values()));

        tipo.getSelectionModel().select(TipoPlan.BASICO);
        estado.getSelectionModel().select(TipoEstado.ACTIVO);

        columnaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        columnaDuracion.setCellValueFactory(new PropertyValueFactory<>("duracionMeses"));
        columnaValor.setCellValueFactory(new PropertyValueFactory<>("valorMensual"));

        actualizarTabla();
    }

    @FXML
    public void crearPlan() {
        try {
            if (tipo.getValue() == null || estado.getValue() == null) {
                mostrarMensaje("Selecciona el tipo y el estado del plan.");
                return;
            }

            int duracionNumero = Integer.parseInt(duracion.getText());
            double valorNumero = Double.parseDouble(valor.getText());
            int sesionesNumero = 0;

            if (!sesiones.getText().isEmpty()) {
                sesionesNumero = Integer.parseInt(sesiones.getText());
            }

            DatosPlan datos = new DatosPlan(codigo.getText(), nombre.getText(), descripcion.getText(),
                    duracionNumero, valorNumero, estado.getValue(), beneficio.getText(), sesionesNumero,
                    especialidad.getValue(), objetivo.getText());

            if (codigo.getText().isEmpty() || nombre.getText().isEmpty() || duracionNumero <= 0 || valorNumero < 0) {
                mostrarMensaje("Completa correctamente los datos del plan.");
                return;
            }

            PlanFactory factory;

            if (tipo.getValue() == TipoPlan.BASICO) {
                factory = new PlanBasicoFactory();
            } else if (tipo.getValue() == TipoPlan.PREMIUM) {
                factory = new PlanPremiumFactory();
            } else {
                factory = new PlanPersonalizadoFactory();
            }

            PlanEntrenamiento plan = factory.crearPlan(datos);
            gimnasio.registrarPlan(plan);
            actualizarTabla();
            limpiarCampos();
            mostrarMensaje("Plan creado correctamente usando Factory Method.");
        } catch (Exception e) {
            mostrarMensaje("Revisa los datos del plan.");
        }
    }

    @FXML
    public void eliminarPlan() {
        PlanEntrenamiento plan = tabla.getSelectionModel().getSelectedItem();

        if (plan == null) {
            mostrarMensaje("Selecciona un plan.");
            return;
        }

        gimnasio.eliminarPlan(plan.getCodigo());
        actualizarTabla();
    }

    private void actualizarTabla() {
        tabla.setItems(FXCollections.observableArrayList(gimnasio.getPlanes()));
    }

    private void limpiarCampos() {
        codigo.clear();
        nombre.clear();
        descripcion.clear();
        duracion.clear();
        valor.clear();
        beneficio.clear();
        sesiones.clear();
        objetivo.clear();
        especialidad.getSelectionModel().clearSelection();
    }

    private void mostrarMensaje(String mensaje) {
        new Alert(Alert.AlertType.INFORMATION, mensaje).showAndWait();
    }
}
