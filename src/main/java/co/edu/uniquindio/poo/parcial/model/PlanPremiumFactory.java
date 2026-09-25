package co.edu.uniquindio.poo.parcial.model;

public class PlanPremiumFactory extends PlanFactory {
    @Override
    public PlanEntrenamiento crearPlan(DatosPlan datos) {
        return new PlanPremium(datos.getCodigo(), datos.getNombre(), datos.getDescripcion(),
                datos.getDuracionMeses(), datos.getValorMensual(), datos.getEstado(), datos.getBeneficio());
    }
}
