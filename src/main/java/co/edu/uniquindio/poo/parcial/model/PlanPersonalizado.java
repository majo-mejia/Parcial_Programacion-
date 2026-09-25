package co.edu.uniquindio.poo.parcial.model;

public class PlanPersonalizado extends PlanEntrenamiento {
    private int cantidadSesiones;
    private TipoEspecialidad especialidad;
    private String objetivo;

    public PlanPersonalizado(String codigo, String nombre, String descripcion, int duracionMeses,
                             double valorMensual, TipoEstado estado, int cantidadSesiones,
                             TipoEspecialidad especialidad, String objetivo) {
        super(codigo, nombre, descripcion, duracionMeses, valorMensual, estado, TipoPlan.PERSONALIZADO);
        this.cantidadSesiones = cantidadSesiones;
        this.especialidad = especialidad;
        this.objetivo = objetivo;
    }

    public int getCantidadSesiones() { return cantidadSesiones; }
    public TipoEspecialidad getEspecialidad() { return especialidad; }
    public String getObjetivo() { return objetivo; }

    @Override
    public double calcularValor() {
        return getValorMensual() * getDuracionMeses();
    }
}
