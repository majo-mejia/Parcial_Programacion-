package co.edu.uniquindio.poo.parcial.controller;

import co.edu.uniquindio.poo.parcial.model.Cliente;
import co.edu.uniquindio.poo.parcial.model.GimnasioSmart;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ClienteController implements Initializable {

    private GimnasioSmart gimnasio = GimnasioSmart.getInstancia();

    @FXML private TextField nombre;
    @FXML private TextField documento;
    @FXML private TextField telefono;
    @FXML private TextField correo;
    @FXML private TextField edad;
    @FXML private Button registrar;
    @FXML private Button eliminar;
    @FXML private TableView<Cliente> tabla;
    @FXML private TableColumn<Cliente, String> columnaNombre;
    @FXML private TableColumn<Cliente, String> columnaDocumento;
    @FXML private TableColumn<Cliente, String> columnaTelefono;
    @FXML private TableColumn<Cliente, String> columnaCorreo;
    @FXML private TableColumn<Cliente, Number> columnaEdad;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaDocumento.setCellValueFactory(new PropertyValueFactory<>("documento"));
        columnaTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        columnaCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        columnaEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        actualizarTabla();
    }

    @FXML
    public void registrarCliente() {
        try {
            String nombreTexto = nombre.getText();
            String documentoTexto = documento.getText();
            String telefonoTexto = telefono.getText();
            String correoTexto = correo.getText();
            int edadNumero = Integer.parseInt(edad.getText());

            if (nombreTexto.isEmpty() || documentoTexto.isEmpty()) {
                mostrarMensaje("El nombre y el documento son obligatorios.");
                return;
            }

            Cliente cliente = new Cliente(nombreTexto, documentoTexto, telefonoTexto,
                    correoTexto, edadNumero, LocalDate.now());

            gimnasio.registrarCliente(cliente);
            actualizarTabla();
            limpiarCampos();
            mostrarMensaje("Cliente registrado correctamente.");
        } catch (Exception e) {
            mostrarMensaje("Revisa los datos del cliente.");
        }
    }

    @FXML
    public void eliminarCliente() {
        Cliente cliente = tabla.getSelectionModel().getSelectedItem();

        if (cliente == null) {
            mostrarMensaje("Selecciona un cliente.");
            return;
        }

        gimnasio.eliminarCliente(cliente.getDocumento());
        actualizarTabla();
    }

    private void actualizarTabla() {
        tabla.setItems(FXCollections.observableArrayList(gimnasio.getClientes()));
    }

    private void limpiarCampos() {
        nombre.clear();
        documento.clear();
        telefono.clear();
        correo.clear();
        edad.clear();
    }

    private void mostrarMensaje(String mensaje) {
        new Alert(Alert.AlertType.INFORMATION, mensaje).showAndWait();
    }
}
