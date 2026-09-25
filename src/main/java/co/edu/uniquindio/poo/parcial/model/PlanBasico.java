package co.edu.uniquindio.poo.parcial.model;

public class PlanBasico extends PlanEntrenamiento {
    private String beneficio;

    public PlanBasico(String codigo, String nombre, String descripcion, int duracionMeses,
                      double valorMensual, TipoEstado estado, String beneficio) {
        super(codigo, nombre, descripcion, duracionMeses, valorMensual, estado, TipoPlan.BASICO);
        this.beneficio = beneficio;
    }

    public String getBeneficio() { return beneficio; }

    @Override
    public double calcularValor() {
        return getValorMensual() * getDuracionMeses();
    }
}
