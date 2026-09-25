package co.edu.uniquindio.poo.parcial.controller;

import co.edu.uniquindio.poo.parcial.model.DatosPlan;
import co.edu.uniquindio.poo.parcial.model.GimnasioSmart;
import co.edu.uniquindio.poo.parcial.model.PlanBasicoFactory;
import co.edu.uniquindio.poo.parcial.model.PlanEntrenamiento;
import co.edu.uniquindio.poo.parcial.model.PlanFactory;
import co.edu.uniquindio.poo.parcial.model.PlanPersonalizadoFactory;
import co.edu.uniquindio.poo.parcial.model.PlanPremiumFactory;
import co.edu.uniquindio.poo.parcial.model.TipoEspecialidad;
import co.edu.uniquindio.poo.parcial.model.TipoEstado;
import co.edu.uniquindio.poo.parcial.model.TipoPlan;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class PlanController implements Initializable {

    private GimnasioSmart gimnasio = GimnasioSmart.getInstancia();

    @FXML
    private ComboBox<TipoPlan> tipo;

    @FXML
    private TextField codigo;

    @FXML
    private TextField nombre;

    @FXML
    private TextField descripcion;

    @FXML
    private TextField duracion;

    @FXML
    private TextField valor;

    @FXML
    private ComboBox<TipoEstado> estado;

    @FXML
    private TextField beneficio;

    @FXML
    private TextField sesiones;

    @FXML
    private ComboBox<TipoEspecialidad> especialidad;

    @FXML
    private TextField objetivo;

    @FXML
    private TableView<PlanEntrenamiento> tabla;

    @FXML
    private TableColumn<PlanEntrenamiento, String> columnaCodigo;

    @FXML
    private TableColumn<PlanEntrenamiento, String> columnaNombre;

    @FXML
    private TableColumn<PlanEntrenamiento, TipoPlan> columnaTipo;

    @FXML
    private TableColumn<PlanEntrenamiento, Number> columnaDuracion;

    @FXML
    private TableColumn<PlanEntrenamiento, Number> columnaValor;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tipo.setItems(
                FXCollections.observableArrayList(TipoPlan.values())
        );

        estado.setItems(
                FXCollections.observableArrayList(TipoEstado.values())
        );

        especialidad.setItems(
                FXCollections.observableArrayList(TipoEspecialidad.values())
        );

        tipo.getSelectionModel().select(TipoPlan.BASICO);
        estado.getSelectionModel().select(TipoEstado.ACTIVO);

        columnaCodigo.setCellValueFactory(
                new PropertyValueFactory<>("codigo")
        );

        columnaNombre.setCellValueFactory(
                new PropertyValueFactory<>("nombre")
        );

        columnaTipo.setCellValueFactory(
                new PropertyValueFactory<>("tipo")
        );

        columnaDuracion.setCellValueFactory(
                new PropertyValueFactory<>("duracionMeses")
        );

        columnaValor.setCellValueFactory(
                new PropertyValueFactory<>("valorMensual")
        );

        actualizarTabla();
    }


    @FXML
    public void crearPlan() {

        try {

            if (codigo.getText().isEmpty()
                    || nombre.getText().isEmpty()
                    || duracion.getText().isEmpty()
                    || valor.getText().isEmpty()) {

                mostrarMensaje("Completa los datos obligatorios.");
                return;
            }

            int duracionNumero = Integer.parseInt(duracion.getText());
            double valorNumero = Double.parseDouble(valor.getText());

            if (duracionNumero <= 0 || valorNumero < 0) {

                mostrarMensaje("La duración debe ser mayor que cero y el valor no puede ser negativo.");
                return;
            }

            int sesionesNumero = 0;

            if (!sesiones.getText().isEmpty()) {
                sesionesNumero = Integer.parseInt(sesiones.getText());
            }

            if (tipo.getValue() == TipoPlan.PERSONALIZADO) {

                if (especialidad.getValue() == null
                        || objetivo.getText().isEmpty()
                        || sesionesNumero <= 0) {

                    mostrarMensaje(
                            "Para un plan personalizado debes indicar sesiones, especialidad y objetivo."
                    );

                    return;
                }
            }

            DatosPlan datos = new DatosPlan(
                    codigo.getText(),
                    nombre.getText(),
                    descripcion.getText(),
                    duracionNumero,
                    valorNumero,
                    estado.getValue(),
                    beneficio.getText(),
                    sesionesNumero,
                    especialidad.getValue(),
                    objetivo.getText()
            );

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

            mostrarMensaje("Plan creado correctamente.");

        } catch (NumberFormatException e) {

            mostrarMensaje("La duración y el valor deben ser números.");

        } catch (Exception e) {

            mostrarMensaje("No se pudo crear el plan.");
        }
    }


    @FXML
    public void eliminarPlan() {

        PlanEntrenamiento plan =
                tabla.getSelectionModel().getSelectedItem();

        if (plan == null) {

            mostrarMensaje("Selecciona un plan.");
            return;
        }

        gimnasio.eliminarPlan(plan.getCodigo());

        actualizarTabla();
    }


    private void actualizarTabla() {

        tabla.setItems(
                FXCollections.observableArrayList(
                        gimnasio.getPlanes()
                )
        );
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

        Alert alerta = new Alert(
                Alert.AlertType.INFORMATION
        );

        alerta.setTitle("SmartGym");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}