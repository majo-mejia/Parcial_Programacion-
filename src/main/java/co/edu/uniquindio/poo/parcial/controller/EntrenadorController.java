package co.edu.uniquindio.poo.parcial.controller;

import co.edu.uniquindio.poo.parcial.model.Entrenador;
import co.edu.uniquindio.poo.parcial.model.GimnasioSmart;
import co.edu.uniquindio.poo.parcial.model.TipoEspecialidad;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class EntrenadorController implements Initializable {

    private GimnasioSmart gimnasio = GimnasioSmart.getInstancia();

    @FXML private TextField identificacion;
    @FXML private TextField nombre;
    @FXML private ComboBox<TipoEspecialidad> especialidad;
    @FXML private TextField telefono;
    @FXML private TextField tarifa;
    @FXML private Button registrar;
    @FXML private Button eliminar;
    @FXML private TableView<Entrenador> tabla;
    @FXML private TableColumn<Entrenador, String> columnaIdentificacion;
    @FXML private TableColumn<Entrenador, String> columnaNombre;
    @FXML private TableColumn<Entrenador, TipoEspecialidad> columnaEspecialidad;
    @FXML private TableColumn<Entrenador, String> columnaTelefono;
    @FXML private TableColumn<Entrenador, Number> columnaTarifa;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        especialidad.setItems(FXCollections.observableArrayList(TipoEspecialidad.values()));
        columnaIdentificacion.setCellValueFactory(new PropertyValueFactory<>("identificacion"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaEspecialidad.setCellValueFactory(new PropertyValueFactory<>("especialidad"));
        columnaTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        columnaTarifa.setCellValueFactory(new PropertyValueFactory<>("tarifaSesion"));
        actualizarTabla();
    }

    @FXML
    public void registrarEntrenador() {
        try {
            if (identificacion.getText().isEmpty() || nombre.getText().isEmpty() || especialidad.getValue() == null) {
                mostrarMensaje("Completa los datos del entrenador.");
                return;
            }

            double tarifaNumero = Double.parseDouble(tarifa.getText());
            Entrenador entrenador = new Entrenador(identificacion.getText(), nombre.getText(),
                    especialidad.getValue(), telefono.getText(), tarifaNumero);

            gimnasio.registrarEntrenador(entrenador);
            actualizarTabla();
            limpiarCampos();
            mostrarMensaje("Entrenador registrado correctamente.");
        } catch (Exception e) {
            mostrarMensaje("Revisa los datos del entrenador.");
        }
    }

    @FXML
    public void eliminarEntrenador() {
        Entrenador entrenador = tabla.getSelectionModel().getSelectedItem();

        if (entrenador == null) {
            mostrarMensaje("Selecciona un entrenador.");
            return;
        }

        gimnasio.eliminarEntrenador(entrenador.getIdentificacion());
        actualizarTabla();
    }

    private void actualizarTabla() {
        tabla.setItems(FXCollections.observableArrayList(gimnasio.getEntrenadores()));
    }

    private void limpiarCampos() {
        identificacion.clear();
        nombre.clear();
        especialidad.getSelectionModel().clearSelection();
        telefono.clear();
        tarifa.clear();
    }

    private void mostrarMensaje(String mensaje) {
        new Alert(Alert.AlertType.INFORMATION, mensaje).showAndWait();
    }
}
