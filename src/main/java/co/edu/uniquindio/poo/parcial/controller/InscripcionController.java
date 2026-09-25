package co.edu.uniquindio.poo.parcial.controller;

import co.edu.uniquindio.poo.parcial.model.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class InscripcionController implements Initializable {

    private GimnasioSmart gimnasio = GimnasioSmart.getInstancia();

    @FXML private ComboBox<Cliente> cliente;
    @FXML private ComboBox<PlanEntrenamiento> plan;
    @FXML private ComboBox<Entrenador> entrenador;
    @FXML private ListView<ServicioAdicional> servicios;
    @FXML private DatePicker fecha;
    @FXML private TextField descuento;
    @FXML private Button actualizar;
    @FXML private Button registrar;
    @FXML private TableView<Inscripcion> tabla;
    @FXML private TableColumn<Inscripcion, Number> columnaNumero;
    @FXML private TableColumn<Inscripcion, String> columnaDescripcion;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fecha.setValue(java.time.LocalDate.now());
        servicios.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        columnaNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        columnaDescripcion.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        actualizarListas();
        actualizarTabla();
    }

    @FXML
    public void actualizarListas() {
        cliente.setItems(FXCollections.observableArrayList(gimnasio.getClientes()));
        plan.setItems(FXCollections.observableArrayList(gimnasio.getPlanes()));
        entrenador.setItems(FXCollections.observableArrayList(gimnasio.getEntrenadores()));
        servicios.setItems(FXCollections.observableArrayList(gimnasio.getServicios()));
    }

    @FXML
    public void registrarInscripcion() {
        try {
            Cliente clienteSeleccionado = cliente.getValue();
            PlanEntrenamiento planSeleccionado = plan.getValue();
            Entrenador entrenadorSeleccionado = entrenador.getValue();

            if (clienteSeleccionado == null || planSeleccionado == null || fecha.getValue() == null) {
                mostrarMensaje("Selecciona cliente, plan y fecha.");
                return;
            }

            double descuentoNumero = Double.parseDouble(descuento.getText());

            if (descuentoNumero < 0 || descuentoNumero > 100) {
                mostrarMensaje("El descuento debe estar entre 0 y 100.");
                return;
            }

            if (planSeleccionado instanceof PlanPersonalizado && entrenadorSeleccionado == null) {
                mostrarMensaje("El plan personalizado necesita un entrenador.");
                return;
            }

            int numero = gimnasio.getInscripciones().size() + 1;
            Inscripcion inscripcion = new Inscripcion(numero, clienteSeleccionado, planSeleccionado,
                    fecha.getValue(), entrenadorSeleccionado, descuentoNumero);

            for (ServicioAdicional servicio : servicios.getSelectionModel().getSelectedItems()) {
                inscripcion.agregarServicio(servicio);
            }

            gimnasio.registrarInscripcion(inscripcion);
            actualizarTabla();
            mostrarMensaje("Inscripción registrada. Total: $" + String.format("%.0f", inscripcion.calcularTotal()));
        } catch (Exception e) {
            mostrarMensaje("Revisa los datos de la inscripción.");
        }
    }

    private void actualizarTabla() {
        tabla.setItems(FXCollections.observableArrayList(gimnasio.getInscripciones()));
    }

    private void mostrarMensaje(String mensaje) {
        new Alert(Alert.AlertType.INFORMATION, mensaje).showAndWait();
    }
}
