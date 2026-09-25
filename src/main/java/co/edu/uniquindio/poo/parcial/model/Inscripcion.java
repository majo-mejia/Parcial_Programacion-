package co.edu.uniquindio.poo.parcial.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Inscripcion {
    private int numero;
    private Cliente cliente;
    private PlanEntrenamiento plan;
    private LocalDate fecha;
    private Entrenador entrenador;
    private List<ServicioAdicional> servicios = new ArrayList<>();
    private double descuento;

    public Inscripcion(int numero, Cliente cliente, PlanEntrenamiento plan,
                       LocalDate fecha, Entrenador entrenador, double descuento) {
        this.numero = numero;
        this.cliente = cliente;
        this.plan = plan;
        this.fecha = fecha;
        this.entrenador = entrenador;
        this.descuento = descuento;
    }

    public void agregarServicio(ServicioAdicional servicio) {
        if (servicio != null && servicio.isDisponible()) {
            servicios.add(servicio);
        }
    }

    public double calcularTotal() {
        double total = plan.calcularValor();

        for (ServicioAdicional servicio : servicios) {
            total = total + servicio.getPrecio();
        }

        if (plan instanceof PlanPersonalizado && entrenador != null) {
            PlanPersonalizado planPersonalizado = (PlanPersonalizado) plan;
            total = total + planPersonalizado.getCantidadSesiones() * entrenador.getTarifaSesion();
        }

        total = total - total * (descuento / 100.0);
        return total;
    }

    public int getNumero() { return numero; }
    public Cliente getCliente() { return cliente; }
    public PlanEntrenamiento getPlan() { return plan; }
    public LocalDate getFecha() { return fecha; }
    public double getDescuento() { return descuento; }

    public String getDescripcion() {
        return numero + " - " + cliente.getNombre() + " - " + plan.getNombre()
                + " - " + fecha + " - $" + String.format("%.0f", calcularTotal());
    }

    @Override
    public String toString() {
        return getDescripcion();
    }
}
