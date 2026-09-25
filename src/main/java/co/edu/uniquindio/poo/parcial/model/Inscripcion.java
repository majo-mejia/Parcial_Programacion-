package co.edu.uniquindio.poo.parcial.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Inscripcion implements Calculable {

    private LocalDate fechaInscripcion;
    private Cliente cliente;
    private PlanEntrenamiento plan;
    private Entrenador entrenador;
    private List<ServicioAdicional> serviciosAdicionales;
    private double porcentajeDescuento;

    public Inscripcion(LocalDate fechaInscripcion, Cliente cliente, PlanEntrenamiento plan) {
        this.fechaInscripcion = fechaInscripcion;
        this.cliente = cliente;
        this.plan = plan;
        serviciosAdicionales = new ArrayList<>();
        porcentajeDescuento = 0;
    }

    public void asignarEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public void agregarServicioAdicional(ServicioAdicional servicio) {
        serviciosAdicionales.add(servicio);
    }

    public void quitarServicioAdicional(ServicioAdicional servicio) {
        serviciosAdicionales.remove(servicio);
    }

    public double calcularValorFinal() {
        double total = plan.calcularValor();

        for (ServicioAdicional servicio : serviciosAdicionales) {
            total += servicio.calcularValor();
        }

        if (plan instanceof PlanPersonalizado && entrenador != null) {
            PlanPersonalizado personalizado = (PlanPersonalizado) plan;
            total += personalizado.getCantidadSesiones() * entrenador.getTarifaSesion();
        }

        return total - (total * porcentajeDescuento);
    }

    @Override
    public double calcularValor() {
        return calcularValorFinal();
    }

    public LocalDate getFechaInscripcion() { return fechaInscripcion; }
    public void setFechaInscripcion(LocalDate fechaInscripcion) { this.fechaInscripcion = fechaInscripcion; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public PlanEntrenamiento getPlan() { return plan; }
    public void setPlan(PlanEntrenamiento plan) { this.plan = plan; }

    public Entrenador getEntrenador() { return entrenador; }

    public List<ServicioAdicional> getServiciosAdicionales() { return serviciosAdicionales; }

    public String getClienteNombre() {
        return cliente.getNombre();
    }

    public String getPlanNombre() {
        return plan.getNombre();
    }

    public String getEntrenadorNombre() {
        return entrenador == null ? "Sin asignar" : entrenador.getNombre();
    }

    public double getValor() {
        return calcularValorFinal();
    }

    public double getPorcentajeDescuento() { return porcentajeDescuento; }

    public void setPorcentajeDescuento(double porcentajeDescuento) {
        if (porcentajeDescuento < 0 || porcentajeDescuento > 1) {
            throw new IllegalArgumentException("El descuento debe estar entre 0 y 1.");
        }
        this.porcentajeDescuento = porcentajeDescuento;
    }

    @Override
    public String toString() {
        return "Inscripción de " + cliente.getNombre() + " - "
                + plan.getNombre() + " = $" + String.format("%.2f", calcularValorFinal());
    }
}
