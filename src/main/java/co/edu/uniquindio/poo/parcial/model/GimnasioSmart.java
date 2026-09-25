package co.edu.uniquindio.poo.parcial.model;

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

    // constructor privado por el singleton
    private GimnasioSmart() {
    }

    // metodo del singletoon
    public static GimnasioSmart getInstancia() {

        if (instancia == null) {
            instancia = new GimnasioSmart();
        }

        return instancia;
    }

    // registra información

    public void registrarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void registrarEntrenador(Entrenador entrenador) {
        entrenadores.add(entrenador);
    }

    public void registrarPlan(PlanEntrenamiento plan) {
        planes.add(plan);
    }

    public void registrarServicio(ServicioAdicional servicio) {
        servicios.add(servicio);
    }

    public void registrarInscripcion(Inscripcion inscripcion) {
        inscripciones.add(inscripcion);
    }

    // elimina cliente

    public boolean eliminarCliente(String documento) {

        for (int i = 0; i < clientes.size(); i++) {

            Cliente cliente = clientes.get(i);

            if (cliente.getDocumento().equals(documento)) {
                clientes.remove(i);
                return true;
            }
        }

        return false;
    }

    // elimina entrenador

    public boolean eliminarEntrenador(String documento) {

        for (int i = 0; i < entrenadores.size(); i++) {

            Entrenador entrenador = entrenadores.get(i);

            if (entrenador.getDocumento().equals(documento)) {
                entrenadores.remove(i);
                return true;
            }
        }

        return false;
    }

    // elimina plan

    public boolean eliminarPlan(String codigo) {

        for (int i = 0; i < planes.size(); i++) {

            PlanEntrenamiento plan = planes.get(i);

            if (plan.getCodigo().equals(codigo)) {
                planes.remove(i);
                return true;
            }
        }

        return false;
    }

    // elimina servicio

    public boolean eliminarServicio(String codigo) {

        for (int i = 0; i < servicios.size(); i++) {

            ServicioAdicional servicio = servicios.get(i);

            if (servicio.getCodigo().equals(codigo)) {
                servicios.remove(i);
                return true;
            }
        }

        return false;
    }

    // busca cliente por documento

    public Cliente buscarCliente(String documento) {

        for (Cliente cliente : clientes) {

            if (cliente.getDocumento().equals(documento)) {
                return cliente;
            }
        }

        return null;
    }

    // busca cliente por telefono

    public Cliente buscarClientePorTelefono(String telefono) {

        for (Cliente cliente : clientes) {

            if (cliente.getTelefono().equals(telefono)) {
                return cliente;
            }
        }

        return null;
    }

    // busca un entrenador

    public Entrenador buscarEntrenador(String identificacion) {

        for (Entrenador entrenador : entrenadores) {

            if (entrenador.getDocumento().equals(identificacion)) {
                return entrenador;
            }
        }

        return null;
    }

    // se puede buscar el plan

    public PlanEntrenamiento buscarPlan(String codigo) {

        for (PlanEntrenamiento plan : planes) {

            if (plan.getCodigo().equals(codigo)) {
                return plan;
            }
        }

        return null;
    }

    // metodo de calcular ingresos de un periodo

    public double calcularIngresos(LocalDate inicio, LocalDate fin) {

        double total = 0;

        for (Inscripcion inscripcion : inscripciones) {

            if (!inscripcion.getFecha().isBefore(inicio)
                    && !inscripcion.getFecha().isAfter(fin)) {

                total = total + inscripcion.calcularTotal();
            }
        }

        return total;
    }

    // getters

    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Entrenador> getEntrenadores() {
        return entrenadores;
    }

    public List<PlanEntrenamiento> getPlanes() {
        return planes;
    }

    public List<ServicioAdicional> getServicios() {
        return servicios;
    }

    public List<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    // Getters de los datos del gimnasio

    public String getNombre() {
        return nombre;
    }

    public String getNit() {
        return nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }
}