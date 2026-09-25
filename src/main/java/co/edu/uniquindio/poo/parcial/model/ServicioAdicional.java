package co.edu.uniquindio.poo.parcial.model;

public class ServicioAdicional {
    private String codigo;
    private String nombre;
    private String descripcion;
    private double precio;
    private boolean disponible;
    private TipoServicio tipo;

    public ServicioAdicional(String codigo, String nombre, String descripcion, double precio, boolean disponible, TipoServicio tipo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.disponible = disponible;
        this.tipo = tipo;
    }

    public ServicioAdicional(String masaje, int i, boolean b) {
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public double getPrecio() { return precio; }
    public boolean isDisponible() { return disponible; }
    public TipoServicio getTipo() { return tipo; }

    @Override
    public String toString() {
        return nombre + " - $" + String.format("%.0f", precio);
    }
}

