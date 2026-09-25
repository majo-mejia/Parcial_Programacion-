package co.edu.uniquindio.poo.parcial.model;

public class ServicioAdicional implements Nombrable, Calculable {

    private TipoServicio servicio;
    private String codigo;
    private String nombre;
    private String descripcion;
    private double precio;
    private String disponibilidad;

    public ServicioAdicional(TipoServicio servicio, String codigo, String nombre,
                             String descripcion, double precio, String disponibilidad) {
        this.servicio = servicio;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.disponibilidad = disponibilidad;
    }

    public double calcularPrecio() {
        return precio;
    }

    @Override
    public double calcularValor() {
        return calcularPrecio();
    }

    public TipoServicio getServicio() {
        return servicio;
    }

    public void setServicio(TipoServicio servicio) {
        this.servicio = servicio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    @Override
    public String toString() {
        return codigo + " - " + nombre + " ($" + precio + ")";
    }
}

