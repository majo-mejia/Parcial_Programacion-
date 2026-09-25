package co.edu.uniquindio.poo.parcial.controller;

import co.edu.uniquindio.poo.parcial.model.Cliente;
import co.edu.uniquindio.poo.parcial.model.GimnasioSmart;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ClienteController implements Initializable {

    private GimnasioSmart gimnasio = GimnasioSmart.getInstancia();
    private ObservableList<Cliente> listaClientes = FXCollections.observableArrayList();

    @FXML private TextField nombre;
    @FXML private TextField documento;
    @FXML private TextField telefono;
    @FXML private TextField correo;
    @FXML private TextField edad;
    @FXML private TableView<Cliente> tabla;
    @FXML private TableColumn<Cliente, String> columnaNombre;
    @FXML private TableColumn<Cliente, String> columnaDocumento;
    @FXML private TableColumn<Cliente, String> columnaTelefono;
    @FXML private TableColumn<Cliente, String> columnaCorreo;
    @FXML private TableColumn<Cliente, Integer> columnaEdad;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaDocumento.setCellValueFactory(new PropertyValueFactory<>("documento"));
        columnaTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        columnaCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        columnaEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));

        tabla.setItems(listaClientes);
        actualizarTabla();
    }

    @FXML
    public void registrarCliente() {
        // confirma la accion de boton
        System.out.println(" el Boton registrar fue presionado");

        try {
            String nombreTexto = nombre.getText() != null ? nombre.getText().trim() : "";
            String documentoTexto = documento.getText() != null ? documento.getText().trim() : "";
            String telefonoTexto = telefono.getText() != null ? telefono.getText().trim() : "";
            String correoTexto = correo.getText() != null ? correo.getText().trim() : "";
            String edadTexto = edad.getText() != null ? edad.getText().trim() : "";

            if (nombreTexto.isEmpty() || documentoTexto.isEmpty() || edadTexto.isEmpty()) {
                mostrarMensaje("Por favor completa Nombre, Documento y Edad.");
                return;
            }

            int edadNumero = Integer.parseInt(edadTexto);

            Cliente cliente = new Cliente(
                    nombreTexto,
                    documentoTexto,
                    telefonoTexto,
                    correoTexto,
                    edadNumero,
                    LocalDate.now()
            );

            gimnasio.registrarCliente(cliente);
            actualizarTabla();
            limpiarCampos();
            mostrarMensaje("Cliente registrado correctamente.");

        } catch (NumberFormatException e) {
            mostrarMensaje("La edad debe ser un número entero.");
        } catch (Exception e) {
            e.printStackTrace();
            mostrarMensaje("Error al registrar: " + e.getMessage());
        }
    }

    @FXML
    public void eliminarCliente() {
        Cliente seleccionado = tabla.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            gimnasio.eliminarCliente(seleccionado.getDocumento());
            actualizarTabla();
        } else {
            mostrarMensaje("Selecciona un cliente de la tabla.");
        }
    }

    private void actualizarTabla() {
        listaClientes.clear();
        listaClientes.addAll(gimnasio.getClientes());
        tabla.refresh();
    }

    private void limpiarCampos() {
        nombre.clear();
        documento.clear();
        telefono.clear();
        correo.clear();
        edad.clear();
    }

    private void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("SmartGym");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}