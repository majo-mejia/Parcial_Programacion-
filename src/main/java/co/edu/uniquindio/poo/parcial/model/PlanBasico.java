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
    PlanBasico plan = new PlanBasico("P01", "Plan Mensual", "Descripcion", 1, 100000.0, TipoEstado.ACTIVO, "Beneficio basico");
}
