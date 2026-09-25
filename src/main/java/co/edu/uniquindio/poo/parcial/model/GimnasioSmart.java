package co.edu.uniquindio.poo.parcial.model;

import co.edu.uniquindio.poo.parcial.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *  esta sera la clase singletoon solo se puede crear una solo y se encarga de todo
 **/
public class GimnasioSmart {
    private static GimnasioSmart instancia;

    private final List<Cliente> clientes = new ArrayList<>();
    private final List<Entrenador> entrenadores = new ArrayList<>();
    private final List<PlanEntrenamiento> planes = new ArrayList<>();
    private final List<ServicioAdicional> servicios = new ArrayList<>();
    private final List<Inscripcion> inscripciones = new ArrayList<>();

    private String nombre = "SmartGym";
    private String nit = "900000000-1";
    private String direccion = "Armenia, Quindío";
    private String telefono = "3000000000";
    private String correo = "smartgym@gmail.com";
    private String paginaWeb = "www.smartgym.com";

    private GimnasioSmart() {
    }

    public static GimnasioSmart getInstancia() {
        if (instancia == null) {
            instancia = new GimnasioSmart();
        }
        return instancia;
    }

    public void registrarCliente(Cliente cliente) { clientes.add(cliente); }
    public void registrarEntrenador(Entrenador entrenador) { entrenadores.add(entrenador); }
    public void registrarPlan(PlanEntrenamiento plan) { planes.add(plan); }
    public void registrarServicio(ServicioAdicional servicio) { servicios.add(servicio); }
    public void registrarInscripcion(Inscripcion inscripcion) { inscripciones.add(inscripcion); }

    public boolean eliminarCliente(String documento) {
        return clientes.removeIf(c -> c.getDocumento().equals(documento));
    }

    public boolean eliminarEntrenador(String identificacion) {
        return entrenadores.removeIf(e -> e.getIdentificacion().equals(identificacion));
    }

    public boolean eliminarPlan(String codigo) {
        return planes.removeIf(p -> p.getCodigo().equals(codigo));
    }

    public boolean eliminarServicio(String codigo) {
        return servicios.removeIf(s -> s.getCodigo().equals(codigo));
    }

    public Cliente buscarCliente(String documento) {
        for (Cliente cliente : clientes) {
            if (cliente.getDocumento().equals(documento)) return cliente;
        }
        return null;
    }

    public Cliente buscarClientePorTelefono(String telefono) {
        for (Cliente cliente : clientes) {
            if (cliente.getTelefono().equals(telefono)) return cliente;
        }
        return null;
    }

    public Entrenador buscarEntrenador(String identificacion) {
        for (Entrenador entrenador : entrenadores) {
            if (entrenador.getIdentificacion().equals(identificacion)) return entrenador;
        }
        return null;
    }

    public PlanEntrenamiento buscarPlan(String codigo) {
        for (PlanEntrenamiento plan : planes) {
            if (plan.getCodigo().equals(codigo)) return plan;
        }
        return null;
    }

    public double calcularIngresos(LocalDate inicio, LocalDate fin) {
        double total = 0;
        for (Inscripcion inscripcion : inscripciones) {
            if (!inscripcion.getFecha().isBefore(inicio) && !inscripcion.getFecha().isAfter(fin)) {
                total += inscripcion.calcularTotal();
            }
        }
        return total;
    }

    public List<Cliente> getClientes() { return clientes; }
    public List<Entrenador> getEntrenadores() { return entrenadores; }
    public List<PlanEntrenamiento> getPlanes() { return planes; }
    public List<ServicioAdicional> getServicios() { return servicios; }
    public List<Inscripcion> getInscripciones() { return inscripciones; }

    public String getNombre() { return nombre; }
    public String getNit() { return nit; }
    public String getDireccion() { return direccion; }
    public String getTelefono() { return telefono; }
    public String getCorreo() { return correo; }
    public String getPaginaWeb() { return paginaWeb; }
}
