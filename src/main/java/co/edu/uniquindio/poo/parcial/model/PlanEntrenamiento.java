package co.edu.uniquindio.poo.parcial.model;

public abstract class PlanEntrenamiento {
    private String codigo;
    private String nombre;
    private String descripcion;
    private int duracionMeses;
    private double valorMensual;
    private TipoEstado estado;
    private final TipoPlan tipo;

    protected PlanEntrenamiento(String codigo, String nombre, String descripcion,
                                int duracionMeses, double valorMensual, TipoEstado estado,
                                TipoPlan tipo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracionMeses = duracionMeses;
        this.valorMensual = valorMensual;
        this.estado = estado;
        this.tipo = tipo;
    }

    public PlanEntrenamiento(String nombre, double valorMensual, TipoPlan tipo) {
    this.nombre = nombre;
        this.valorMensual = valorMensual;
        this.tipo = tipo;
    }

    public PlanEntrenamiento(String planBasico, String planPersonalizado, String descripcion, int duracionMeses, double valorMensual, TipoEstado tipoEstado, int cantidadSesiones) {
        this(planBasico, 0.0, null); // Llama al constructor principal
        this.codigo = planBasico;
    }

    public abstract double calcularValor();

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public int getDuracionMeses() { return duracionMeses; }
    public double getValorMensual() { return valorMensual; }
    public TipoEstado getEstado() { return estado; }
    public TipoPlan getTipo() { return tipo; }

    @Override
    public String toString() {
        return codigo + " - " + nombre + " (" + tipo + ")";
    }
}
