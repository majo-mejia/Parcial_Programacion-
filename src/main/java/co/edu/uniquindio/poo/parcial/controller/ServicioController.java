package co.edu.uniquindio.poo.parcial.controller;

import co.edu.uniquindio.poo.parcial.model.GimnasioSmart;
import co.edu.uniquindio.poo.parcial.model.ServicioAdicional;
import co.edu.uniquindio.poo.parcial.model.TipoServicio;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ServicioController implements Initializable {

    private GimnasioSmart gimnasio = GimnasioSmart.getInstancia();

    @FXML private TextField codigo;
    @FXML private TextField nombre;
    @FXML private TextField descripcion;
    @FXML private TextField precio;
    @FXML private ComboBox<TipoServicio> tipo;
    @FXML private CheckBox disponible;
    @FXML private Button agregar;
    @FXML private Button eliminar;
    @FXML private TableView<ServicioAdicional> tabla;
    @FXML private TableColumn<ServicioAdicional, String> columnaCodigo;
    @FXML private TableColumn<ServicioAdicional, String> columnaNombre;
    @FXML private TableColumn<ServicioAdicional, TipoServicio> columnaTipo;
    @FXML private TableColumn<ServicioAdicional, Number> columnaPrecio;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tipo.setItems(FXCollections.observableArrayList(TipoServicio.values()));
        tipo.getSelectionModel().selectFirst();
        disponible.setSelected(true);
        columnaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        columnaPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        actualizarTabla();
    }

    @FXML
    public void registrarServicio() {
        try {
            if (codigo.getText().isEmpty() || nombre.getText().isEmpty() || tipo.getValue() == null) {
                mostrarMensaje("Completa los datos del servicio.");
                return;
            }

            double precioNumero = Double.parseDouble(precio.getText());
            ServicioAdicional servicio = new ServicioAdicional(codigo.getText(), nombre.getText(),
                    descripcion.getText(), precioNumero, disponible.isSelected(), tipo.getValue());

            gimnasio.registrarServicio(servicio);
            actualizarTabla();
            limpiarCampos();
            mostrarMensaje("Servicio registrado correctamente.");
        } catch (Exception e) {
            mostrarMensaje("Revisa los datos del servicio.");
        }
    }

    @FXML
    public void eliminarServicio() {
        ServicioAdicional servicio = tabla.getSelectionModel().getSelectedItem();

        if (servicio == null) {
            mostrarMensaje("Selecciona un servicio.");
            return;
        }

        gimnasio.eliminarServicio(servicio.getCodigo());
        actualizarTabla();
    }

    private void actualizarTabla() {
        tabla.setItems(FXCollections.observableArrayList(gimnasio.getServicios()));
    }

    private void limpiarCampos() {
        codigo.clear();
        nombre.clear();
        descripcion.clear();
        precio.clear();
        disponible.setSelected(true);
    }

    private void mostrarMensaje(String mensaje) {
        new Alert(Alert.AlertType.INFORMATION, mensaje).showAndWait();
    }
}
