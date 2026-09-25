package co.edu.uniquindio.poo.parcial.model;

public class DatosPlan {
    private String codigo;
    private String nombre;
    private String descripcion;
    private int duracionMeses;
    private double valorMensual;
    private TipoEstado estado;
    private String beneficio;
    private int cantidadSesiones;
    private TipoEspecialidad especialidad;
    private String objetivo;

    public DatosPlan(String codigo, String nombre, String descripcion, int duracionMeses,
                     double valorMensual, TipoEstado estado, String beneficio,
                     int cantidadSesiones, TipoEspecialidad especialidad, String objetivo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracionMeses = duracionMeses;
        this.valorMensual = valorMensual;
        this.estado = estado;
        this.beneficio = beneficio;
        this.cantidadSesiones = cantidadSesiones;
        this.especialidad = especialidad;
        this.objetivo = objetivo;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public int getDuracionMeses() { return duracionMeses; }
    public double getValorMensual() { return valorMensual; }
    public TipoEstado getEstado() { return estado; }
    public String getBeneficio() { return beneficio; }
    public int getCantidadSesiones() { return cantidadSesiones; }
    public TipoEspecialidad getEspecialidad() { return especialidad; }
    public String getObjetivo() { return objetivo; }
}
