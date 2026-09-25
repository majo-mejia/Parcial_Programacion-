package co.edu.uniquindio.poo.parcial.controller;

import co.edu.uniquindio.poo.parcial.model.Cliente;
import co.edu.uniquindio.poo.parcial.model.GimnasioSmart;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ConsultasController {

    private GimnasioSmart gimnasio = GimnasioSmart.getInstancia();

    @FXML private TextField telefono;
    @FXML private Button buscar;
    @FXML private Label resultadoCliente;
    @FXML private DatePicker inicio;
    @FXML private DatePicker fin;
    @FXML private Button ingresos;
    @FXML private Label resultadoIngresos;

    @FXML
    public void initialize() {
        inicio.setValue(java.time.LocalDate.now().withDayOfMonth(1));
        fin.setValue(java.time.LocalDate.now());
    }

    @FXML
    public void buscarCliente() {
        Cliente cliente = gimnasio.buscarClientePorTelefono(telefono.getText());

        if (cliente == null) {
            resultadoCliente.setText("No existe un cliente con ese teléfono.");
        } else {
            String perfecto;
            if (cliente.esNumeroPerfecto()) {
                perfecto = "Sí";
            } else {
                perfecto = "No";
            }

            resultadoCliente.setText("Cliente: " + cliente.getNombre() + " | Número perfecto: " + perfecto);
        }
    }

    @FXML
    public void calcularIngresos() {
        if (inicio.getValue() == null || fin.getValue() == null || inicio.getValue().isAfter(fin.getValue())) {
            new Alert(Alert.AlertType.INFORMATION, "Selecciona un periodo válido.").showAndWait();
            return;
        }

        double total = gimnasio.calcularIngresos(inicio.getValue(), fin.getValue());
        resultadoIngresos.setText("Ingresos del periodo: $" + String.format("%.0f", total));
    }
}
