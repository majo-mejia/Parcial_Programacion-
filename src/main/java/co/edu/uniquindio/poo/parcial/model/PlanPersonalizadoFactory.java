package co.edu.uniquindio.poo.parcial.model;

public class PlanPersonalizadoFactory extends PlanFactory {
    @Override
    public PlanEntrenamiento crearPlan(DatosPlan datos) {
        return new PlanPersonalizado(datos.getCodigo(), datos.getNombre(), datos.getDescripcion(),
                datos.getDuracionMeses(), datos.getValorMensual(), datos.getEstado(),
                datos.getCantidadSesiones(), datos.getEspecialidad(), datos.getObjetivo());
    }
}
